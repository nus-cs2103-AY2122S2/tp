package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.ID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ID_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_CODE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TELEGRAM_HANDLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TELEGRAM_HANDLE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TELEGRAM_HANDLE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_HANDLE_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.person.Email;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.TelegramHandle;
import seedu.address.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + ID_DESC_BOB + NAME_DESC_BOB + MODULE_CODE_DESC_BOB
                + PHONE_DESC_BOB + TELEGRAM_HANDLE_DESC_BOB + EMAIL_DESC_BOB, new AddCommand(expectedPerson));

        // multiple ids - last id accepted
        assertParseSuccess(parser, ID_DESC_AMY + ID_DESC_BOB + NAME_DESC_BOB + MODULE_CODE_DESC_BOB
                + PHONE_DESC_BOB + TELEGRAM_HANDLE_DESC_BOB + EMAIL_DESC_BOB, new AddCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, ID_DESC_BOB + NAME_DESC_AMY + NAME_DESC_BOB + MODULE_CODE_DESC_BOB
                + PHONE_DESC_BOB + TELEGRAM_HANDLE_DESC_BOB + EMAIL_DESC_BOB, new AddCommand(expectedPerson));

        // multiple module codes - last module code accepted
        assertParseSuccess(parser, ID_DESC_BOB + NAME_DESC_BOB + MODULE_CODE_DESC_AMY + MODULE_CODE_DESC_BOB
                + PHONE_DESC_BOB + TELEGRAM_HANDLE_DESC_BOB + EMAIL_DESC_BOB, new AddCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, ID_DESC_BOB + NAME_DESC_BOB + MODULE_CODE_DESC_BOB + PHONE_DESC_AMY
                + PHONE_DESC_BOB + TELEGRAM_HANDLE_DESC_BOB + EMAIL_DESC_BOB, new AddCommand(expectedPerson));

        // multiple telegram handles - last phone accepted
        assertParseSuccess(parser, ID_DESC_BOB + NAME_DESC_BOB + MODULE_CODE_DESC_BOB + PHONE_DESC_BOB
                + TELEGRAM_HANDLE_DESC_AMY + TELEGRAM_HANDLE_DESC_BOB + EMAIL_DESC_BOB, new AddCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, ID_DESC_BOB + NAME_DESC_BOB + MODULE_CODE_DESC_BOB + PHONE_DESC_BOB
                + TELEGRAM_HANDLE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB, new AddCommand(expectedPerson));

    }
     //TODO: Fix this testcase
    @Test
    public void parse_optionalFieldsMissing_success() {
        // missing phone
        Person expectedPerson = new PersonBuilder(AMY).withPhone(null).build();
        assertParseSuccess(parser, ID_DESC_AMY + NAME_DESC_AMY + MODULE_CODE_DESC_AMY
                + TELEGRAM_HANDLE_DESC_AMY + EMAIL_DESC_AMY, new AddCommand(expectedPerson));

        // missing telegramHandle
        expectedPerson = new PersonBuilder(AMY).withTelegramHandle(null).build();
        assertParseSuccess(parser, ID_DESC_AMY + NAME_DESC_AMY + MODULE_CODE_DESC_AMY + PHONE_DESC_AMY
                + EMAIL_DESC_AMY, new AddCommand(expectedPerson));

        // missing email
        expectedPerson = new PersonBuilder(AMY).withEmail(null).build();
        assertParseSuccess(parser, ID_DESC_AMY + NAME_DESC_AMY + MODULE_CODE_DESC_AMY + PHONE_DESC_AMY
                + TELEGRAM_HANDLE_DESC_AMY, new AddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing studentId prefix
        assertParseFailure(parser, VALID_ID_BOB + NAME_DESC_BOB + MODULE_CODE_DESC_BOB, expectedMessage);

        // missing name prefix
        assertParseFailure(parser, ID_DESC_BOB + VALID_NAME_BOB + MODULE_CODE_DESC_BOB, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, ID_DESC_BOB + NAME_DESC_BOB + VALID_MODULE_CODE_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_ID_BOB + VALID_NAME_BOB + VALID_MODULE_CODE_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid studentId
        assertParseFailure(parser, INVALID_ID_DESC + NAME_DESC_BOB + MODULE_CODE_DESC_BOB + PHONE_DESC_BOB
                + TELEGRAM_HANDLE_DESC_BOB + EMAIL_DESC_BOB, StudentId.MESSAGE_CONSTRAINTS);

        // invalid name
        assertParseFailure(parser, ID_DESC_BOB + INVALID_NAME_DESC + MODULE_CODE_DESC_BOB + PHONE_DESC_BOB
                + TELEGRAM_HANDLE_DESC_BOB + EMAIL_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // invalid moduleCode
        assertParseFailure(parser, ID_DESC_BOB + NAME_DESC_BOB + INVALID_MODULE_CODE_DESC + PHONE_DESC_BOB
                + TELEGRAM_HANDLE_DESC_BOB + EMAIL_DESC_BOB, ModuleCode.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, ID_DESC_BOB + NAME_DESC_BOB + MODULE_CODE_DESC_BOB + INVALID_PHONE_DESC
                + TELEGRAM_HANDLE_DESC_BOB + EMAIL_DESC_BOB, Phone.MESSAGE_CONSTRAINTS);

        // invalid telegramHandle
        assertParseFailure(parser, ID_DESC_BOB + NAME_DESC_BOB + MODULE_CODE_DESC_BOB + PHONE_DESC_BOB
                + INVALID_TELEGRAM_HANDLE_DESC + EMAIL_DESC_BOB, TelegramHandle.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, ID_DESC_BOB + NAME_DESC_BOB + MODULE_CODE_DESC_BOB + PHONE_DESC_BOB
                + TELEGRAM_HANDLE_DESC_BOB + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_ID_DESC + INVALID_NAME_DESC + MODULE_CODE_DESC_BOB + PHONE_DESC_BOB
                + TELEGRAM_HANDLE_DESC_BOB + EMAIL_DESC_BOB, StudentId.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + ID_DESC_BOB + NAME_DESC_BOB
                + MODULE_CODE_DESC_BOB + PHONE_DESC_BOB + TELEGRAM_HANDLE_DESC_BOB + EMAIL_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
