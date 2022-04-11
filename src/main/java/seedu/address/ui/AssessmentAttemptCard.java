package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.ui.AssessmentAttemptsWindow.AssessmentAttempt;

//@@author Gernene
/**
 * An UI component that displays information of a {@code Person}.
 */
public class AssessmentAttemptCard extends UiPart<Region> {

    private static final String FXML = "AssessmentAttemptListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final AssessmentAttempt attempt;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label student;
    @FXML
    private Label grade;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     * 
     * @param attempt A student's {@code AssessmentAttempt} to display.
     * @param displayedIndex The attempt's index in the displayed list.
     */
    public AssessmentAttemptCard(AssessmentAttempt attempt, int displayedIndex) {
        super(FXML);
        this.attempt = attempt;
        id.setText(displayedIndex + "");
        student.setText(attempt.getStudent().getName().fullName);
        grade.setText("Grade: " + attempt.getGrade().value);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AssessmentAttemptCard)) {
            return false;
        }

        // state check
        AssessmentAttemptCard card = (AssessmentAttemptCard) other;
        return attempt.equals(card.attempt);
    }
}
