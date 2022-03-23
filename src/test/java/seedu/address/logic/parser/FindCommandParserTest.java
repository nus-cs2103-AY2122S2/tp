package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ID_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_CODE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TELEGRAM_HANDLE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.ModuleCodeContainsKeywordsPredicate;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.StudentIdContainsKeywordsPredicate;


import java.util.Arrays;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_nonemptyPreamble_throwsParseException() {
        assertParseFailure(parser, PREAMBLE_NON_EMPTY, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_multipleFields_throwsParseException() {
        // all 3 fields
        assertParseFailure(parser, PREAMBLE_WHITESPACE + ID_DESC_BOB + NAME_DESC_BOB + MODULE_CODE_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        // student id + name
        assertParseFailure(parser, PREAMBLE_WHITESPACE + ID_DESC_BOB + NAME_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        // student id + mod code
        assertParseFailure(parser, PREAMBLE_WHITESPACE + ID_DESC_BOB + MODULE_CODE_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        // name + mod code
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + MODULE_CODE_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidFields_throwsParseException() {
        // invalid student id
        assertParseFailure(parser, PREAMBLE_WHITESPACE + INVALID_ID_DESC,
                StudentId.MESSAGE_CONSTRAINTS);

        // invalid name
        assertParseFailure(parser, PREAMBLE_WHITESPACE + INVALID_NAME_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // invalid mod code
        assertParseFailure(parser, PREAMBLE_WHITESPACE + INVALID_MODULE_CODE_DESC,
                ModuleCode.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_nonSupportedFields_throwsParseException() {
        // telegram handle
        assertParseFailure(parser, PREAMBLE_WHITESPACE + TELEGRAM_HANDLE_DESC_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        // phone number
        assertParseFailure(parser, PREAMBLE_WHITESPACE + PHONE_DESC_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        // email
        assertParseFailure(parser, PREAMBLE_WHITESPACE + EMAIL_DESC_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validStudentId_returnsFindCommand() {
        // valid student id
        FindCommand expectedFindCommand =
                new FindCommand(
                        new StudentIdContainsKeywordsPredicate(
                                Arrays.asList(VALID_ID_BOB.split("\\s+"))));

        // no white space before id
        assertParseSuccess(parser, ID_DESC_BOB, expectedFindCommand);

        // whitespace before id
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + ID_DESC_BOB, expectedFindCommand);
    }

    @Test
    public void parse_validName_returnsFindCommand() {
        // valid name
        FindCommand expectedFindCommand =
                new FindCommand(
                        new NameContainsKeywordsPredicate(
                                Arrays.asList(VALID_NAME_BOB.split("\\s+"))));

        // no white space before name
        assertParseSuccess(parser, NAME_DESC_BOB, expectedFindCommand);

        // whitespace before name
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB, expectedFindCommand);
    }

    @Test
    public void parse_validModuleCode_returnsFindCommand() {
        // valid module code
        FindCommand expectedFindCommand =
                new FindCommand(
                        new ModuleCodeContainsKeywordsPredicate(
                                Arrays.asList(VALID_MODULE_CODE_BOB.split("\\s+"))));

        // no white space before module code
        assertParseSuccess(parser, MODULE_CODE_DESC_BOB, expectedFindCommand);

        // whitespace before module code
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + MODULE_CODE_DESC_BOB, expectedFindCommand);
    }
}
