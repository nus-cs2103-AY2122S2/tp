package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

public class BudgetPrompt extends UiPart<Stage> {
    public static final String BUDGET_PROMPT = "Please set a monthly budget now by entering the "
            + "\"budget a/AMOUNT\" command";

    private static final Logger logger = LogsCenter.getLogger(BudgetPrompt.class);
    private static final String FXML = "BudgetPrompt.fxml";

    @FXML
    private Label budgetPrompt;

    /**
     * Creates a new BudgetPrompt.
     *
     * @param root Stage to use as the root of the BudgetPrompt
     */
    public BudgetPrompt(Stage root) {
        super(FXML, root);
        budgetPrompt.setText(BUDGET_PROMPT);
    }

    /**
     * Creates a new BudgetPrompt.
     */
    public BudgetPrompt() {
        this(new Stage());
    }

    /**
     * Shows the budget prompt.
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
        logger.fine("Prompting the user to set the budget.");
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
        clipboard.setContent(url);
    }
}

