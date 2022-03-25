package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.interview.Interview;

/**
 * An UI component that displays information of a {@code Interview}.
 */
public class FocusCard extends UiPart<Region> {

    private static final String FXML = "FocusListCard.fxml";

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
    private Label applicationStatus;
    @FXML
    private Label interviewStatus;
    @FXML
    private Label availability;
    @FXML
    private FlowPane tags;
    /**
     * Creates a {@code InterviewCode} with the given {@code Interview} and index to display.
     */
    public FocusCard(Interview interview, int displayedIndex) {
        super(FXML);
        this.interview = interview;
        id.setText(displayedIndex + ". ");
        name.setText(interview.getInterviewDate().toString());
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
                && interview.equals(card.interview);
    }
}
