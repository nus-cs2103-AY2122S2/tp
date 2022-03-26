package seedu.ibook.ui.popup;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import seedu.ibook.ui.MainWindow;
import seedu.ibook.ui.UiComponent;

/**
 * Abstract Class representing the skeleton of a popup.
 */
public abstract class Popup extends UiComponent<Stage> {

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
     * Hides the popup if it is showing.
     */
    public void hideIfShowing() {
        if (isShowing()) {
            hide();
        }
    }

    /**
     * Focuses the popup window.
     */
    public void focus() {
        getRoot().requestFocus();
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

}
