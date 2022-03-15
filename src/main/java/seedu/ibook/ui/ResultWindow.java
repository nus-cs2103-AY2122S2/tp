package seedu.ibook.ui;

import static java.util.Objects.requireNonNull;

import javafx.scene.control.TextArea;

/**
 * The window that shows feedback of a command.
 */
public class ResultWindow extends UiPart<TextArea> {

    private static final String FXML = "ResultWindow.fxml";

    /**
     * Initializes a {@code ResultWindow}.
     */
    ResultWindow() {
        super(FXML);
    }

    /**
     * Shows the feedback of a command to user.
     * @param feedbackToUser The feedback of a command.
     */
    void setFeedbackToUser(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        getRoot().setText(feedbackToUser);
    }

}
