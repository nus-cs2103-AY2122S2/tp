package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class ResponseDisplay extends UiPart<Region> {

    private static final String FXML = "ResponseDisplay.fxml";

    @FXML
    private TextArea responseDisplay;

    public ResponseDisplay() {
        super(FXML);
    }

    public void setFeedbackToUser(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        responseDisplay.setText(feedbackToUser);
    }

}
