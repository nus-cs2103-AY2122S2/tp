package seedu.address.ui;

import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.interview.Interview;

/**
 * An UI component that displays information of a {@code Interview}.
 */
public class InterviewCard extends UiPart<Region> {

    private static final String FXML = "InterviewListCard.fxml";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE dd MMM yyyy, h:mma");

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
    private Label id;
    @FXML
    private Label date;
    @FXML
    private Label role;
    @FXML
    private Label name;
    @FXML
    private Label interviewphone;
    @FXML
    private Label interviewemail;
    @FXML
    private Label status;

    /**
     * Creates a {@code InterviewCard} with the given {@code Interview} and index to display.
     */
    public InterviewCard(Interview interview, int displayedIndex) {
        super(FXML);
        this.interview = interview;

        id.setText(displayedIndex + ". ");
        date.setText(interview.getDate().format(formatter));
        role.setText(interview.getPosition().getPositionName().positionName);

        Applicant applicant = interview.getApplicant();
        name.setText(applicant.getName().fullName);
        interviewphone.setText(applicant.getPhone().value);
        interviewemail.setText(applicant.getEmail().value);
        status.setText(interview.getStatus().toString());
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
