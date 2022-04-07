package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_CODE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASKNAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TASK_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TELEGRAM_HANDLE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_HANDLE_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ProgressCommand;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Person;
import seedu.address.model.person.Task;
import seedu.address.testutil.PersonBuilder;

public class ProgressCommandParserTest {
    private ProgressCommandParser parser = new ProgressCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "   ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ProgressCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_nonemptyPreamble_throwsParseException() {
        assertParseFailure(parser, PREAMBLE_NON_EMPTY, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ProgressCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validModuleCodeAndTaskName_returnsProgressCommand() {
        Person amy = new PersonBuilder().withStudentId(VALID_ID_AMY)
                .withModuleCode(VALID_MODULE_CODE_AMY)
                .withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY)
                .withTelegramHandle(VALID_TELEGRAM_HANDLE_AMY)
                .withEmail(VALID_EMAIL_AMY).build();
        amy.addTask(new Task("Task A"));
        amy.markTaskAsComplete(0);
        Task task = amy.getTaskList().getTaskList().get(0);
        String taskName = " tn/" + task.getTaskName();

        // valid moduleCode and taskName
        ProgressCommand expectedProgressCommand =
                new ProgressCommand(amy.getModuleCode(), task);

        // moduleCode followed by taskName (no whitespace before moduleCode)
        assertParseSuccess(parser, MODULE_CODE_DESC_AMY + taskName,
                expectedProgressCommand);

        // moduleCode followed by taskName (whitespace before moduleCode)
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + MODULE_CODE_DESC_AMY + taskName,
                expectedProgressCommand);

        // taskName followed by moduleCode (no whitespace before taskName)
        assertParseSuccess(parser, taskName + MODULE_CODE_DESC_AMY,
                expectedProgressCommand);

        // taskName followed by moduleCode (whitespace before taskName)
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + taskName + MODULE_CODE_DESC_AMY,
                expectedProgressCommand);
    }

    @Test
    public void parse_invalidFields_throwsParseException() {
        // invalid moduleCode
        assertParseFailure(parser, PREAMBLE_WHITESPACE + INVALID_MODULE_CODE_DESC + TASK_NAME_DESC,
                ModuleCode.MESSAGE_CONSTRAINTS);
        // invalid taskName
        assertParseFailure(parser, PREAMBLE_WHITESPACE + MODULE_CODE_DESC_AMY + INVALID_TASKNAME_DESC,
                Task.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_missingFields_throwsParseException() {
        // missing moduleCode
        assertParseFailure(parser, PREAMBLE_WHITESPACE + TASK_NAME_DESC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ProgressCommand.MESSAGE_USAGE));
        // missing taskName
        assertParseFailure(parser, PREAMBLE_WHITESPACE + MODULE_CODE_DESC_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ProgressCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_nonSupportedFields_throwsParseException() {
        // student ID
        assertParseFailure(parser, PREAMBLE_WHITESPACE + ID_DESC_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ProgressCommand.MESSAGE_USAGE));

        // name
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ProgressCommand.MESSAGE_USAGE));

        // telegram handle
        assertParseFailure(parser, PREAMBLE_WHITESPACE + TELEGRAM_HANDLE_DESC_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ProgressCommand.MESSAGE_USAGE));

        // phone number
        assertParseFailure(parser, PREAMBLE_WHITESPACE + PHONE_DESC_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ProgressCommand.MESSAGE_USAGE));

        // email
        assertParseFailure(parser, PREAMBLE_WHITESPACE + EMAIL_DESC_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ProgressCommand.MESSAGE_USAGE));
    }
}
