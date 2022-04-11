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

    public static final String USERGUIDE_URL = "https://ay2122s2-cs2103-w16-3.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "Refer to the user guide: " + USERGUIDE_URL;

    public static final String LIST_COMMAND_DESC = "Listing all persons: ";
    public static final String LIST_COMMAND = "list";

    public static final String EXIT_COMMAND_DESC = "Exiting the program: ";
    public static final String EXIT_COMMAND = "exit";

    public static final String CLEAR_COMMAND_DESC = "Clearing all the data: ";
    public static final String CLEAR_COMMAND = "clear";

    public static final String ADD_COMMAND_DESC = "Add student contact information: ";
    public static final String ADD_COMMAND = "add n/NAME p/PHONE_NUMBER e/EMAIL a/ACADEMIC_MAJOR [t/TAG]";

    public static final String DELETE_COMMAND_DESC = "Delete student contact information: ";
    public static final String DELETE_COMMAND = "delete INDEX";

    public static final String ADD_GROUP_COMMAND_DESC = "Create a group: ";
    public static final String ADD_GROUP_COMMAND = "addgroup g/GROUP_NAME";

    public static final String DELETE_GROUP_COMMAND_DESC = "Delete a group: ";
    public static final String DELETE_GROUP_COMMAND = "delgroup g/GROUP_NAME";

    public static final String ASSIGN_COMMAND_DESC = "Assign a student to a group: ";
    public static final String ASSIGN_COMMAND = "assign INDEX g/GROUP_NAME";

    public static final String DEASSIGN_COMMAND_DESC = "Deassign a student from a group: ";
    public static final String DEASSIGN_COMMAND = "deassign INDEX g/GROUP_NAME";

    public static final String ADD_TASK_COMMAND_DESC = "Add a task to a group: ";
    public static final String ADD_TASK_COMMAND = "addtask task/TASK_NAME g/GROUP_NAME";

    public static final String DELETE_TASK_COMMAND_DESC = "Delete a task from a group: ";
    public static final String DELETE_TASK_COMMAND = "deltask task/TASK_NAME g/GROUP_NAME";

    public static final String VIEW_TASK_COMMAND_DESC = "Displays the tasks in a group: ";
    public static final String VIEW_TASK_COMMAND = "viewtask g/GROUP_NAME";

    public static final String VIEW_CONTACT_COMMAND_DESC = "Displays the student contacts in a group: ";
    public static final String VIEW_CONTACT_COMMAND = "viewcontact g/GROUP_NAME";

    public static final String FIND_COMMAND_DESC = "Finds the student contacts that matches the keyword(s): ";
    public static final String FIND_COMMAND = "find PREFIX/KEYWORD or find PREFIX/KEYWORD [MORE_KEYWORDS]";

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    @FXML
    private Label listCommandDesc;

    @FXML
    private Label listCommand;

    @FXML
    private Label exitCommandDesc;

    @FXML
    private Label exitCommand;

    @FXML
    private Label clearCommandDesc;

    @FXML
    private Label clearCommand;

    @FXML
    private Label addCommandDesc;

    @FXML
    private Label addCommand;

    @FXML
    private Label deleteCommandDesc;

    @FXML
    private Label deleteCommand;

    @FXML
    private Label addGroupCommandDesc;

    @FXML
    private Label addGroupCommand;

    @FXML
    private Label delGroupCommandDesc;

    @FXML
    private Label delGroupCommand;

    @FXML
    private Label assignCommandDesc;

    @FXML
    private Label assignCommand;

    @FXML
    private Label deassignCommandDesc;

    @FXML
    private Label deassignCommand;

    @FXML
    private Label addTaskCommandDesc;

    @FXML
    private Label addTaskCommand;

    @FXML
    private Label delTaskCommandDesc;

    @FXML
    private Label delTaskCommand;

    @FXML
    private Label viewTaskCommandDesc;

    @FXML
    private Label viewTaskCommand;

    @FXML
    private Label viewContactCommandDesc;

    @FXML
    private Label viewContactCommand;

    @FXML
    private Label findCommandDesc;

    @FXML
    private Label findCommand;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
        listCommandDesc.setText(LIST_COMMAND_DESC);
        listCommand.setText(LIST_COMMAND);

        exitCommandDesc.setText(EXIT_COMMAND_DESC);
        exitCommand.setText(EXIT_COMMAND);

        clearCommandDesc.setText(CLEAR_COMMAND_DESC);
        clearCommand.setText(CLEAR_COMMAND);

        addCommandDesc.setText(ADD_COMMAND_DESC);
        addCommand.setText(ADD_COMMAND);

        deleteCommandDesc.setText(DELETE_COMMAND_DESC);
        deleteCommand.setText(DELETE_COMMAND);

        addGroupCommandDesc.setText(ADD_GROUP_COMMAND_DESC);
        addGroupCommand.setText(ADD_GROUP_COMMAND);

        delGroupCommandDesc.setText(DELETE_GROUP_COMMAND_DESC);
        delGroupCommand.setText(DELETE_GROUP_COMMAND);

        assignCommandDesc.setText(ASSIGN_COMMAND_DESC);
        assignCommand.setText(ASSIGN_COMMAND);

        deassignCommandDesc.setText(DEASSIGN_COMMAND_DESC);
        deassignCommand.setText(DEASSIGN_COMMAND);

        addTaskCommandDesc.setText(ADD_TASK_COMMAND_DESC);
        addTaskCommand.setText(ADD_TASK_COMMAND);

        delTaskCommandDesc.setText(DELETE_TASK_COMMAND_DESC);
        delTaskCommand.setText(DELETE_TASK_COMMAND);

        viewTaskCommandDesc.setText(VIEW_TASK_COMMAND_DESC);
        viewTaskCommand.setText(VIEW_TASK_COMMAND);

        viewContactCommandDesc.setText(VIEW_CONTACT_COMMAND_DESC);
        viewContactCommand.setText(VIEW_CONTACT_COMMAND);

        findCommandDesc.setText(FIND_COMMAND_DESC);
        findCommand.setText(FIND_COMMAND);
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
