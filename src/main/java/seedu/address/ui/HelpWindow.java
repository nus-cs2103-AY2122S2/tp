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
    public static final String COMMAND_LIST = "Add add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS "
                                            + "pr/PROPERTY_SIZE, PROPERTY_LOCATION, PROPERTY_PRICE, t/USER_TYPE\n";

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

    @FXML
    public void onHomeClick() {
        HelpText helpText = new HelpText(WELCOME_MESSAGE);
        helpPanePlaceHolder.getChildren().clear();
        helpPanePlaceHolder.getChildren().setAll(helpText.getRoot());
    }

    @FXML
    public void onCommandClick() {
        HelpTable helpTable = new HelpTable();
        helpPanePlaceHolder.getChildren().clear();
        helpPanePlaceHolder.getChildren().setAll(helpTable.getRoot());
    }

    @FXML
    public void onUiClick() {
        Image guideImage = new Image(this.getClass().getResourceAsStream("/images/UI_Guide.png"));
        HelpImage helpImage = new HelpImage(guideImage);
        helpPanePlaceHolder.getChildren().clear();
        helpPanePlaceHolder.getChildren().setAll(helpImage.getRoot());
    }
}

