package seedu.address.ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.tasks.Task;

public class TaskCard extends UiPart<Region> {

    private static final String FXML = "TaskListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Task task;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label header;
    @FXML
    private Label information;
    @FXML
    private Label date;
    @FXML
    private Label time;

    /**
     * Creates a {@code TaskCode} with the given {@code Task} and index to display.
     */
    public TaskCard(Task task, int displayedIndex) {
        super(FXML);
        this.task = task;
        id.setText(displayedIndex + ". ");
        header.setText(task.getHeader().fullHeader);
        information.setText(task.getInformation().fullInformation);
        String formattedDate = formatDate(task);
        date.setText("Date: " + formattedDate);
        String formattedTime = formatTime(task);
        time.setText("Time: " + formattedTime);
    }

    /**
     * Format date to display in correct format
     *
     * @param task task
     * @return date in correct format
     */
    public String formatDate(Task task) {
        String[] dateSplit = task.getDate().value.split("-");
        LocalDate parsedDate = LocalDate.parse(task.getDate().value);
        String formattedMonth = parsedDate.format(DateTimeFormatter.ofPattern("MMM"));
        String formattedDate = dateSplit[2] + " " + formattedMonth + " " + dateSplit[0];
        return formattedDate;
    }

    /**
     * Format time to display in correct format
     *
     * @param task task
     * @return time in correct format
     */
    public String formatTime(Task task) {
        String[] timeSplit = task.getTime().value.split(":");
        LocalTime parsedTime = LocalTime.parse(timeSplit[0] + timeSplit[1], DateTimeFormatter.ofPattern("HHmm"));
        String formattedTime = parsedTime.format(DateTimeFormatter.ofPattern("hh:mma"));
        return formattedTime;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof TaskCard)) {
            return false;
        }

        // state check
        TaskCard card = (TaskCard) other;
        return id.getText().equals(card.id.getText())
                && task.equals(card.task);
    }
}
