package seedu.address.ui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2122s2-cs2103t-t12-3.github.io/tp/UserGuide.html";
    public static final String NEWLINE_AND_INDENTATION = "\n     ";
    public static final String HELP_MESSAGE = "Tracey supports the following features: Find, Add, Delete, Edit, Clear, "
            + "Summarise, List, Help, Filter, Undo, Redo, Email, Archive and Resize.\n"
            + "This guide aims to showcase the syntax used for the aforementioned features.\n"
            + "For a full comprehensive guide, please refer to " + USERGUIDE_URL
            + "\n"

            + "\n1. FIND a particular contact in Tracey. The input keywords does not require the full name of a contact"
            + NEWLINE_AND_INDENTATION
            + "Format: find NAME"
            + NEWLINE_AND_INDENTATION
            + "Example 1: find John\n"

            + "\n2. ADD a student with relevant details into Tracey."
            + NEWLINE_AND_INDENTATION
            + "List of tags: n/Name b/Block f/Faculty p/Phone Number a/Address e/Email "
            + "mc/Matriculation Number CS/Covid Status"
            + NEWLINE_AND_INDENTATION
            + "Format: add n/NAME p/PHONENUMBER e/EMAIL ..."
            + NEWLINE_AND_INDENTATION
            + "Example 1: add n/Melvin b/e f/SOC p/99999999 a/12 Kent Ridge Drive "
            + "e/melvin@bing.com mc/a0216456n cs/negative"
            + NEWLINE_AND_INDENTATION
            + "Example 2: add f/FOL p/84848002 a/13 Kent Ridge Drive e/danny@yahoo.com "
            + "mc/a0024345h n/Danny cs/positive b/c\n"
            + "\n3. DELETE a contact at a specific INDEX. The index refers to the index number shown in the "
            + "displayed person list."
            + NEWLINE_AND_INDENTATION
            + "Format: delete INDEX"
            + NEWLINE_AND_INDENTATION
            + "Example 1: Delete 2"
            + NEWLINE_AND_INDENTATION
            + "Example 2: Delete 4\n"

            + "\n4. EDIT the person at the specified INDEX. The index refers to the index number shown in the "
            + "displayed person list."
            + NEWLINE_AND_INDENTATION
            + "Format: edit INDEX n/NAME ..."
            + NEWLINE_AND_INDENTATION
            + "Example 1: edit 1 p/91234567 e/johndoe@example.com"
            + NEWLINE_AND_INDENTATION
            + "Example 2: edit 2 n/David Limpeh t/\n"

            + "\n5. CLEAR all the data inside Tracey"
            + NEWLINE_AND_INDENTATION
            + "WARNING: Cleared data cannot be retrieved!"
            + NEWLINE_AND_INDENTATION
            + "Format: clear\n"

            + "\n6. SUMMARISE the record inside Tracey that to provide an overview of the data,"
            + " such as the number of students who are Covid Positive"
            + NEWLINE_AND_INDENTATION
            + "Format: summarise\n"

            + "\n7. LIST the full record, which displays all the student’s data that are logged into Tracey"
            + NEWLINE_AND_INDENTATION
            + "Format: list\n"

            + "\n8. Display syntax examples through HELP"
            + NEWLINE_AND_INDENTATION
            + "Format: help\n"

            + "\n9. FILTER student based on covid status, block or faculty"
            + NEWLINE_AND_INDENTATION
            + "Format: filter f/FACULTY cs/COVID_STATUS b/BLOCK"
            + NEWLINE_AND_INDENTATION
            + "Example 1: filter cs/positive"
            + NEWLINE_AND_INDENTATION
            + "Example 2: filter b/c\n"

            + "\n10. UNDO an executed add, edit or delete command."
            + NEWLINE_AND_INDENTATION
            + "Format: undo\n"

            + "\n11. REDO an executed undo command.."
            + NEWLINE_AND_INDENTATION
            + "Format: redo\n"

            + "\n12. EMAIL addresses of the students in the list are collated."
            + NEWLINE_AND_INDENTATION
            + "Format: email\n"


            + "\n13. ARCHIVE the current database, saving the data in ROOT/data/archive/DIRECTORY/ARCHIVED_FILE."
            + NEWLINE_AND_INDENTATION
            + "Format: archive\n"

            + "\n14. RESIZE the display of the response box according to size 1, 2 or 3."
            + NEWLINE_AND_INDENTATION
            + "Format: RESIZE SIZE"
            + NEWLINE_AND_INDENTATION
            + "Example 1: resize 1"
            + NEWLINE_AND_INDENTATION
            + "Example 2: resize 3";


    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";


    @FXML
    private Button openUserGuideButton;

    @FXML
    private TextArea helpMessage;

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
     * @throws IllegalStateException When the window is shown when not called
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
     * Open the User Guide in the user's default browser.
     */
    @FXML
    private void openUG() {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop desktop = Desktop.getDesktop();
                desktop.browse(URI.create(USERGUIDE_URL));
            } catch (IOException e) {
                logger.warning("User Guide page is currently down");
                Alert alert = new Alert(Alert.AlertType.ERROR, "The URL is currently down/being changed!");
                alert.show();
                e.printStackTrace();
            }
        } else {
            logger.warning("User operating system not supported");
            Alert alert = new Alert(Alert.AlertType.ERROR, "Your operating system is currently not supported!");
            alert.show();
        }
    }
}
