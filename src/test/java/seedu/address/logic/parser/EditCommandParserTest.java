package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_GARENA;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_SHOPEE;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_GARENA;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_SHOPEE;
import static seedu.address.logic.commands.CommandTestUtil.INTERVIEWSLOT_DESC_GARENA;
import static seedu.address.logic.commands.CommandTestUtil.INTERVIEWSLOT_DESC_SHOPEE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_INTERVIESLOT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_SHOPEE;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_GARENA;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_SHOPEE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_LOCAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_GARENA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_SHOPEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_GARENA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_SHOPEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INTERVIEW_SLOT_GARENA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INTERVIEW_SLOT_SHOPEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_SHOPEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_GARENA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_SHOPEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_LOCAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW_SLOT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_APPLICATION;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_APPLICATION;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditApplicationDescriptor;
import seedu.address.model.application.Address;
import seedu.address.model.application.Email;
import seedu.address.model.application.InterviewSlot;
import seedu.address.model.application.Name;
import seedu.address.model.application.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditApplicationDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;
    private static final String INTERVIEWSLOT_EMPTY = " " + PREFIX_INTERVIEW_SLOT;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_SHOPEE, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_SHOPEE, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_SHOPEE, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "1" + INVALID_INTERVIESLOT_DESC, InterviewSlot.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_SHOPEE, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_GARENA + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Application} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_DESC_LOCAL + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_LOCAL, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_LOCAL, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1"
                        + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_ADDRESS_SHOPEE + VALID_PHONE_SHOPEE,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_resetTags_failure() {
        Index targetIndex = INDEX_THIRD_APPLICATION;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseFailure(parser, userInput, Tag.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_APPLICATION;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_GARENA + TAG_DESC_LOCAL
                + EMAIL_DESC_SHOPEE + ADDRESS_DESC_SHOPEE + INTERVIEWSLOT_DESC_SHOPEE
                + NAME_DESC_SHOPEE + TAG_DESC_FRIEND;

        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder().withName(VALID_NAME_SHOPEE)
                .withPhone(VALID_PHONE_GARENA).withEmail(VALID_EMAIL_SHOPEE).withAddress(VALID_ADDRESS_SHOPEE)
                .withInterviewSlot(VALID_INTERVIEW_SLOT_SHOPEE)
                .withTags(VALID_TAG_LOCAL, VALID_TAG_FRIEND).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_APPLICATION;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_GARENA + EMAIL_DESC_SHOPEE;

        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder().withPhone(VALID_PHONE_GARENA)
                .withEmail(VALID_EMAIL_SHOPEE).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_APPLICATION;
        String userInput = targetIndex.getOneBased() + NAME_DESC_SHOPEE;
        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder()
                .withName(VALID_NAME_SHOPEE).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_SHOPEE;
        descriptor = new EditApplicationDescriptorBuilder().withPhone(VALID_PHONE_SHOPEE).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_SHOPEE;
        descriptor = new EditApplicationDescriptorBuilder().withEmail(VALID_EMAIL_SHOPEE).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // interview slot
        userInput = targetIndex.getOneBased() + INTERVIEWSLOT_DESC_GARENA;
        descriptor = new EditApplicationDescriptorBuilder().withInterviewSlot(VALID_INTERVIEW_SLOT_GARENA).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + ADDRESS_DESC_SHOPEE;
        descriptor = new EditApplicationDescriptorBuilder().withAddress(VALID_ADDRESS_SHOPEE).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_FRIEND;
        descriptor = new EditApplicationDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_APPLICATION;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_SHOPEE + ADDRESS_DESC_SHOPEE + EMAIL_DESC_SHOPEE
                + TAG_DESC_FRIEND + PHONE_DESC_SHOPEE + ADDRESS_DESC_SHOPEE + EMAIL_DESC_SHOPEE + TAG_DESC_FRIEND
                + PHONE_DESC_GARENA + ADDRESS_DESC_GARENA + EMAIL_DESC_GARENA + TAG_DESC_LOCAL;

        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder().withPhone(VALID_PHONE_GARENA)
                .withEmail(VALID_EMAIL_GARENA).withAddress(VALID_ADDRESS_GARENA)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_LOCAL)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_APPLICATION;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_GARENA;
        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder()
                .withPhone(VALID_PHONE_GARENA).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_GARENA + INVALID_PHONE_DESC + ADDRESS_DESC_GARENA
                + PHONE_DESC_GARENA;
        descriptor = new EditApplicationDescriptorBuilder().withPhone(VALID_PHONE_GARENA).withEmail(VALID_EMAIL_GARENA)
                .withAddress(VALID_ADDRESS_GARENA).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
