package seedu.address.ui;

import java.util.List;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.FindCommand;

/**
 * Controller for a tutorial page
 */
public class TutorialWindow extends UiPart<Stage> {

    public static final String WELCOME = "Welcome to onlyFAs! Here is a tutorial on how to use our app.";
    public static final List<String> STEPS = List.of(WELCOME, AddCommand.MESSAGE_USAGE, DeleteCommand.MESSAGE_USAGE,
            EditCommand.MESSAGE_USAGE, FindCommand.MESSAGE_USAGE);

    private static final Logger logger = LogsCenter.getLogger(TutorialWindow.class);
    private static final String FXML = "TutorialWindow.fxml";
    private int index = 0;

    @FXML
    private Button nextButton;

    @FXML
    private Label tutorialMessage;

    /**
     * Creates a new TutorialWindow.
     *
     * @param root Stage to use as the root of the TutorialWindow.
     */
    public TutorialWindow(Stage root) {
        super(FXML, root);
        tutorialMessage.setText(STEPS.get(index));
    }

    /**
     * Creates a new TutorialWindow.
     */
    public TutorialWindow() {
        this(new Stage());
    }

    /**
     * Shows the tutorial window.
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
        logger.fine("Showing tutorial page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the tutorial window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the tutorial window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the tutorial window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void next() {
        if (index + 1 == STEPS.size()) {
            getRoot().close();
            index = 0;
            tutorialMessage.setText(STEPS.get(0));
        } else {
            if (index == STEPS.size() - 2) {
                nextButton.setText("Done");
            }
            index += 1;
            tutorialMessage.setText(STEPS.get(index));
        }
    }
}
