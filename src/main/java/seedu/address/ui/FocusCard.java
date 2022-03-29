package seedu.address.ui;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.candidate.ApplicationStatus.ACCEPTED_STATUS;
import static seedu.address.model.candidate.ApplicationStatus.PENDING_STATUS;
import static seedu.address.model.candidate.ApplicationStatus.REJECTED_STATUS;
import static seedu.address.model.candidate.InterviewStatus.COMPLETED;
import static seedu.address.model.candidate.InterviewStatus.NOT_SCHEDULED;
import static seedu.address.model.candidate.InterviewStatus.SCHEDULED;
import static seedu.address.ui.Styles.AVAILABILITY_ID;
import static seedu.address.ui.Styles.BLUE;
import static seedu.address.ui.Styles.BRIGHT_GREEN;
import static seedu.address.ui.Styles.CHANGE_COLOUR;
import static seedu.address.ui.Styles.CLOSING_INLINE;
import static seedu.address.ui.Styles.GREEN;
import static seedu.address.ui.Styles.GREY;
import static seedu.address.ui.Styles.RED;
import static seedu.address.ui.Styles.WHITE_FONT_INLINE;
import static seedu.address.ui.Styles.YELLOW;

import java.util.Random;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import seedu.address.model.candidate.ApplicationStatus;
import seedu.address.model.candidate.Availability;
import seedu.address.model.candidate.Candidate;
import seedu.address.model.candidate.InterviewStatus;
import seedu.address.model.candidate.Name;

/**
 * An UI component that displays information of a {@code Candidate}.
 */
public class FocusCard extends UiPart<Region> {

    private static final String FXML = "FocusListCard.fxml";
    private static final String BLANK_PICTURE_PATH = "docs/images/blankprofile.png";
    private static final String SENIORITY_VALUE = "COM";

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
    private StackPane stackPane;
    @FXML
    private Text profileName;

    /**
     * Creates a {@code CandidateCode} with the given {@code Candidate} and index to display.
     */

    public FocusCard(Candidate candidate) {
        super(FXML);
        requireNonNull(candidate);
        this.candidate = candidate;
        id.setText(candidate.getStudentId().toString());
        name.setText(candidate.getName().fullName);
        phone.setText(candidate.getPhone().value);
        email.setText(candidate.getEmail().value);
        course.setText(candidate.getCourse().course + ", " + SENIORITY_VALUE + candidate.getSeniority().seniority);
        setProfilePicture(candidate.getName());
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
            label.setId(AVAILABILITY_ID);
            label.setMinWidth(30);

            if (isAvail[i]) {
                label.setStyle(availStyle);
            } else {
                label.setStyle(notAvailStyle);
            }
            availableDaysFocus.getChildren().add(label);
        }
    }

    private void setProfilePicture(Name candidateName) {
        String[] temp = candidateName.fullName.split(" ");
        StringBuilder initials = new StringBuilder();
        if (temp.length > 1) {
            initials.append(temp[0].charAt(0)).append(temp[1].charAt(0));
        } else {
            initials.append(temp[0].charAt(0));
        }

        Random random = new Random();
        double red = (random.nextInt(106) + 150) / 255.0;
        double blue = (random.nextInt(106) + 150) / 255.0;
        double green = (random.nextInt(106) + 150) / 255.0;

        stackPane.setBackground(new Background(new BackgroundFill(
                Color.color(red, blue, green),
                null,
                null
        )));

        Circle circle = new Circle();
        circle.setRadius(60);
        circle.setCenterX(75);
        circle.setCenterY(75);
        profileName.setText(initials.toString());
        stackPane.setClip(circle);
        stackPane.setAlignment(Pos.CENTER);
    }
}
