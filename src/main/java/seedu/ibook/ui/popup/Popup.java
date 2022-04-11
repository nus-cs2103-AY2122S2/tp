package seedu.ibook.ui.popup;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import seedu.ibook.ui.MainWindow;
import seedu.ibook.ui.UiComponent;

/**
 * Abstract Class representing the skeleton of a popup.
 */
public abstract class Popup extends UiComponent<Stage> {

    protected static final String ILLEGAL_REGEX = ".*(?:[^\\\\]|^):.*";

    protected static final String MESSAGE_CONSTRAINTS = "The : character must be escaped with a backslash.";

    @FXML
    private Text error;

    /**
     * Initializes a new popup window with a {@code FXML} file path
     * and a {@code CommandExecutor}.
     *
     * @param filePath FXML filepath.
     * @param mainWindow The {@code MainWindow} that this component resides on.
     */
    public Popup(String filePath, MainWindow mainWindow) {
        super(filePath, mainWindow, new Stage());
    }

    /**
     * Shows the popup window.
     */
    public void show() {
        getRoot().setAlwaysOnTop(true);
        getRoot().show();
        error.setText("");
    }

    /**
     * Hides the popup window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Checks if the popup is showing.
     *
     * @return A boolean indicating if the popup is showing.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Shows feedback to user.
     *
     * @param feedbackToUser The feedback string.
     */
    public void setFeedbackToUser(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        error.setText(feedbackToUser);
    }

    /**
     * Executes the commandText with the {@code commandExecutor}.
     *
     * @param commandText The commandText.
     */
    public void execute(String commandText) {
        getMainWindow().executeCommand(commandText);
    }

    /**
     * Replaces line break to white space
     *
     * @param element The {@code TextArea} element.
     */
    protected void replaceLineBreak(TextArea element) {
        element.setText(element.getText().replace("\n", " "));
    }

}
