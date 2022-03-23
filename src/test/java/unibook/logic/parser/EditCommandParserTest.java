package unibook.logic.parser;

import static unibook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static unibook.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static unibook.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static unibook.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static unibook.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static unibook.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static unibook.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static unibook.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static unibook.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static unibook.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static unibook.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static unibook.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static unibook.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static unibook.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static unibook.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static unibook.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static unibook.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static unibook.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static unibook.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static unibook.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static unibook.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static unibook.logic.parser.CliSyntax.PREFIX_TAG;
import static unibook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static unibook.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static unibook.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static unibook.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static unibook.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import unibook.commons.core.index.Index;
import unibook.logic.commands.EditCommand;
import unibook.logic.commands.EditCommand.EditPersonDescriptor;
import unibook.model.person.Email;
import unibook.model.person.Name;
import unibook.model.person.Phone;
import unibook.model.tag.Tag;
import unibook.testutil.EditPersonDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.PERSON_MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {

        // no field specified
        assertParseFailure(parser, " 1 o/person ", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        // assertParseFailure(parser, " o/person ", MESSAGE_INVALID_FORMAT);
    }

    //    @Test
    //    public void parse_invalidPreamble_failure() {
    //        // negative index
    //        assertParseFailure(parser, " -5 " + " o/person " + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);
    //
    //        // zero index
    //        assertParseFailure(parser, " 0 " + " o/person " + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);
    //
    //        // invalid arguments being parsed as preamble
    //        assertParseFailure(parser, " 1 some random string o/person", MESSAGE_INVALID_FORMAT);
    //
    //        // invalid prefix being parsed as preamble
    //        assertParseFailure(parser, " 1 i/ string o/person", MESSAGE_INVALID_FORMAT);
    //    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, "1" + " o/person " + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS);
        // invalid phone
        assertParseFailure(parser, "1" + " o/person " + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);
        // invalid email
        assertParseFailure(parser, "1" + " o/person " + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS);
        // invalid tag
        assertParseFailure(parser, "1" + " o/person " + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + " o/person " + INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + " o/person " + PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1"
                                    + " o/person "
                                    + TAG_DESC_FRIEND
                                    + TAG_DESC_HUSBAND
                                    + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1"
                                   + " o/person "
                                   + TAG_DESC_FRIEND
                                   + TAG_EMPTY
                                   + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1"
                                    + " o/person "
                                    + TAG_EMPTY
                                    + TAG_DESC_FRIEND
                                    + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1"
                                    + " o/person "
                                    + INVALID_NAME_DESC
                                    + INVALID_EMAIL_DESC
                                    + VALID_ADDRESS_AMY
                                    + VALID_PHONE_AMY, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + " o/person " + PHONE_DESC_BOB + TAG_DESC_HUSBAND
                + EMAIL_DESC_AMY + NAME_DESC_AMY + TAG_DESC_FRIEND;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " o/person " + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + " o/person " + NAME_DESC_AMY;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + " o/person " + PHONE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + " o/person " + EMAIL_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + " o/person " + TAG_DESC_FRIEND;
        descriptor = new EditPersonDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " o/person " + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + TAG_DESC_FRIEND + PHONE_DESC_AMY + EMAIL_DESC_AMY + TAG_DESC_FRIEND
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + TAG_DESC_HUSBAND;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " o/person " + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + " o/person " + EMAIL_DESC_BOB + INVALID_PHONE_DESC
                + PHONE_DESC_BOB;
        descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + " o/person " + TAG_EMPTY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
