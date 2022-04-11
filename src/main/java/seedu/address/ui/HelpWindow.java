package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String HELPWINDOW_MESSAGE = "Need help? Check out any of the links below!";
    public static final String USERGUIDE_URL = "https://ay2122s2-cs2103t-t12-2.github.io/tp/UserGuide.html#quick-start";
    public static final String USER_GUIDE_HELP_MESSAGE = "User Guide: " + USERGUIDE_URL;
    public static final String COMMAND_SUMMARY_URL =
            "https://ay2122s2-cs2103t-t12-2.github.io/tp/UserGuide.html#command-summary";
    public static final String COMMAND_SUMMARY_HELP_MESSAGE = "Command Summary: " + COMMAND_SUMMARY_URL;

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    private CopyUrlWindow copyUrlWindow;

    @FXML
    private Label helpWindowMessage;

    @FXML
    private Button userGuideCopyButton;

    @FXML
    private Label userGuideHelpMessage;

    @FXML
    private Button commandSummaryCopyButton;

    @FXML
    private Label commandSummaryHelpMessage;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        userGuideHelpMessage.setText(USER_GUIDE_HELP_MESSAGE);
        commandSummaryHelpMessage.setText(COMMAND_SUMMARY_HELP_MESSAGE);
        helpWindowMessage.setText(HELPWINDOW_MESSAGE);

        copyUrlWindow = new CopyUrlWindow();
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
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
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
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
        copyUrlWindow.hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUserGuideUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(USERGUIDE_URL);
        clipboard.setContent(url);
        handleCopyUrlSuccess();
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyCommandSummaryUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(COMMAND_SUMMARY_URL);
        clipboard.setContent(url);
        handleCopyUrlSuccess();
    }

    /**
     * Opens the copy url success window or focuses on it if it's already opened.
     */
    @FXML
    public void handleCopyUrlSuccess() {
        if (!copyUrlWindow.isShowing()) {
            copyUrlWindow.show();
        } else {
            copyUrlWindow.focus();
        }
    }
}
