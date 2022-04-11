package seedu.address.ui;

import java.io.IOException;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.LogicManager;

/**
 * Controller for a clear confirmation
 */
public class ConfirmWindow extends UiPart<Stage> {

    public static final String CONFIRM_MESSAGE = "Are you sure you want to proceed? Deleted data cannot be recovered.";

    private static final Logger logger = LogsCenter.getLogger(ConfirmWindow.class);
    private static final String FXML = "ConfirmWindow.fxml";
    private LogicManager logic;
    private boolean isMeetingClear;

    @FXML
    private Button confirm;

    @FXML
    private Label confirmMessage;

    /**
     * Creates a new ConfirmWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public ConfirmWindow(Stage root) {
        super(FXML, root);
    }

    /**
     * Creates a new Confirm Window
     */
    public ConfirmWindow(Logic logic) {
        this(new Stage());
        this.logic = (LogicManager) logic;
    }

    /**
     * Shows the confirm window.
     * @param isMeetingClear checks whether user requests to clear the meetings only
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
    public void show(boolean isMeetingClear) {
        logger.fine("Asking for user confirmation.");
        this.isMeetingClear = isMeetingClear;
        if (isMeetingClear) {
            confirmMessage.setText(CONFIRM_MESSAGE + "\nAll meetings will be deleted.");
        } else {
            confirmMessage.setText(CONFIRM_MESSAGE + "\nAll Contacts and consequently, Meetings will be deleted.");
        }
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the confirm window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the confirm window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the confirm window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Returns confirmation.
     */
    @FXML
    private void confirm() throws IOException {
        if (isMeetingClear) {
            logic.clearMeetingBook();
        } else {
            logic.clearAddressBook();
            logic.clearMeetingBook();
        }
        hide();
    }
}
