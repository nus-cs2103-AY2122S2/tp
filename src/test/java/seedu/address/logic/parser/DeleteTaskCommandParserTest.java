package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ID_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INDEX_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_CODE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TASK_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteTaskCommand;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.Task;
import seedu.address.testutil.TypicalPersons;

public class DeleteTaskCommandParserTest {

    private static final String DELETE_TASK_MSG_INVALID_COMMAND_FORMAT = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            DeleteTaskCommand.MESSAGE_USAGE);

    private final StudentId studentIdBob = TypicalPersons.BOB.getStudentId();

    private final ModuleCode moduleCodeBob = TypicalPersons.BOB.getModuleCode();

    private final Task validTask = new Task("Cry");
    private final String validTaskDesc = " tn/" + validTask.getTaskName();

    private final Index validIndex = Index.fromOneBased(Integer.valueOf(VALID_INDEX));

    private DeleteTaskCommandParser parser = new DeleteTaskCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "   ", DELETE_TASK_MSG_INVALID_COMMAND_FORMAT);
    }

    @Test
    public void parse_missingStudentIdPrefix_throwsParseException() {
        String userInputMissingStudentIdPrefix = VALID_ID_BOB + INDEX_DESC;
        assertParseFailure(parser, userInputMissingStudentIdPrefix, DELETE_TASK_MSG_INVALID_COMMAND_FORMAT);
    }

    @Test
    public void parse_missingModuleCodePrefix_throwsParseException() {
        String userInputMissingModuleCodePrefix = VALID_MODULE_CODE_BOB + TASK_DESC_BOB;
        assertParseFailure(parser, userInputMissingModuleCodePrefix, DELETE_TASK_MSG_INVALID_COMMAND_FORMAT);
    }

    @Test
    public void parse_missingIndexPrefixByStudentId_throwsParseException() {
        String userInputMissingTaskPrefix = ID_DESC_BOB + VALID_INDEX;
        assertParseFailure(parser, userInputMissingTaskPrefix, DELETE_TASK_MSG_INVALID_COMMAND_FORMAT);
    }

    @Test
    public void parse_missingTaskPrefixByModuleCode_throwsParseException() {
        String userInputMissingTaskPrefix = VALID_MODULE_CODE_BOB + VALID_TASK_BOB;
        assertParseFailure(parser, userInputMissingTaskPrefix, DELETE_TASK_MSG_INVALID_COMMAND_FORMAT);
    }

    @Test
    public void parse_missingStudentIdAndIndexPrefix_throwsParseException() {
        String userInputMissingStudentIdAndTaskPrefix = VALID_ID_BOB + VALID_INDEX;
        assertParseFailure(parser, userInputMissingStudentIdAndTaskPrefix, DELETE_TASK_MSG_INVALID_COMMAND_FORMAT);
    }

    @Test
    public void parse_missingModuleCodeAndTaskNamePrefix_throwsParseException() {
        String userInputMissingStudentIdAndTaskPrefix = VALID_ID_BOB + VALID_TASK_BOB;
        assertParseFailure(parser, userInputMissingStudentIdAndTaskPrefix, DELETE_TASK_MSG_INVALID_COMMAND_FORMAT);
    }

    @Test
    public void parse_invalidStudentId_throwsParseException() {
        String userInputInvalidStudentId = INVALID_ID_DESC + TASK_DESC_BOB;
        assertParseFailure(parser, userInputInvalidStudentId, DELETE_TASK_MSG_INVALID_COMMAND_FORMAT);
    }

    @Test
    public void parse_invalidModuleCode_throwsParseException() {
        String userInputInvalidModuleCode = INVALID_MODULE_CODE_DESC + TASK_DESC_BOB;
        assertParseFailure(parser, userInputInvalidModuleCode, DELETE_TASK_MSG_INVALID_COMMAND_FORMAT);
    }

    @Test
    public void parse_invalidTaskByStudentId_throwsParseException() {
        String userInputInvalidTask = ID_DESC_BOB + INVALID_TASK_DESC;
        assertParseFailure(parser, userInputInvalidTask, DELETE_TASK_MSG_INVALID_COMMAND_FORMAT);
    }

    @Test
    public void parse_invalidTaskByModuleCode_throwsParseException() {
        String userInputInvalidTask = MODULE_CODE_DESC_BOB + INVALID_TASK_DESC;
        assertParseFailure(parser, userInputInvalidTask, DELETE_TASK_MSG_INVALID_COMMAND_FORMAT);
    }

    @Test
    public void parse_allFieldsPresentByStudentIdAndIndex_success() {
        DeleteTaskCommand expectedDeleteCommand = new DeleteTaskCommand(studentIdBob, validIndex);
        String userInputValid = ID_DESC_BOB + INDEX_DESC;
        assertParseSuccess(parser, userInputValid, expectedDeleteCommand);
    }

    @Test
    public void parse_allFieldsPresentByModuleCodeAndTaskName_success() {
        DeleteTaskCommand expectedDeleteTaskCommand = new DeleteTaskCommand(moduleCodeBob, validTask);
        String userInputValid = MODULE_CODE_DESC_BOB + validTaskDesc;
        assertParseSuccess(parser, userInputValid, expectedDeleteTaskCommand);
    }

}
