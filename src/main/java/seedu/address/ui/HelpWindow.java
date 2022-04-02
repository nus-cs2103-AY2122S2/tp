package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.ui.formats.HelpImage;
import seedu.address.ui.formats.HelpTable;
import seedu.address.ui.formats.HelpText;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2122s2-cs2103-w16-4.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "For more details: " + USERGUIDE_URL;
    public static final String WELCOME_MESSAGE = "Welcome to RealEstatePro\n\n"
                                                + "To access the user guide please access it via the URL above.\n"
                                                + "For a simple guide on how to use the app you may access it "
                                                + "by clicking any of the buttons on the left.";
    public static final String FEATURE_MESSAGE = "Main features of  RealEstatePro\n\n"
                + "Manage clients - Add, edit and delete clients information as you see fit\n"
                + "Filter clients - find and sort clients easily to find the information you need\n"
                + "Match clients - Match clients easily based on their properties and preference\n"
                + "Reminders - Add reminders so you will no longer be late for any meetings with clients\n"
                + "Favourite - Favourite clients to keep track of those that require your urgent attention.\n"
                + "Add Images - Upload the house, maps of your clients for easy reference";
    public static final String FLAGS_MESSAGE = "Flags for Add and Edit commands: "
                            + "n/ - Name\n"
                            + "p/ - Phone Number\n"
                            + "e/ - Email\n"
                            + "a/ - Address\n"
                            + "pr/ - Property\n"
                            + "pf/ - Preference\n"
                            + "i/ - Upload image\n";
    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    @FXML
    private StackPane helpPanePlaceHolder;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
        onHomeClick();
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

    /**
     * Changes the contents of pane in help window to home page
     */
    @FXML
    public void onHomeClick() {
        HelpText helpText = new HelpText(WELCOME_MESSAGE);
        helpPanePlaceHolder.getChildren().clear();
        helpPanePlaceHolder.getChildren().setAll(helpText.getRoot());
    }

    /**
     * Changes the contents of pane in help window to features page
     */
    @FXML
    public void onFeaturesClick() {
        HelpText helpText = new HelpText(FEATURE_MESSAGE);
        helpPanePlaceHolder.getChildren().clear();
        helpPanePlaceHolder.getChildren().setAll(helpText.getRoot());
    }

    /**
     * Changes the contents of pane in help window to ui guide page
     */
    @FXML
    public void onUiClick() {
        Image guideImage = new Image(this.getClass().getResourceAsStream("/images/UI_Guide.png"));
        HelpImage helpImage = new HelpImage(guideImage);
        helpPanePlaceHolder.getChildren().clear();
        helpPanePlaceHolder.getChildren().setAll(helpImage.getRoot());
    }

    /**
     * Changes the contents of pane in help window to command list page
     */
    @FXML
    public void onCommandClick() {
        HelpTable helpTable = new HelpTable();
        helpPanePlaceHolder.getChildren().clear();
        helpPanePlaceHolder.getChildren().setAll(helpTable.getRoot());
    }

    /**
     * Changes the contents of pane in help window to features page
     */
    @FXML
    public void onFlagsClick() {
        HelpText helpText = new HelpText(FLAGS_MESSAGE);
        helpPanePlaceHolder.getChildren().clear();
        helpPanePlaceHolder.getChildren().setAll(helpText.getRoot());
    }

}

