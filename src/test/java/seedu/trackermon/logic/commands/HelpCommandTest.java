package seedu.trackermon.logic.commands;

import static seedu.trackermon.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.trackermon.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.trackermon.model.Model;
import seedu.trackermon.model.ModelManager;

/**
 * Contains integration tests (interaction with the Model) for {@code HelpCommand}.
 */
public class HelpCommandTest {
    private final Model model = new ModelManager();
    private final Model expectedModel = new ModelManager();

    /**
     * Tests the showing of the help command from the execution of {@code HelpCommand}.
     */
    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false);
        assertCommandSuccess(new HelpCommand(), model, expectedCommandResult, expectedModel);
    }
}
