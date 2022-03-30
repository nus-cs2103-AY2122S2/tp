package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.storage.ReminderPersons;

/**
 * The Reminder Window provides the basic applications layout containing a menu
 * bar and space to display clients marked for reminders in the application.
 */
public class ReminderWindow extends UiPart<Stage> {
    private static final String FXML = "ReminderWindow.fxml";
    private static ReminderWindow reminderWindow;
    private final Logger logger = LogsCenter.getLogger(getClass());
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private ReminderPersonListPanel reminderPersonListPanel;

    @FXML
    private Label reminderStatus;
    @FXML
    private StackPane reminderPersonListPanelPlaceholder;

    /**
     * Sets up Logic instance in ReminderWindow
     */
    private ReminderWindow(Logic logic) {
        super(FXML);
        this.logic = logic;
    }

    public static ReminderWindow getInstance(Logic logic) {
        if (reminderWindow == null) {
            reminderWindow = new ReminderWindow(logic);
        }
        return reminderWindow;
    }

    public static ReminderWindow getInstance() {
        return reminderWindow;
    }

    /**
     * Calls methods to fill in all parts of the window and displays window
     */
    public void show() {
        logger.fine("Showing Reminder Window.");
        fillInnerParts();
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

    public ReminderPersonListPanel getPersonListPanel() {
        return reminderPersonListPanel;
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        if (ReminderPersons.getInstance().isEmpty()) {
            reminderStatus.setText("No Reminders set!");
        } else {
            reminderStatus.setVisible(false);
            reminderStatus.setManaged(false);
        }
        reminderPersonListPanel = new ReminderPersonListPanel(logic.getReminderPersonList());
        reminderPersonListPanelPlaceholder.getChildren().add(reminderPersonListPanel.getRoot());
    }
}
