package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for an error page
 */
public class ErrorWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(ErrorWindow.class);
    private static final String FXML = "ErrorWindow.fxml";

    private static String message;

    @FXML
    private Label errorMessage;

    /**
     * Creates a new ErrorWindow.
     *
     * @param root Stage to use as the root of the ErrorWindow.
     * @param message Error message to be shown in ErrorWindow.
     */
    public ErrorWindow(Stage root, String message) {
        super(FXML, root);
        errorMessage.setText(message);
    }

    /**
     * Creates a new ErrorWindow.
     *
     * @param message Error message to be shown in ErrorWindow.
     */
    public ErrorWindow(String message) {
        this(new Stage(), message);
    }

    /**
     * Shows the error window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        logger.fine("Showing error window of the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the error window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the error window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the error window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}
