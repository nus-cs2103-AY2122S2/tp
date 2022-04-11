package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC_LONG;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_LONG;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_SHORT;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.USERNAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.USERNAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_USERNAME_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GIT_USERNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.testutil.PersonBuilder;


public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();


    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + USERNAME_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + USERNAME_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + USERNAME_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + USERNAME_DESC_AMY
                + USERNAME_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + USERNAME_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedPersonMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // Phone number lesser than 3 digits
        assertParseFailure(parser, NAME_DESC_AMY + INVALID_PHONE_SHORT + EMAIL_DESC_AMY + USERNAME_DESC_AMY
            + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);
        // Phone number lesser than 15 digits
        assertParseFailure(parser, NAME_DESC_AMY + INVALID_PHONE_LONG + EMAIL_DESC_AMY + USERNAME_DESC_AMY
                + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);
        // Email longer than 54 characters
        assertParseFailure(parser, NAME_DESC_AMY + PHONE_DESC_AMY + INVALID_EMAIL_DESC_LONG + USERNAME_DESC_AMY
                + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_incorrectFields_failure() {
        // Phone number too short
        Person expectedPerson = new PersonBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + USERNAME_DESC_AMY,
                new AddCommand(expectedPerson));
    }


    @Test
    public void parse_compulsoryFieldMissing_failure() {

        // missing name prefix
        String missingNameMessage = missingParametersErrorMessage(false, true,
                true, true).toString() + "\n" + AddCommand.MESSAGE_USAGE;
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + USERNAME_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, missingNameMessage));

        // missing phone prefix
        String missingPhoneMessage = missingParametersErrorMessage(true, false,
                true, true).toString() + "\n" + AddCommand.MESSAGE_USAGE;
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + USERNAME_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, missingPhoneMessage));

        // missing email prefix
        String missingEmailMessage = missingParametersErrorMessage(true, true,
                false, true).toString() + "\n" + AddCommand.MESSAGE_USAGE;
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + USERNAME_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, missingEmailMessage));

        // missing username prefix
        String missingUsernameMessage = missingParametersErrorMessage(true, true,
                true, false).toString() + "\n" + AddCommand.MESSAGE_USAGE;
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_USERNAME_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, missingUsernameMessage));

        // all prefixes missing
        String missingAllMessage = missingParametersErrorMessage(false, false,
                false, false).toString() + "\n" + AddCommand.MESSAGE_USAGE;
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_USERNAME_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, missingAllMessage));
    }

    private StringBuffer missingParametersErrorMessage(boolean hasName, boolean hasPhone,
                                                       boolean hasEmail, boolean hasGit) {
        String errorString = "Missing/Invalid parameters: ";
        if (!hasName) {
            errorString += PREFIX_NAME + ", ";
        }
        if (!hasPhone) {
            errorString += PREFIX_PHONE + ", ";
        }
        if (!hasEmail) {
            errorString += PREFIX_EMAIL + ", ";
        }
        if (!hasGit) {
            errorString += PREFIX_GIT_USERNAME + ", ";
        }
        StringBuffer sb = new StringBuffer(errorString);
        sb.delete(sb.length() - 2, sb.length() - 1); //Deleting last comma
        return sb;
    }

}
