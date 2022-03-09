package seedu.ibook.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class ResultWindow extends UiPart<TextArea> {

    private static final String FXML = "ResultWindow.fxml";

    @FXML
    private TextArea resultDisplay;

    ResultWindow() {
        super(FXML);
    }

    void setFeedbackToUser(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        resultDisplay.setText(feedbackToUser);
    }

}
