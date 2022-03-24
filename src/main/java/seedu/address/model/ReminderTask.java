package seedu.address.model;

import java.util.TimerTask;

import javafx.application.Platform;
import seedu.address.ui.ReminderWindow;

/**
 * A class to launch the reminders.
 */
public class ReminderTask extends TimerTask {
    @Override
    public void run() {
        Platform.runLater(() -> {
            showReminders();
        });
    }

    private void showReminders() {
        ReminderWindow reminderWindow = ReminderWindow.getInstance();
        if (!reminderWindow.isShowing()) {
            reminderWindow.show();
        } else {
            reminderWindow.focus();
        }
    }
}
