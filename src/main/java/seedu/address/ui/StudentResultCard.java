package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.assessment.StudentResult;

public class StudentResultCard extends UiPart<Region> {
    private static final String FXML = "StudentResultListCard.fxml";
    private static final String SCORE_KEYWORD = "Score: ";

    public final StudentResult result;
    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label studentName;
    @FXML
    private Label studentId;
    @FXML
    private Label score;

    /**
     * Creates a {@code StudentResultCard} with the given {@code StudentResult} and index to display.
     */
    public StudentResultCard(StudentResult item, int index) {
        super(FXML);
        result = item;
        id.setText(index + ". ");
        studentName.setText(result.getStudentName().toString());
        studentId.setText(result.getStudentId().toString());
        score.setText(SCORE_KEYWORD + result.getScore().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StudentResultCard)) {
            return false;
        }

        // state check
        StudentResultCard card = (StudentResultCard) other;
        return id.getText().equals(card.id.getText())
                && result.equals(card.result);
    }
}
