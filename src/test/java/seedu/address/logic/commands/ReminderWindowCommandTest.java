package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ReminderWindowCommand.MESSAGE_REMINDERS_WINDOW_SUCCESS;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

class ReminderWindowCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_reminderWindow_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_REMINDERS_WINDOW_SUCCESS,
                false, false, false, false, false, true, false);
        assertCommandSuccess(new ReminderWindowCommand(), model, expectedCommandResult, expectedModel);
    }
}
