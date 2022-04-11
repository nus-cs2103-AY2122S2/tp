package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

public class CopyUrlWindow extends UiPart<Stage> {

    public static final String SUCCESS_MESSAGE = "URL successfully copied!";

    private static final Logger logger = LogsCenter.getLogger(CopyUrlWindow.class);
    private static final String FXML = "CopyUrlWindow.fxml";


    @FXML
    private Label successMessage;

    /**
     * Creates a new CopyUrlWindow.
     */
    public CopyUrlWindow(Stage root) {
        super(FXML, root);
        successMessage.setText(SUCCESS_MESSAGE);
    }

    /**
     * Creates a new CopyUrlWindow.
     */
    public CopyUrlWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
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
        logger.fine("Showing message that url has been copied successfully.");
        getRoot().show();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}
