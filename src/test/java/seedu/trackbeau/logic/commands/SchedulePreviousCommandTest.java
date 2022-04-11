package seedu.trackbeau.logic.commands;

import static seedu.trackbeau.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.trackbeau.logic.commands.SchedulePreviousCommand.MESSAGE_SUCCESS;

import org.junit.jupiter.api.Test;

import seedu.trackbeau.model.Model;
import seedu.trackbeau.model.ModelManager;
import seedu.trackbeau.ui.Panel;


public class SchedulePreviousCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_exit_success() {
        expectedModel.setSelectedDate(expectedModel.getSelectedDate().minusDays(7));
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_SUCCESS, Panel.SCHEDULE_PANEL);
        assertCommandSuccess(new SchedulePreviousCommand(), model, expectedCommandResult, expectedModel);
    }
}
