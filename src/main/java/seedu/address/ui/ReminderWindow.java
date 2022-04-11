package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;

/**
 * Controller for reminder window
 */
public class ReminderWindow extends UiPart<Stage> {
    public static final String REMINDER_MESSAGE = "Here are your upcoming interviews!";

    private static final Logger logger = LogsCenter.getLogger(ReminderWindow.class);
    private static final String FXML = "ReminderWindow.fxml";

    // Independent Ui parts residing in this Ui container
    private ApplicationListPanel applicationListPanel;
    private ResultDisplay resultDisplay;

    @FXML
    private Label reminderMessage;

    @FXML
    private StackPane applicationListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;


    /**
     * Creates a new ReminderWindow.
     *
     * @param root Stage to use as the root of the ReminderWindow.
     */
    public ReminderWindow(Stage root) {
        super(FXML, root);
        reminderMessage.setText(REMINDER_MESSAGE);
    }

    /**
     * Creates a new ReminderWindow.
     */
    public ReminderWindow() {
        this(new Stage());
    }

    /**
     * Shows the reminder window.
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
        logger.fine("Showing reminder of upcoming interviews.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the reminder window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the reminder window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the reminder window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts(Logic logic) {
        applicationListPanel = new ApplicationListPanel(logic.getUpcomingApplicationsList());
        applicationListPanelPlaceholder.getChildren().add(applicationListPanel.getRoot());
    }

}
