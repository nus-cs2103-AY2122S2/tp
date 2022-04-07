package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.HelpArgument;
import seedu.address.logic.commands.help.DetailHelpCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;


public class DetailHelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void construct_nullArgument_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DetailHelpCommand(null));
    }

    @Test
    public void execute_addHelp_success() {
        CommandResult expectedCommandResult = new CommandResult(HelpArgument.ADD_COMMAND_DESCRIPTION,
                null, true, false);
        HelpArgument validArgument = new HelpArgument("add");
        assertCommandSuccess(new DetailHelpCommand(validArgument), model,
                expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_deleteHelp_success() {
        CommandResult expectedCommandResult = new CommandResult(HelpArgument.DELETE_COMMAND_DESCRIPTION,
                null, true, false);
        HelpArgument validArgument = new HelpArgument("delete");
        assertCommandSuccess(new DetailHelpCommand(validArgument), model,
                expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_editHelp_success() {
        CommandResult expectedCommandResult = new CommandResult(HelpArgument.EDIT_COMMAND_DESCRIPTION,
                null, true, false);
        HelpArgument validArgument = new HelpArgument("edit");
        assertCommandSuccess(new DetailHelpCommand(validArgument), model,
                expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_listHelp_success() {
        CommandResult expectedCommandResult = new CommandResult(HelpArgument.LIST_COMMAND_DESCRIPTION,
                null, true, false);
        HelpArgument validArgument = new HelpArgument("list");
        assertCommandSuccess(new DetailHelpCommand(validArgument), model,
                expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_passHelp_success() {
        CommandResult expectedCommandResult = new CommandResult(HelpArgument.PASS_COMMAND_DESCRIPTION,
                null, true, false);
        HelpArgument validArgument = new HelpArgument("pass");
        assertCommandSuccess(new DetailHelpCommand(validArgument), model,
                expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_failHelp_success() {
        CommandResult expectedCommandResult = new CommandResult(HelpArgument.FAIL_COMMAND_DESCRIPTION,
                null, true, false);
        HelpArgument validArgument = new HelpArgument("fail");
        assertCommandSuccess(new DetailHelpCommand(validArgument), model,
                expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_acceptHelp_success() {
        CommandResult expectedCommandResult = new CommandResult(HelpArgument.ACCEPT_COMMAND_DESCRIPTION,
                null, true, false);
        HelpArgument validArgument = new HelpArgument("accept");
        assertCommandSuccess(new DetailHelpCommand(validArgument), model,
                expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_rejectHelp_success() {
        CommandResult expectedCommandResult = new CommandResult(HelpArgument.REJECT_COMMAND_DESCRIPTION,
                null, true, false);
        HelpArgument validArgument = new HelpArgument("reject");
        assertCommandSuccess(new DetailHelpCommand(validArgument), model,
                expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_exportHelp_success() {
        CommandResult expectedCommandResult = new CommandResult(HelpArgument.EXPORT_COMMAND_DESCRIPTION,
                null, true, false);
        HelpArgument validArgument = new HelpArgument("export");
        assertCommandSuccess(new DetailHelpCommand(validArgument), model,
                expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_exitHelp_success() {
        CommandResult expectedCommandResult = new CommandResult(HelpArgument.EXIT_COMMAND_DESCRIPTION,
                null, true, false);
        HelpArgument validArgument = new HelpArgument("exit");
        assertCommandSuccess(new DetailHelpCommand(validArgument), model,
                expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_overallHelp_success() {
        CommandResult expectedCommandResult = new CommandResult(HelpArgument.OVERALL_HELPING_DESCRIPTION,
                null, true, false);
        HelpArgument validArgument = new HelpArgument("");
        assertCommandSuccess(new DetailHelpCommand(validArgument), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_nullModel_throwsCommandException() {
        assertThrows(NullPointerException.class, () -> new DetailHelpCommand(new HelpArgument("")).execute(null));
    }
}
