package woofareyou.logic.parser;

import static woofareyou.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static woofareyou.commons.core.Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX;
import static woofareyou.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static woofareyou.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static woofareyou.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static woofareyou.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static woofareyou.logic.commands.CommandTestUtil.INVALID_OWNER_NAME_DESC;
import static woofareyou.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static woofareyou.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static woofareyou.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static woofareyou.logic.commands.CommandTestUtil.OWNER_NAME_DESC_AMY;
import static woofareyou.logic.commands.CommandTestUtil.OWNER_NAME_DESC_BOB;
import static woofareyou.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static woofareyou.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static woofareyou.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static woofareyou.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static woofareyou.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static woofareyou.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static woofareyou.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static woofareyou.logic.commands.CommandTestUtil.VALID_OWNER_NAME_AMY;
import static woofareyou.logic.commands.CommandTestUtil.VALID_OWNER_NAME_BOB;
import static woofareyou.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static woofareyou.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static woofareyou.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static woofareyou.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static woofareyou.logic.parser.CliSyntax.PREFIX_TAG;
import static woofareyou.logic.parser.CommandParserTestUtil.assertParseFailure;
import static woofareyou.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static woofareyou.testutil.TypicalIndexes.INDEX_FIRST_PET;
import static woofareyou.testutil.TypicalIndexes.INDEX_SECOND_PET;
import static woofareyou.testutil.TypicalIndexes.INDEX_THIRD_PET;

import org.junit.jupiter.api.Test;

import woofareyou.commons.core.Messages;
import woofareyou.commons.core.index.Index;
import woofareyou.logic.commands.EditCommand;
import woofareyou.logic.commands.EditCommand.EditPetDescriptor;
import woofareyou.model.pet.Address;
import woofareyou.model.pet.Name;
import woofareyou.model.pet.OwnerName;
import woofareyou.model.pet.Phone;
import woofareyou.model.tag.Tag;
import woofareyou.testutil.EditPetDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);
    private static final String NEG_INTEGER_MAX = String.valueOf(-(Integer.MAX_VALUE + 1));
    private static final String POS_INTEGER_MAX = String.valueOf(Integer.MAX_VALUE + 1);


    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string" + NAME_DESC_AMY,
                Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string" + NAME_DESC_AMY, Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX);
    }

    @Test
    public void parse_invalidValue_failure() {

        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone

        // invalid ownerName
        assertParseFailure(parser, "1" + INVALID_OWNER_NAME_DESC, OwnerName.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid number of tags
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, Tag.MESSAGE_SIZE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);

        // invalid phone followed by valid ownerName
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + OWNER_NAME_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);


        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_OWNER_NAME_DESC + VALID_ADDRESS_AMY
                        + VALID_PHONE_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PET;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + TAG_DESC_HUSBAND
                + OWNER_NAME_DESC_AMY + ADDRESS_DESC_AMY + NAME_DESC_AMY;

        EditCommand.EditPetDescriptor descriptor = new EditPetDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withOwnerName(VALID_OWNER_NAME_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTag(VALID_TAG_HUSBAND).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PET;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + OWNER_NAME_DESC_AMY;

        EditCommand.EditPetDescriptor descriptor = new EditPetDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withOwnerName(VALID_OWNER_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_PET;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditCommand.EditPetDescriptor descriptor = new EditPetDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
        descriptor = new EditPetDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // ownerName
        userInput = targetIndex.getOneBased() + OWNER_NAME_DESC_AMY;
        descriptor = new EditPetDescriptorBuilder().withOwnerName(VALID_OWNER_NAME_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + ADDRESS_DESC_AMY;
        descriptor = new EditPetDescriptorBuilder().withAddress(VALID_ADDRESS_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_FRIEND;
        descriptor = new EditPetDescriptorBuilder().withTag(VALID_TAG_FRIEND).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_PET;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_AMY + ADDRESS_DESC_AMY + OWNER_NAME_DESC_AMY
                + TAG_DESC_FRIEND + PHONE_DESC_AMY + ADDRESS_DESC_AMY + OWNER_NAME_DESC_AMY + PHONE_DESC_BOB
                + ADDRESS_DESC_BOB + OWNER_NAME_DESC_BOB;

        EditCommand.EditPetDescriptor descriptor = new EditPetDescriptorBuilder()
                .withPhone(VALID_PHONE_BOB)
                .withOwnerName(VALID_OWNER_NAME_BOB)
                .withAddress(VALID_ADDRESS_BOB)
                .withTag(VALID_TAG_FRIEND)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_PET;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        EditCommand.EditPetDescriptor descriptor = new EditPetDescriptorBuilder().withPhone(VALID_PHONE_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + OWNER_NAME_DESC_BOB + INVALID_PHONE_DESC + ADDRESS_DESC_BOB
                + PHONE_DESC_BOB;
        descriptor = new EditPetDescriptorBuilder().withPhone(VALID_PHONE_BOB).withOwnerName(VALID_OWNER_NAME_BOB)
                .withAddress(VALID_ADDRESS_BOB).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_PET;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditPetDescriptor descriptor = new EditPetDescriptorBuilder().withTag().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_indexOutOfBoundsIntegerOverflow_throwsParseException() {
        // large positive number
        assertParseFailure(parser, POS_INTEGER_MAX + TAG_DESC_HUSBAND, MESSAGE_INVALID_PET_DISPLAYED_INDEX);
        // large negative number
        assertParseFailure(parser, NEG_INTEGER_MAX + TAG_DESC_HUSBAND, MESSAGE_INVALID_PET_DISPLAYED_INDEX);
    }

}
