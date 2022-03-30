package seedu.address.ui;

import java.io.IOException;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.HelpWindowUtil;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL =
            "https://ay2122s2-cs2103-f11-2.github.io/tp/UserGuide.html#command-summary";
    private static final String HELP_MESSAGE = "Available commands on this version";
    private static final String ADD_FIELD = "n/NAME id/STUDENT_ID c/COURSE avail/AVAILABILITY";
    private static final String FIELD_IS_INDEX = "INDEX";
    private static final String EDIT_FIELD = FIELD_IS_INDEX + " [PREFIX]/[NEW VALUE]";
    private static final String FIND_FIELD = "k/KEYWORD k/[MORE_KEYWORDS] f/[ATTRIBUTE_FIELD]";
    private static final String SCHEDULE_FIELD = "add candidate/" + FIELD_IS_INDEX + " at/dd-MM-yyyy HH:mm";
    private static final String SORT_FIELD = "s/ATTRIBUTE";
    private static final String VIEW_FIELD = "[today] [week] [month]";

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;
    @FXML
    private Label helpMessage;

    @FXML
    private Label addField;
    @FXML
    private Label deleteField;
    @FXML
    private Label editField;
    @FXML
    private Label exit;
    @FXML
    private Label findField;
    @FXML
    private Label focusField;
    @FXML
    private Label scheduleField;
    @FXML
    private Label sortField;
    @FXML
    private Label viewField;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
        addField.setText(ADD_FIELD);
        deleteField.setText(FIELD_IS_INDEX);
        editField.setText(EDIT_FIELD);
        findField.setText(FIND_FIELD);
        scheduleField.setText(SCHEDULE_FIELD);
        sortField.setText(SORT_FIELD);
        focusField.setText(FIELD_IS_INDEX);
        viewField.setText(VIEW_FIELD);
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
    public void goToUrl() throws IOException {
        new HelpWindowUtil(USERGUIDE_URL).goToUrl();
    }
}

