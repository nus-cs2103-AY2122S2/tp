package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.assessment.Assessment;

/**
 * An UI component that displays information of an {@code Assessment}.
 */
public class AssessmentCard extends UiPart<Region> {
    private static final String FXML = "AssessmentListCard.fxml";
    private static final String WEIGHTAGE_KEYWORD = "Weightage: ";
    private static final String FULL_MARK_KEYWORD = "Full marks: ";

    public final Assessment assessment;
    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label weightage;
    @FXML
    private Label fullMark;

    /**
     * Creates a {@code AssessmentCard} with the given {@code Assessment} and index to display.
     */
    public AssessmentCard(Assessment assessment, int index) {
        super(FXML);
        this.assessment = assessment;
        id.setText(index + ". ");
        name.setText(assessment.getAssessmentName().name);
        weightage.setText(WEIGHTAGE_KEYWORD + assessment.getWeightage().toString());
        fullMark.setText(FULL_MARK_KEYWORD + assessment.getFullMark().toString());
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
        return id.getText().equals(card.id.getText())
                && assessment.equals(card.assessment);
    }
}
