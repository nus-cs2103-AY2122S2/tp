package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a generic message window
 */
public class MessageWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(MessageWindow.class);
    private static final String FXML = "MessageWindow.fxml";

    @FXML
    private Label message;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the MessageWindow.
     */
    public MessageWindow(Stage root) {
        super(FXML, root);
    }

    /**
     * Creates a new MessageWindow.
     */
    public MessageWindow() {
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
    public void show(String messageToShow) {
        logger.fine("Showing a message: " + messageToShow);
        message.setText(messageToShow);
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the message window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the message window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the message window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

}
