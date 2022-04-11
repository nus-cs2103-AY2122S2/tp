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
    public void execute_helpEdit_success() {
        CommandResult expectedCommandResult = new CommandResult(EditCommand.MESSAGE_USAGE);
        assertCommandSuccess(new HelpCommand("edit"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_helpAdd_success() {
        CommandResult expectedCommandResult = new CommandResult(AddCommand.MESSAGE_USAGE);
        assertCommandSuccess(new HelpCommand("add"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_helpDelete_success() {
        CommandResult expectedCommandResult = new CommandResult(DeleteCommand.MESSAGE_USAGE);
        assertCommandSuccess(new HelpCommand("delete"), model, expectedCommandResult, expectedModel);
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
    public void execute_helpPerson_success() {
        CommandResult expectedCommandResult = new CommandResult(PersonOwesCommand.MESSAGE_USAGE);
        assertCommandSuccess(new HelpCommand("person"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_helpPaid_success() {
        CommandResult expectedCommandResult = new CommandResult(PaidPersonWhoOwedCommand.MESSAGE_USAGE);
        assertCommandSuccess(new HelpCommand("paid"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_helpListCat_success() {
        CommandResult expectedCommandResult = new CommandResult(ListCatCommand.MESSAGE_USAGE);
        assertCommandSuccess(new HelpCommand("listCat"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_helpUpdate_success() {
        CommandResult expectedCommandResult = new CommandResult(EditPersonCommand.MESSAGE_USAGE);
        assertCommandSuccess(new HelpCommand("update"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_helpFilter_success() {
        CommandResult expectedCommandResult = new CommandResult(FilterCommand.MESSAGE_USAGE);
        assertCommandSuccess(new HelpCommand("filter"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_helpClear_success() {
        CommandResult expectedCommandResult = new CommandResult(ClearCommand.MESSAGE_USAGE);
        assertCommandSuccess(new HelpCommand("clear"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_helpAddCat_success() {
        CommandResult expectedCommandResult = new CommandResult(AddCategoryCommand.MESSAGE_USAGE);
        assertCommandSuccess(new HelpCommand("addCat"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_helpHelp_success() {
        CommandResult expectedCommandResult = new CommandResult(HelpCommand.MESSAGE_USAGE);
        assertCommandSuccess(new HelpCommand("help"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_helpSetBudget_success() {
        CommandResult expectedCommandResult = new CommandResult(SetBudgetCommand.MESSAGE_USAGE);
        assertCommandSuccess(new HelpCommand("budget"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_helpUnknownCommand_commandExceptionThrown() {
        assertCommandFailure(new HelpCommand("unknssdf"), model, HelpCommand.INVALID_COMMAND_USAGE);
    }
}
