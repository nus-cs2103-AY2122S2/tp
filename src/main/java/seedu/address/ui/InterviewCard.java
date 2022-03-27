package seedu.address.ui;

import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.interview.Interview;

/**
 * An UI component that displays information of a {@code Interview}.
 */
public class InterviewCard extends UiPart<Region> {

    private static final String FXML = "InterviewListCard.fxml";
    private static final String INTERVIEW_DAY = "Day : ";
    private static final String INTERVIEW_DATE = "Date : ";
    private static final String INTERVIEW_TIME = "Time: ";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Interview interview;

    @FXML
    private HBox interviewCard;
    @FXML
    private Label name;
    @FXML
    private Label studentId;
    @FXML
    private Label id;
    @FXML
    private Label day;
    @FXML
    private Label date;
    @FXML
    private Label time;

    /**
     * Creates a {@code InterviewCode} with the given {@code Interview} and index to display.
     */
    public InterviewCard(Interview interview, int displayedIndex) {
        super(FXML);
        this.interview = interview;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        id.setText(displayedIndex + ". ");
        name.setText(interview.getCandidate().getName().toString());
        studentId.setText(interview.getCandidate().getStudentId().toString());
        day.setText(DayOfWeek.of(interview.getInterviewDay()).toString());
        date.setText(interview.getInterviewDate().format(formatter));
        time.setText(interview.getInterviewStartTime().toString());
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
