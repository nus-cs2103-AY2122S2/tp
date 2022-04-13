package seedu.tinner.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.tinner.model.reminder.Reminder;

/**
 * A UI component that displays information of a {@code Reminder}.
 */
public class ReminderCard extends UiPart<Region> {

    private static final String FXML = "ReminderCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on CompanyList level 4</a>
     */

    public final Reminder reminder;

    @FXML
    private HBox cardPane;
    @FXML
    private Label companyName;
    @FXML
    private Label roleName;
    @FXML
    private Label status;
    @FXML
    private Label reminderDate;

    /**
     * Creates a {@code ReminderCard} with the given {@code Reminder}.
     */
    public ReminderCard(Reminder reminder) {
        super(FXML);
        this.reminder = reminder;

        String companyNameField = reminder.getCompanyName().value;
        String roleNameField = reminder.getRoleName().value;
        String statusField = reminder.getStatus().value;
        String reminderDateField = reminder.getReminderDate().toString();

        companyName.setText(companyNameField);
        roleName.setText(roleNameField);
        status.setText(statusField);
        reminderDate.setText(reminderDateField);

        companyName.setManaged(!companyNameField.isEmpty());
        roleName.setManaged(!roleNameField.isEmpty());
        status.setManaged(!statusField.isEmpty());
        reminderDate.setManaged(!reminderDateField.isEmpty());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ReminderCard)) {
            return false;
        }

        // state check
        ReminderCard card = (ReminderCard) other;
        return reminder.equals(card.reminder);
    }
}
