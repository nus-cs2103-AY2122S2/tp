package manageezpz.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class ResultDisplay extends UiPart<Region> {

    private static final String FXML = "ResultDisplay.fxml";
    private static final String WELCOME_MESSAGE = "Welcome to ManageEZPZ!";

    @FXML
    private TextArea resultDisplay;

    /**
     * Initializes a {@code ResultDisplay} and sets the welcome message on
     * {@code TextArea} resultDisplay.
     */
    public ResultDisplay() {
        super(FXML);
        resultDisplay.setText(WELCOME_MESSAGE);
    }

    public void setFeedbackToUser(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        resultDisplay.setText(feedbackToUser);
    }
}
