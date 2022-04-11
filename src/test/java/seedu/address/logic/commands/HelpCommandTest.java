package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false);
        assertCommandSuccess(new HelpCommand(""), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_helpFind_success() {
        CommandResult expectedCommandResult = new CommandResult(FindCommand.MESSAGE_USAGE);
        assertCommandSuccess(new HelpCommand("find"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_helpAdd_success() {
        CommandResult expectedCommandResult = new CommandResult(AddCommand.MESSAGE_USAGE);
        assertCommandSuccess(new HelpCommand("add"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_helpEdit_success() {
        CommandResult expectedCommandResult = new CommandResult(EditCommand.MESSAGE_USAGE);
        assertCommandSuccess(new HelpCommand("edit"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_helpDelete_success() {
        CommandResult expectedCommandResult = new CommandResult(DeleteCommand.MESSAGE_USAGE);
        assertCommandSuccess(new HelpCommand("delete"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_helpClear_success() {
        CommandResult expectedCommandResult = new CommandResult(ClearCommand.MESSAGE_USAGE);
        assertCommandSuccess(new HelpCommand("clear"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_helpList_success() {
        CommandResult expectedCommandResult = new CommandResult(ListCommand.MESSAGE_USAGE);
        assertCommandSuccess(new HelpCommand("list"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_helpExit_success() {
        CommandResult expectedCommandResult = new CommandResult(ExitCommand.MESSAGE_USAGE);
        assertCommandSuccess(new HelpCommand("exit"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_helpHelp_success() {
        CommandResult expectedCommandResult = new CommandResult(HelpCommand.MESSAGE_USAGE);
        assertCommandSuccess(new HelpCommand("help"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_helpSetDefaultGroup_success() {
        CommandResult expectedCommandResult = new CommandResult(SetDefaultGroupCommand.MESSAGE_USAGE);
        assertCommandSuccess(new HelpCommand("set-default-group"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_helpMailIndex_success() {
        CommandResult expectedCommandResult = new CommandResult(MailIndexCommand.MESSAGE_USAGE);
        assertCommandSuccess(new HelpCommand("mail-index"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_helpMailX_success() {
        CommandResult expectedCommandResult = new CommandResult(MailXCommand.MESSAGE_USAGE);
        assertCommandSuccess(new HelpCommand("mail-x"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_helpMailAll_success() {
        CommandResult expectedCommandResult = new CommandResult(MailAllCommand.MESSAGE_USAGE);
        assertCommandSuccess(new HelpCommand("mail-all"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_helpUndo_success() {
        CommandResult expectedCommandResult = new CommandResult(UndoCommand.MESSAGE_USAGE);
        assertCommandSuccess(new HelpCommand("undo"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_helpRedo_success() {
        CommandResult expectedCommandResult = new CommandResult(RedoCommand.MESSAGE_USAGE);
        assertCommandSuccess(new HelpCommand("redo"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_helpNewTask_success() {
        CommandResult expectedCommandResult = new CommandResult(NewTaskCommand.MESSAGE_USAGE);
        assertCommandSuccess(new HelpCommand("newtask"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_helpRemoveTask_success() {
        CommandResult expectedCommandResult = new CommandResult(RemoveTaskCommand.MESSAGE_USAGE);
        assertCommandSuccess(new HelpCommand("deltask"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_helpUnknownCommand_commandExceptionThrown() {
        assertCommandFailure(new HelpCommand("unknoowwssdf"), model, HelpCommand.INVALID_COMMAND_USAGE);
    }

}
