package seedu.address.ui;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.candidate.ApplicationStatus.ACCEPTED_STATUS;
import static seedu.address.model.candidate.ApplicationStatus.PENDING_STATUS;
import static seedu.address.model.candidate.ApplicationStatus.REJECTED_STATUS;
import static seedu.address.model.candidate.InterviewStatus.COMPLETED;
import static seedu.address.model.candidate.InterviewStatus.NOT_SCHEDULED;
import static seedu.address.model.candidate.InterviewStatus.SCHEDULED;
import static seedu.address.ui.Styles.BLUE;
import static seedu.address.ui.Styles.BRIGHT_GREEN;
import static seedu.address.ui.Styles.CHANGE_COLOUR;
import static seedu.address.ui.Styles.CLOSING_INLINE;
import static seedu.address.ui.Styles.GREEN;
import static seedu.address.ui.Styles.GREY;
import static seedu.address.ui.Styles.RED;
import static seedu.address.ui.Styles.WHITE_FONT_INLINE;
import static seedu.address.ui.Styles.YELLOW;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.candidate.ApplicationStatus;
import seedu.address.model.candidate.Availability;
import seedu.address.model.candidate.Candidate;
import seedu.address.model.candidate.InterviewStatus;

/**
 * An UI component that displays information of a {@code Candidate}.
 */
public class FocusCard extends UiPart<Region> {

    private static final String FXML = "FocusListCard.fxml";
    private static final String BLANK_PICTURE_PATH = "docs/images/blankprofile.png";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Candidate candidate;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label course;
    @FXML
    private Label email;
    @FXML
    private Label address;
    @FXML
    private Label seniority;
    @FXML
    private Label applicationStatus;
    @FXML
    private Label candidateStatus;
    @FXML
    private Label availability;
    @FXML
    private FlowPane statusFocusPane;
    @FXML
    private FlowPane availableDaysFocus;
    @FXML
    private ImageView displayPicture;
    /**
     * Creates a {@code CandidateCode} with the given {@code Candidate} and index to display.
     */

    public FocusCard(Candidate candidate) throws FileNotFoundException {
        super(FXML);
        requireNonNull(candidate);
        this.candidate = candidate;
        id.setText(candidate.getStudentId().toString());
        name.setText(candidate.getName().fullName);
        phone.setText(candidate.getPhone().value);
        email.setText(candidate.getEmail().value);
        course.setText(candidate.getCourse().course + ", " + candidate.getSeniority().seniority);
        displayPicture.setImage(new Image(new FileInputStream(BLANK_PICTURE_PATH)));
        setApplicationStatus(candidate.getApplicationStatus());
        setInterviewStatus(candidate.getInterviewStatus());
        setAvailableDays(candidate.getAvailability());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FocusCard)) {
            return false;
        }

        // state check
        FocusCard card = (FocusCard) other;
        return id.getText().equals(card.id.getText())
                && candidate.equals(card.candidate);
    }

    public void setApplicationStatus(ApplicationStatus applicationStatus) {
        String applicationString = applicationStatus.toString();
        Label applicationLabel = new Label(applicationString);

        if (applicationString.equals(REJECTED_STATUS)) {
            applicationLabel.setStyle(CHANGE_COLOUR + RED);
            statusFocusPane.getChildren().add(applicationLabel);
        } else if (applicationString.equals(ACCEPTED_STATUS)) {
            applicationLabel.setStyle(CHANGE_COLOUR + GREEN);
            statusFocusPane.getChildren().add(applicationLabel);
        } else if (applicationString.equals(PENDING_STATUS)) {
            applicationLabel.setStyle(CHANGE_COLOUR + YELLOW);
            statusFocusPane.getChildren().add(applicationLabel);
        }
    }

    public void setInterviewStatus(InterviewStatus interviewStatus) {
        String interviewString = interviewStatus.toString();
        Label interviewLabel = new Label(interviewString);

        if (interviewString.equals(NOT_SCHEDULED)) {
            interviewLabel.setStyle(CHANGE_COLOUR + RED);
            statusFocusPane.getChildren().add(interviewLabel);
        } else if (interviewString.equals(COMPLETED)) {
            interviewLabel.setStyle(CHANGE_COLOUR + GREEN);
            statusFocusPane.getChildren().add(interviewLabel);
        } else if (interviewString.equals(SCHEDULED)) {
            interviewLabel.setStyle(CHANGE_COLOUR + BLUE);
            statusFocusPane.getChildren().add(interviewLabel);
        }
    }

    public void setAvailableDays(Availability availability) {
        String[] week = Availability.WEEK;
        boolean[] isAvail = availability.getAvailableListAsBoolean();
        String availStyle = CHANGE_COLOUR + BRIGHT_GREEN + CLOSING_INLINE + WHITE_FONT_INLINE;
        String notAvailStyle = CHANGE_COLOUR + GREY + CLOSING_INLINE + WHITE_FONT_INLINE;

        for (int i = 0; i < week.length; i++) {
            Label label = new Label(week[i]);

            if (isAvail[i]) {
                label.setStyle(availStyle);
            } else {
                label.setStyle(notAvailStyle);
            }
            availableDaysFocus.getChildren().add(label);
        }
    }
}
