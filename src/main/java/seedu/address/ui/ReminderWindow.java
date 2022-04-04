package seedu.address.ui;

import seedu.address.logic.Logic;
import seedu.address.storage.ReminderPersons;

/**
 * The Reminder Window provides the basic applications layout containing a menu
 * bar and space to display clients marked for reminders in the application.
 */
public class ReminderWindow extends SingleColumnPersonListWindow {

    /**
     * Sets up Logic instance in ReminderWindow
     */
    public ReminderWindow(Logic logic) {
        super(logic);
    }

    /**
     * Fills up all the placeholders of this window.
     */
    @Override
    void fillInnerParts() {
        if (ReminderPersons.getInstance().isEmpty()) {
            reminderStatus.setVisible(true);
            reminderStatus.setManaged(true);
            reminderStatus.setText("No Reminders set!");
        } else {
            reminderStatus.setVisible(false);
            reminderStatus.setManaged(false);
        }
        personListPanel = new PersonListPanel(logic.getReminderPersonList());
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());
        setTitle("Reminders");
    }
}
