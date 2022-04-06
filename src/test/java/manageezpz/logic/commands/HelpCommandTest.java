package manageezpz.logic.commands;

import static manageezpz.logic.commands.CommandTestUtil.assertCommandSuccess;
import static manageezpz.logic.commands.HelpCommand.MESSAGE_SHOWING_HELP;

import org.junit.jupiter.api.Test;

import manageezpz.model.Model;
import manageezpz.model.ModelManager;

public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_SHOWING_HELP, true, false);
        assertCommandSuccess(new HelpCommand(), model, expectedCommandResult, expectedModel);
    }
}
