package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FlagCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.MeetCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.UndoCommand;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {
    public static final String MESSAGE_SPACING = "\n\n";

    public static final String USERGUIDE_URL = "https://ay2122s2-cs2103t-w15-2.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "Refer to the user guide: " + USERGUIDE_URL + "\n\n"
            + AddCommand.MESSAGE_USAGE + MESSAGE_SPACING + ClearCommand.MESSAGE_USAGE + MESSAGE_SPACING
            + DeleteCommand.MESSAGE_USAGE + MESSAGE_SPACING + EditCommand.MESSAGE_USAGE + MESSAGE_SPACING
            + ExitCommand.MESSAGE_USAGE + MESSAGE_SPACING + FindCommand.MESSAGE_USAGE + MESSAGE_SPACING
            + FlagCommand.MESSAGE_USAGE + MESSAGE_SPACING + HelpCommand.MESSAGE_USAGE + MESSAGE_SPACING
            + ListCommand.MESSAGE_USAGE + MESSAGE_SPACING + MeetCommand.MESSAGE_USAGE + MESSAGE_SPACING
            + RedoCommand.MESSAGE_USAGE + MESSAGE_SPACING + SortCommand.MESSAGE_USAGE + MESSAGE_SPACING
            + UndoCommand.MESSAGE_USAGE + MESSAGE_SPACING;

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
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
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(USERGUIDE_URL);
        clipboard.setContent(url);
    }
}
