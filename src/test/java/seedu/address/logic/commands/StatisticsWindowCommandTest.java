package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.StatisticsWindowCommand.MESSAGE_STATISTICS_WINDOW_SUCCESS;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class StatisticsWindowCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_statistics_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_STATISTICS_WINDOW_SUCCESS,
                false, false, false, true, false);
        assertCommandSuccess(new StatisticsWindowCommand(), model, expectedCommandResult, expectedModel);
    }
}
