package seedu.unite.logic.commands;

import static seedu.unite.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.unite.logic.commands.ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;

import org.junit.jupiter.api.Test;

import seedu.unite.model.Model;
import seedu.unite.model.ModelManager;

public class ExitCommandTest {
    private final Model model = new ModelManager();
    private final Model expectedModel = new ModelManager();

    @Test
    public void execute_exit_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
        assertCommandSuccess(new ExitCommand(), model, expectedCommandResult, expectedModel);
    }
}
