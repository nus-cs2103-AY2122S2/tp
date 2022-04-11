package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandResult.SpecialCommandResult.SHOW_HELP;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccessInDetailedViewMode;
import static seedu.address.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, SHOW_HELP);
        assertCommandSuccess(new HelpCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void executeInDetailedView_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, SHOW_HELP);
        assertCommandSuccessInDetailedViewMode(new HelpCommand(), model, expectedCommandResult, expectedModel);
    }
}
