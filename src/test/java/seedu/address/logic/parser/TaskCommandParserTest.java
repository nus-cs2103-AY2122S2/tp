package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ID_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TELEGRAM_HANDLE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.TaskCommand;
import seedu.address.model.person.StudentId;

public class TaskCommandParserTest {
    private TaskCommandParser parser = new TaskCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                TaskCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_nonemptyPreamble_throwsParseException() {
        assertParseFailure(parser, PREAMBLE_NON_EMPTY, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                TaskCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidFields_throwsParseException() {
        // invalid student id
        assertParseFailure(parser, PREAMBLE_WHITESPACE + INVALID_ID_DESC,
                StudentId.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_nonSupportedFields_throwsParseException() {
        // name
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskCommand.MESSAGE_USAGE));

        // module code
        assertParseFailure(parser, PREAMBLE_WHITESPACE + MODULE_CODE_DESC_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskCommand.MESSAGE_USAGE));

        // telegram handle
        assertParseFailure(parser, PREAMBLE_WHITESPACE + TELEGRAM_HANDLE_DESC_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskCommand.MESSAGE_USAGE));

        // phone number
        assertParseFailure(parser, PREAMBLE_WHITESPACE + PHONE_DESC_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskCommand.MESSAGE_USAGE));

        // email
        assertParseFailure(parser, PREAMBLE_WHITESPACE + EMAIL_DESC_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validStudentId_returnsTaskCommand() {
        // valid student id
        TaskCommand expectedTaskCommand =
                new TaskCommand(new StudentId(VALID_ID_BOB));

        // no white space before id
        assertParseSuccess(parser, ID_DESC_BOB, expectedTaskCommand);

        // whitespace before id
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + ID_DESC_BOB, expectedTaskCommand);
    }
}
