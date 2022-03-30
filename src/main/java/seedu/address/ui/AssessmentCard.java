package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.assessment.Assessment;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class AssessmentCard extends UiPart<Region> {

    private static final String FXML = "AssessmentListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Assessment assessment;

    private AssessmentAttemptsWindow attemptsWindow;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label module;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public AssessmentCard(Assessment assessment, int displayedIndex) {
        super(FXML);
        this.assessment = assessment;

        id.setText(displayedIndex + ". ");
        name.setText(assessment.getAssessmentName().value);
        module.setText(assessment.getTaModule().getModuleCode().value);

        attemptsWindow = new AssessmentAttemptsWindow(assessment.getAttempts());
    }

    @FXML
    private void handleAttempts() {
        if (!attemptsWindow.isShowing()) {
            attemptsWindow.show();
        } else {
            attemptsWindow.focus();
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AssessmentCard)) {
            return false;
        }

        // state check
        AssessmentCard card = (AssessmentCard) other;
        return assessment.equals(card.assessment);
    }
}
