package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.INVALID_COMMAND_NAME;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.ManualMessages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class ManualCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_manual_all() {
        CommandResult expectedCommandResult = new CommandResult(ManualMessages.MANUAL_MESSAGE_ALL_COMMANDS);
        assertCommandSuccess(new ManualCommand(""), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_manual_add() {
        CommandResult expectedCommandResult = new CommandResult(ManualMessages.MANUAL_MESSAGE_ADD_COMMAND);
        assertCommandSuccess(new ManualCommand("add"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_manual_delete() {
        CommandResult expectedCommandResult = new CommandResult(ManualMessages.MANUAL_MESSAGE_DELETE_COMMAND);
        assertCommandSuccess(new ManualCommand("delete"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_manual_find() {
        CommandResult expectedCommandResult = new CommandResult(ManualMessages.MANUAL_MESSAGE_FIND_COMMAND);
        assertCommandSuccess(new ManualCommand("find"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_manual_edit() {
        CommandResult expectedCommandResult = new CommandResult(ManualMessages.MANUAL_MESSAGE_EDIT_COMMAND);
        assertCommandSuccess(new ManualCommand("edit"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_manual_clear() {
        CommandResult expectedCommandResult = new CommandResult(ManualMessages.MANUAL_MESSAGE_CLEAR_COMMAND);
        assertCommandSuccess(new ManualCommand("clear"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_manual_list() {
        CommandResult expectedCommandResult = new CommandResult(ManualMessages.MANUAL_MESSAGE_LIST_COMMAND);
        assertCommandSuccess(new ManualCommand("list"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_manual_manual() {
        CommandResult expectedCommandResult = new CommandResult(ManualMessages.MANUAL_MESSAGE_MANUAL_COMMAND);
        assertCommandSuccess(new ManualCommand("manual"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_manual_help() {
        CommandResult expectedCommandResult = new CommandResult(ManualMessages.MANUAL_MESSAGE_HELP_COMMAND);
        assertCommandSuccess(new ManualCommand("help"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_manual_exit() {
        CommandResult expectedCommandResult = new CommandResult(ManualMessages.MANUAL_MESSAGE_EXIT_COMMAND);
        assertCommandSuccess(new ManualCommand("exit"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_manual_task() {
        CommandResult expectedCommandResult = new CommandResult(ManualMessages.MANUAL_MESSAGE_TASK_COMMAND);
        assertCommandSuccess(new ManualCommand("task"), model, expectedCommandResult, expectedModel);
    }
    @Test
    public void execute_manual_mark() {
        CommandResult expectedCommandResult = new CommandResult(ManualMessages.MANUAL_MESSAGE_MARK_COMMAND);
        assertCommandSuccess(new ManualCommand("mark"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_manual_unmark() {
        CommandResult expectedCommandResult = new CommandResult(ManualMessages.MANUAL_MESSAGE_UNMARK_COMMAND);
        assertCommandSuccess(new ManualCommand("unmark"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_manual_archive() {
        CommandResult expectedCommandResult = new CommandResult(ManualMessages.MANUAL_MESSAGE_ARCHIVE_COMMAND);
        assertCommandSuccess(new ManualCommand("archive"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_manual_assign() {
        CommandResult expectedCommandResult = new CommandResult(ManualMessages.MANUAL_MESSAGE_ASSIGN_COMMAND);
        assertCommandSuccess(new ManualCommand("assign"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_manual_invalid() {
        ManualCommand manualCommand = new ManualCommand(INVALID_COMMAND_NAME);
        CommandResult expectedCommandResult = new CommandResult(ManualMessages.MANUAL_MESSAGE_UNKNOWN_COMMANDS);
        assertCommandFailure(manualCommand, model, ManualMessages.MANUAL_MESSAGE_UNKNOWN_COMMANDS);
    }
}
