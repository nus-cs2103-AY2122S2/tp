package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_NOT_EDITED;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_A;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_DESC_BIG_BANK;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_A;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_B;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BIG_BANK;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_JANICE_STREET;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_A;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_B;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_APPLIED;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_INTERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_A;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_BIG_BANK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_JANICE_STREET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_A;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_A;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_INTERVIEW;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ENTRY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ENTRY;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_ENTRY;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCompanyCommand;
import seedu.address.logic.commands.EditCompanyCommand.EditCompanyDescriptor;
import seedu.address.model.entry.Email;
import seedu.address.model.entry.Name;
import seedu.address.model.entry.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditCompanyDescriptorBuilder;

public class EditCompanyCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCompanyCommand.MESSAGE_USAGE);

    private EditCompanyCommandParser parser = new EditCompanyCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_COMPANY_BIG_BANK, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + COMPANY_DESC_BIG_BANK, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + COMPANY_DESC_BIG_BANK, MESSAGE_INVALID_FORMAT);

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
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_A, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_B + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Company} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_INTERVIEW + TAG_DESC_APPLIED + TAG_EMPTY,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_INTERVIEW + TAG_EMPTY + TAG_DESC_APPLIED,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_INTERVIEW + TAG_DESC_APPLIED,
                Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC
                        + VALID_COMPANY_BIG_BANK + VALID_PHONE_A,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_ENTRY;
        String userInput = targetIndex.getOneBased() + NAME_DESC_BIG_BANK + PHONE_DESC_A + TAG_DESC_INTERVIEW
                + EMAIL_DESC_A + ADDRESS_DESC_A;

        EditCompanyDescriptor descriptor = new EditCompanyDescriptorBuilder().withName(VALID_COMPANY_BIG_BANK)
                .withPhone(VALID_PHONE_A).withEmail(VALID_EMAIL_A).withAddress(VALID_ADDRESS_A)
                .withTags(VALID_TAG_INTERVIEW).build();
        EditCompanyCommand expectedCommand = new EditCompanyCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_ENTRY;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_A + EMAIL_DESC_A;

        EditCompanyDescriptor descriptor = new EditCompanyDescriptorBuilder().withPhone(VALID_PHONE_A)
                .withEmail(VALID_EMAIL_A).build();
        EditCompanyCommand expectedCommand = new EditCompanyCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_ENTRY;
        String userInput = targetIndex.getOneBased() + NAME_DESC_BIG_BANK;
        EditCompanyDescriptor descriptor = new EditCompanyDescriptorBuilder().withName(VALID_COMPANY_BIG_BANK).build();
        EditCompanyCommand expectedCommand = new EditCompanyCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // company
        userInput = targetIndex.getOneBased() + NAME_DESC_BIG_BANK;
        descriptor = new EditCompanyDescriptorBuilder().withName(VALID_COMPANY_BIG_BANK).build();
        expectedCommand = new EditCompanyCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_A;
        descriptor = new EditCompanyDescriptorBuilder().withPhone(VALID_PHONE_A).build();
        expectedCommand = new EditCompanyCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_A;
        descriptor = new EditCompanyDescriptorBuilder().withEmail(VALID_EMAIL_A).build();
        expectedCommand = new EditCompanyCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + ADDRESS_DESC_A;
        descriptor = new EditCompanyDescriptorBuilder().withAddress(VALID_ADDRESS_A).build();
        expectedCommand = new EditCompanyCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_INTERVIEW;
        descriptor = new EditCompanyDescriptorBuilder().withTags(VALID_TAG_INTERVIEW).build();
        expectedCommand = new EditCompanyCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_ENTRY;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_A + NAME_DESC_BIG_BANK + EMAIL_DESC_A
                + PHONE_DESC_A + NAME_DESC_BIG_BANK + EMAIL_DESC_A
                + PHONE_DESC_B + NAME_DESC_JANICE_STREET + EMAIL_DESC_B + TAG_DESC_INTERVIEW;

        EditCompanyDescriptor descriptor = new EditCompanyDescriptorBuilder().withName(VALID_COMPANY_JANICE_STREET)
                .withEmail(VALID_EMAIL_B).withPhone(VALID_PHONE_B).withTags(VALID_TAG_INTERVIEW)
                .build();
        EditCompanyCommand expectedCommand = new EditCompanyCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_ENTRY;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_B;
        EditCompanyDescriptor descriptor = new EditCompanyDescriptorBuilder().withPhone(VALID_PHONE_B).build();
        EditCompanyCommand expectedCommand = new EditCompanyCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_B + INVALID_PHONE_DESC + NAME_DESC_JANICE_STREET
                + PHONE_DESC_B;
        descriptor = new EditCompanyDescriptorBuilder().withPhone(VALID_PHONE_B).withEmail(VALID_EMAIL_B)
                .withName(VALID_COMPANY_JANICE_STREET).build();
        expectedCommand = new EditCompanyCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_ENTRY;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditCompanyDescriptor descriptor = new EditCompanyDescriptorBuilder().withTags().build();
        EditCompanyCommand expectedCommand = new EditCompanyCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
