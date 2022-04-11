package seedu.address.ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.interview.Interview;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Interview}.
 */
public class InterviewCard extends UiPart<Region> {

    private static final String FXML = "InterviewListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Interview interview;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label date;
    @FXML
    private Label time;
    @FXML
    private Label job;
    @FXML
    private Label stage;
    @FXML
    private Label phone;
    @FXML
    private Label email;

    /**
     * Creates a {@code InterviewCode} with the given {@code Interview} and index to display.
     */
    public InterviewCard(Interview interview, int displayedIndex) {
        super(FXML);
        this.interview = interview;
        id.setText(displayedIndex + ". ");
        Person person = interview.getPerson();
        name.setText(person.getName().fullName);
        String formattedDate = formatDate(interview);
        date.setText(formattedDate);
        String formattedTime = formatTime(interview);
        time.setText("@ " + formattedTime);
        job.setText(person.getJob().jobTitle);
        stage.setText(person.getStage().value);
        editStageStyle(stage, person);
        phone.setText(person.getPhone().value);
        email.setText(person.getEmail().value);
    }

    /**
     * Format date to display in the correct format
     *
     * @param interview interview
     * @return date in correct format
     */
    public String formatDate(Interview interview) {
        String[] dateSplit = interview.getDate().value.split("-");
        LocalDate parsedDate = LocalDate.parse(interview.getDate().value);
        String formattedMonth = parsedDate.format(DateTimeFormatter.ofPattern("MMM"));
        String formattedDate = dateSplit[2] + " " + formattedMonth + " " + dateSplit[0];
        return formattedDate;
    }

    /**
     * Format time to display in correct format
     *
     * @param interview interview
     * @return time in correct format
     */
    public String formatTime(Interview interview) {
        String[] timeSplit = interview.getTime().value.split(":");
        LocalTime parsedTime = LocalTime.parse(timeSplit[0] + timeSplit[1], DateTimeFormatter.ofPattern("HHmm"));
        String formattedTime = parsedTime.format(DateTimeFormatter.ofPattern("hh:mma"));
        return formattedTime;
    }

    /**
     * Edit style of stage
     *
     * @param label stage label
     * @param person applicant
     */
    public void editStageStyle(Label label, Person person) {
        if (person.getStage().value.equals("INPROGRESS")) {
            stage.setStyle("-fx-text-fill: white; -fx-background-color: #d2691e; -fx-padding: 1 3 1 3; "
                    + "-fx-border-radius: 2; -fx-background-radius: 2; -fx-font-size: 11;");
        } else if (person.getStage().value.equals("ACCEPTED")) {
            stage.setStyle("-fx-text-fill: white; -fx-background-color: #228b22; -fx-padding: 1 3 1 3; "
                    + "-fx-border-radius: 2; -fx-background-radius: 2; -fx-font-size: 11;");
        } else if (person.getStage().value.equals("REJECTED")) {
            stage.setStyle("-fx-text-fill: white; -fx-background-color: #b22222; -fx-padding: 1 3 1 3; "
                    + "-fx-border-radius: 2; -fx-background-radius: 2; -fx-font-size: 11;");
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof InterviewCard)) {
            return false;
        }

        // state check
        InterviewCard card = (InterviewCard) other;
        return id.getText().equals(card.id.getText())
                && interview.equals(card.interview);
    }
}
