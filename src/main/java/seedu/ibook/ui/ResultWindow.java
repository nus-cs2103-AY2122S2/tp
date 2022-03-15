package seedu.ibook.ui;

import static java.util.Objects.requireNonNull;

import javafx.scene.control.TextArea;

/**
 * The window that shows feedback of a command.
 */
public class ResultWindow extends UiComponent<TextArea> {

    private static final String FXML = "ResultWindow.fxml";

    /**
     * Initializes a {@code ResultWindow}.
     *
     * @param mainWindow The {@code MainWindow} that this component resides on.
     */
    ResultWindow(MainWindow mainWindow) {
        super(FXML, mainWindow);
    }

    /**
     * Shows the feedback of a command to user.
     *
     * @param feedbackToUser The feedback of a command.
     */
    void setFeedbackToUser(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        getRoot().setText(feedbackToUser);
    }

}
