package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_GARENA;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_SHOPEE;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_GARENA;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_SHOPEE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_JOBTITLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.JOBTITLE_DESC_GARENA;
import static seedu.address.logic.commands.CommandTestUtil.JOBTITLE_DESC_SHOPEE;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_GARENA;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_SHOPEE;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_GARENA;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_SHOPEE;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_LOCAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_GARENA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_GARENA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOBTITLE_GARENA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_GARENA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_GARENA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_LOCAL;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalApplications.GARENA;
import static seedu.address.testutil.TypicalApplications.SHOPEE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.Command;
import seedu.address.model.application.Address;
import seedu.address.model.application.Application;
import seedu.address.model.application.Email;
import seedu.address.model.application.JobTitle;
import seedu.address.model.application.Name;
import seedu.address.model.application.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.ApplicationBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Application expectedApplication = new ApplicationBuilder(GARENA).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_GARENA + PHONE_DESC_GARENA + EMAIL_DESC_GARENA
                + ADDRESS_DESC_GARENA + TAG_DESC_FRIEND + JOBTITLE_DESC_GARENA, new AddCommand(expectedApplication));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_SHOPEE + NAME_DESC_GARENA + PHONE_DESC_GARENA + EMAIL_DESC_GARENA
                + ADDRESS_DESC_GARENA + TAG_DESC_FRIEND + JOBTITLE_DESC_GARENA, new AddCommand(expectedApplication));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_GARENA + PHONE_DESC_SHOPEE + PHONE_DESC_GARENA + EMAIL_DESC_GARENA
                + ADDRESS_DESC_GARENA + TAG_DESC_FRIEND + JOBTITLE_DESC_GARENA, new AddCommand(expectedApplication));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_GARENA + PHONE_DESC_GARENA + EMAIL_DESC_SHOPEE + EMAIL_DESC_GARENA
                + ADDRESS_DESC_GARENA + TAG_DESC_FRIEND + JOBTITLE_DESC_GARENA, new AddCommand(expectedApplication));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_GARENA + PHONE_DESC_GARENA + EMAIL_DESC_GARENA + ADDRESS_DESC_SHOPEE
                + ADDRESS_DESC_GARENA + TAG_DESC_FRIEND + JOBTITLE_DESC_GARENA, new AddCommand(expectedApplication));

        // multiple tags - all accepted
        Application expectedApplicationMultipleTags = new ApplicationBuilder(GARENA)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_LOCAL).build();
        String userInput = NAME_DESC_GARENA + PHONE_DESC_GARENA + EMAIL_DESC_GARENA + ADDRESS_DESC_GARENA
                + TAG_DESC_LOCAL + TAG_DESC_FRIEND + JOBTITLE_DESC_GARENA;
        Command output = new AddCommand(expectedApplicationMultipleTags);
        assertParseSuccess(parser, userInput, output);

        // multiple job titles - last job title accepted
        assertParseSuccess(parser, NAME_DESC_GARENA + PHONE_DESC_GARENA + EMAIL_DESC_GARENA + ADDRESS_DESC_SHOPEE
                        + ADDRESS_DESC_GARENA + TAG_DESC_FRIEND + JOBTITLE_DESC_SHOPEE + JOBTITLE_DESC_GARENA,
                new AddCommand(expectedApplication));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Application expectedApplication = new ApplicationBuilder(SHOPEE).withTags().build();
        assertParseSuccess(parser, NAME_DESC_SHOPEE + PHONE_DESC_SHOPEE + EMAIL_DESC_SHOPEE + ADDRESS_DESC_SHOPEE
                        + JOBTITLE_DESC_SHOPEE,
                new AddCommand(expectedApplication));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_GARENA + PHONE_DESC_GARENA + EMAIL_DESC_GARENA + ADDRESS_DESC_GARENA
                        + JOBTITLE_DESC_GARENA,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_GARENA + VALID_PHONE_GARENA + EMAIL_DESC_GARENA + ADDRESS_DESC_GARENA
                        + JOBTITLE_DESC_GARENA,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_GARENA + PHONE_DESC_GARENA + VALID_EMAIL_GARENA + ADDRESS_DESC_GARENA
                        + JOBTITLE_DESC_GARENA,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_GARENA + PHONE_DESC_GARENA + EMAIL_DESC_GARENA + VALID_ADDRESS_GARENA
                        + JOBTITLE_DESC_GARENA,
                expectedMessage);

        // missing jobTitle prefix
        assertParseFailure(parser, NAME_DESC_GARENA + PHONE_DESC_GARENA + EMAIL_DESC_GARENA + ADDRESS_DESC_GARENA
                        + VALID_JOBTITLE_GARENA,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_GARENA + VALID_PHONE_GARENA + VALID_EMAIL_GARENA + VALID_ADDRESS_GARENA
                        + VALID_JOBTITLE_GARENA,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_GARENA + EMAIL_DESC_GARENA + ADDRESS_DESC_GARENA
                + TAG_DESC_LOCAL + TAG_DESC_FRIEND + JOBTITLE_DESC_GARENA, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_GARENA + INVALID_PHONE_DESC + EMAIL_DESC_GARENA + ADDRESS_DESC_GARENA
                + TAG_DESC_LOCAL + TAG_DESC_FRIEND + JOBTITLE_DESC_GARENA, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_GARENA + PHONE_DESC_GARENA + INVALID_EMAIL_DESC + ADDRESS_DESC_GARENA
                + TAG_DESC_LOCAL + TAG_DESC_FRIEND + JOBTITLE_DESC_GARENA, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_GARENA + PHONE_DESC_GARENA + EMAIL_DESC_GARENA + INVALID_ADDRESS_DESC
                + TAG_DESC_LOCAL + TAG_DESC_FRIEND + JOBTITLE_DESC_GARENA, Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_GARENA + PHONE_DESC_GARENA + EMAIL_DESC_GARENA + ADDRESS_DESC_GARENA
                + INVALID_TAG_DESC + VALID_TAG_FRIEND + JOBTITLE_DESC_GARENA, Tag.MESSAGE_CONSTRAINTS);

        // invalid jobTitle
        assertParseFailure(parser, NAME_DESC_GARENA + PHONE_DESC_GARENA + EMAIL_DESC_GARENA + ADDRESS_DESC_GARENA
                + TAG_DESC_LOCAL + TAG_DESC_FRIEND + INVALID_JOBTITLE_DESC, JobTitle.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_GARENA + EMAIL_DESC_GARENA + INVALID_ADDRESS_DESC
                        + JOBTITLE_DESC_GARENA,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_GARENA + PHONE_DESC_GARENA + EMAIL_DESC_GARENA
                + ADDRESS_DESC_GARENA + TAG_DESC_LOCAL + TAG_DESC_FRIEND + JOBTITLE_DESC_GARENA,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
