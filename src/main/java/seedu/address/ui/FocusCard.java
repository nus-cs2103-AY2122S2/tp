package seedu.address.ui;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.candidate.ApplicationStatus.ACCEPTED_STATUS;
import static seedu.address.model.candidate.ApplicationStatus.PENDING_STATUS;
import static seedu.address.model.candidate.ApplicationStatus.REJECTED_STATUS;
import static seedu.address.model.candidate.InterviewStatus.COMPLETED;
import static seedu.address.model.candidate.InterviewStatus.NOT_SCHEDULED;
import static seedu.address.model.candidate.InterviewStatus.SCHEDULED;

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
import seedu.address.model.candidate.Candidate;
import seedu.address.model.candidate.InterviewStatus;





/**
 * An UI component that displays information of a {@code Candidate}.
 */
public class FocusCard extends UiPart<Region> {

    private static final String FXML = "FocusListCard.fxml";
    private static final String RED = "#800000";
    private static final String GREEN = "#006100";
    private static final String YELLOW = "#8B8000";
    private static final String CHANGE_COLOUR = "-fx-background-color: ";
    private static final String PHONE = "Phone Number: ";
    private static final String EMAIL = "Email Address: ";
    private static final String ADDRESS = "Address: ";
    private static final String SENIORITY = "Seniority: ";
    private static final String COURSE = "Course: ";

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
        displayPicture.setImage(new Image(new FileInputStream("docs/images/leeenen.png")));
        setApplicationStatus(candidate.getApplicationStatus());
        setInterviewStatus(candidate.getInterviewStatus());
        candidate.getAvailability().getList()
                .forEach(availability -> availableDaysFocus.getChildren().add(new Label(availability)));
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
            interviewLabel.setStyle(CHANGE_COLOUR + YELLOW);
            statusFocusPane.getChildren().add(interviewLabel);
        }
    }


}
