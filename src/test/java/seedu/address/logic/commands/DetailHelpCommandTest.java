package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.HelpArgument;
import seedu.address.logic.commands.help.DetailHelpCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;


public class DetailHelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_addHelp_success() {
        CommandResult expectedCommandResult = new CommandResult(HelpArgument.ADD_COMMAND_DESCRIPTION,
                null, true, false);
        assertCommandSuccess(new DetailHelpCommand(new HelpArgument("add")), model,
                expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_deleteHelp_success() {
        CommandResult expectedCommandResult = new CommandResult(HelpArgument.DELETE_COMMAND_DESCRIPTION,
                null, true, false);
        assertCommandSuccess(new DetailHelpCommand(new HelpArgument("delete")), model,
                expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_editHelp_success() {
        CommandResult expectedCommandResult = new CommandResult(HelpArgument.EDIT_COMMAND_DESCRIPTION,
                null, true, false);
        assertCommandSuccess(new DetailHelpCommand(new HelpArgument("edit")), model,
                expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_listHelp_success() {
        CommandResult expectedCommandResult = new CommandResult(HelpArgument.LIST_COMMAND_DESCRIPTION,
                null, true, false);
        assertCommandSuccess(new DetailHelpCommand(new HelpArgument("list")), model,
                expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_exitHelp_success() {
        CommandResult expectedCommandResult = new CommandResult(HelpArgument.EXIT_COMMAND_DESCRIPTION,
                null, true, false);
        assertCommandSuccess(new DetailHelpCommand(new HelpArgument("exit")), model,
                expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_overallHelp_success() {
        CommandResult expectedCommandResult = new CommandResult(HelpArgument.OVERALL_HELPING_DESCRIPTION,
                null, true, false);
        assertCommandSuccess(new DetailHelpCommand(new HelpArgument("")), model, expectedCommandResult, expectedModel);
    }

    //    @Test
    //    public void execute_invalidHelpCommand_throwsCommandException() {
    //        assertCommandFailure(new DetailHelpCommand("wrong command somehow"), model,
    //                HelpArgument.COMMAND_NOT_FOUND_DESCRIPTION);
    //    }
}
