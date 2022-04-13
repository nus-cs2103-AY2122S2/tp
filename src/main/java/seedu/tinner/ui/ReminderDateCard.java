package seedu.tinner.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.tinner.model.reminder.Reminder;
import seedu.tinner.model.reminder.UniqueReminderList;

/**
 * An UI component that displays information of a {@code Company}.
 */
public class ReminderDateCard extends UiPart<Region> {

    private static final String FXML = "ReminderDateCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on CompanyList level 4</a>
     */

    public final LocalDate reminderDate;

    @FXML
    private HBox cardPane;
    @FXML
    private Label date;
    @FXML
    private StackPane reminderListPanelPlaceholder;

    /**
     * Creates a {@code CompanyCard} with the given {@code Company} and index to display.
     */
    public ReminderDateCard(LocalDate reminderDate) {
        super(FXML);
        this.reminderDate = reminderDate;
        date.setText(reminderDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));

        UniqueReminderList reminderList = UniqueReminderList.getInstance();
        ObservableList<Reminder> dateSpecificReminders = reminderList.getDateSpecificReminders(reminderDate);
        reminderListPanelPlaceholder.setPrefHeight(100 * dateSpecificReminders.size());

        setReminderListPanelPlaceholder(dateSpecificReminders);
    }

    public void setReminderListPanelPlaceholder(ObservableList<Reminder> dateSpecificReminders) {
        ReminderListPanel reminderDateListPanel = new ReminderListPanel(dateSpecificReminders);
        reminderListPanelPlaceholder.getChildren().add(reminderDateListPanel.getRoot());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ReminderDateCard)) {
            return false;
        }

        // state check
        ReminderDateCard card = (ReminderDateCard) other;
        return date.getText().equals(card.date.getText());
    }
}
