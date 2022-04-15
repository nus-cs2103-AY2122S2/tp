package seedu.address.ui.testresult;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.testresult.TestResult;
import seedu.address.ui.UiPart;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class TestResultCard extends UiPart<Region> {

    private static final String FXML = "testresult/TestResultListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final TestResult testResult;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label testDate;
    @FXML
    private Label medicalTest;
    @FXML
    private Label result;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public TestResultCard(TestResult testResult, int displayedIndex) {
        super(FXML);
        this.testResult = testResult;
        id.setText(displayedIndex + ". ");
        testDate.setText(testResult.getTestDate().toString());
        medicalTest.setText(testResult.getMedicalTest().value);
        result.setText(testResult.getResult().value);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TestResultCard)) {
            return false;
        }

        // state check
        TestResultCard card = (TestResultCard) other;
        return id.getText().equals(card.id.getText())
                && testResult.equals(card.testResult);
    }
}
