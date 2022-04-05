package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ID_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_CODE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TASK_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AssignCommand;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.Task;
import seedu.address.testutil.TypicalPersons;

public class AssignCommandParserTest {

    private static final String ASSIGN_MESSAGE_INVALID_COMMAND_FORMAT = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            AssignCommand.MESSAGE_USAGE);

    private final StudentId studentIdBob = TypicalPersons.BOB.getStudentId();

    private final ModuleCode moduleCodeBob = TypicalPersons.BOB.getModuleCode();

    private final Task validTask = new Task("Cry");
    private final String validTaskDesc = " tn/" + validTask.getTaskName();

    private AssignCommandParser parser = new AssignCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "   ", ASSIGN_MESSAGE_INVALID_COMMAND_FORMAT);
    }

    @Test
    public void parse_missingStudentIdPrefix_throwsParseException() {
        String userInputMissingStudentIdPrefix = VALID_ID_BOB + TASK_DESC_BOB;
        assertParseFailure(parser, userInputMissingStudentIdPrefix, ASSIGN_MESSAGE_INVALID_COMMAND_FORMAT);
    }

    @Test
    public void parse_missingModuleCodePrefix_throwsParseException() {
        String userInputMissingModuleCodePrefix = VALID_MODULE_CODE_BOB + TASK_DESC_BOB;
        assertParseFailure(parser, userInputMissingModuleCodePrefix, ASSIGN_MESSAGE_INVALID_COMMAND_FORMAT);
    }

    @Test
    public void parse_missingTaskPrefixByStudentId_throwsParseException() {
        String userInputMissingTaskPrefix = ID_DESC_BOB + VALID_TASK_BOB;
        assertParseFailure(parser, userInputMissingTaskPrefix, ASSIGN_MESSAGE_INVALID_COMMAND_FORMAT);
    }

    @Test
    public void parse_missingTaskPrefixByModuleCode_throwsParseException() {
        String userInputMissingTaskPrefix = MODULE_CODE_DESC_BOB + VALID_TASK_BOB;
        assertParseFailure(parser, userInputMissingTaskPrefix, ASSIGN_MESSAGE_INVALID_COMMAND_FORMAT);
    }

    @Test
    public void parse_missingStudentIdAndTaskPrefix_throwsParseException() {
        String userInputMissingStudentIdAndTaskPrefix = VALID_ID_BOB + VALID_TASK_BOB;
        assertParseFailure(parser, userInputMissingStudentIdAndTaskPrefix, ASSIGN_MESSAGE_INVALID_COMMAND_FORMAT);
    }

    @Test
    public void parse_missingTaskPrefixByName_throwsParseException() {
        String userInputMissingTaskPrefix = NAME_DESC_BOB + TASK_DESC_BOB;
        assertParseFailure(parser, userInputMissingTaskPrefix, ASSIGN_MESSAGE_INVALID_COMMAND_FORMAT);
    }

    @Test
    public void parse_invalidStudentId_throwsParseException() {
        String userInputInvalidStudentId = INVALID_ID_DESC + TASK_DESC_BOB;
        assertParseFailure(parser, userInputInvalidStudentId, StudentId.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidModuleCode_throwsParseException() {
        String userInputInvalidModuleCode = INVALID_MODULE_CODE_DESC + TASK_DESC_BOB;
        assertParseFailure(parser, userInputInvalidModuleCode, ModuleCode.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidTaskByStudentId_throwsParseException() {
        String userInputInvalidTask = ID_DESC_BOB + INVALID_TASK_DESC;
        assertParseFailure(parser, userInputInvalidTask, Task.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidTaskByModuleCode_throwsParseException() {
        String userInputInvalidTask = MODULE_CODE_DESC_BOB + INVALID_TASK_DESC;
        assertParseFailure(parser, userInputInvalidTask, Task.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsPresentByStudentId_success() {
        AssignCommand expectedAssignCommand = new AssignCommand(studentIdBob, validTask);
        String userInputValid = ID_DESC_BOB + validTaskDesc;
        assertParseSuccess(parser, userInputValid, expectedAssignCommand);
    }

    @Test
    public void parse_allFieldsPresentByModuleCode_success() {
        AssignCommand expectedAssignCommand = new AssignCommand(moduleCodeBob, validTask);
        String userInputValid = MODULE_CODE_DESC_BOB + validTaskDesc;
        assertParseSuccess(parser, userInputValid, expectedAssignCommand);
    }
}
