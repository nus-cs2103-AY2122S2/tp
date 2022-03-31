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
            "https://ay2122s2-cs2103-f11-2.github.io/tp/UserGuide.html";
    private static final String HELP_MESSAGE = "Available commands on this version";
    private static final String ADD_FIELD = "id/STUDENT_ID n/NAME p/PHONE e/EMAIL c/COURSE yr/SENIORITY "
            + "avail/AVAILABILITY";
    private static final String CLEAR_FIELD = "Clears all data in the system";
    private static final String INDEX = "INDEX";
    private static final String EDIT_FIELD = INDEX + " PREFIX/NEW_VALUE";
    private static final String EXIT_FIELD = "Ends the program";
    private static final String FIND_FIELD = "k/KEYWORD [k/MORE_KEYWORDS]... [f/ATTRIBUTE_FIELD]";
    private static final String LIST_FIELD = "Lists all candidates in the system";
    private static final String HELP_FIELD = "Opens the help window";
    private static final String REMARK_FIELD = INDEX + " r/REMARKS";
    private static final String SCHEDULE_INDEX = "SCHEDULE_INDEX";
    private static final String SCHEDULE_FIELD = "add candidate/" + INDEX + " at/dd-MM-yyyy HH:mm\n"
            + "edit " + SCHEDULE_INDEX + " at/dd-MM-yyyy HH:mm"
            + "\ndelete " + SCHEDULE_INDEX
            + "\nclear";
    private static final String SORT_FIELD = "s/ATTRIBUTE_FIELD";
    private static final String VIEW_FIELD = "today | week | month | all";

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;
    @FXML
    private Label helpMessage;
    @FXML
    private Label addField;
    @FXML
    private Label clearField;
    @FXML
    private Label deleteField;
    @FXML
    private Label editField;
    @FXML
    private Label exitField;
    @FXML
    private Label findField;
    @FXML
    private Label focusField;
    @FXML
    private Label helpField;
    @FXML
    private Label listField;
    @FXML
    private Label remarkField;
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
        clearField.setText(CLEAR_FIELD);
        deleteField.setText(INDEX);
        editField.setText(EDIT_FIELD);
        exitField.setText(EXIT_FIELD);
        findField.setText(FIND_FIELD);
        focusField.setText(INDEX);
        helpField.setText(HELP_FIELD);
        listField.setText(LIST_FIELD);
        remarkField.setText(REMARK_FIELD);
        scheduleField.setText(SCHEDULE_FIELD);
        sortField.setText(SORT_FIELD);
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

