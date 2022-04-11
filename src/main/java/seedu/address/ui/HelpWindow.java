package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import seedu.address.commons.core.HelpWindowMessages;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://se-education.org/addressbook-level3/UserGuide.html";
    public static final String HELP_MESSAGE = "Refer to the user guide: " + USERGUIDE_URL;

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private RadioButton introButton;

    @FXML
    private RadioButton commandButton;

    @FXML
    private RadioButton devGuideButton;

    @FXML
    private AnchorPane displayPane;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        root.setResizable(true);
        initializeButtons();
        addIntroText();
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

    private void initializeButtons() {
        RadioButton[] buttonArray = {introButton, commandButton, devGuideButton};
        for (RadioButton button : buttonArray) {
            button.getStyleClass().remove("radio-button");
            button.getStyleClass().add("toggle-button");
        }
    }

    private void addIntroText() {
        TextFlow introText = HelpWindowMessages.createIntroText();
        setAnchors(introText, 47, 35, 35, 35);
        introText.prefWidthProperty().bind(displayPane.widthProperty());
        introText.prefHeightProperty().bind(displayPane.heightProperty());
        displayPane.getChildren().add(introText);
    }

    private void addDevGuideText() {
        TextFlow devGuideText = HelpWindowMessages.createDevGuideText();
        setAnchors(devGuideText, 47, 35, 35, 35);
        devGuideText.prefWidthProperty().bind(displayPane.widthProperty());
        devGuideText.prefHeightProperty().bind(displayPane.heightProperty());
        displayPane.getChildren().add(devGuideText);
    }

    private void setAnchors(Node node, double top, double bottom, double left, double right) {
        AnchorPane.setTopAnchor(node, top);
        AnchorPane.setLeftAnchor(node, left);
        AnchorPane.setRightAnchor(node, right);
        AnchorPane.setBottomAnchor(node, bottom);
    }

    @FXML
    private void showCommands() {
        displayPane.getChildren().clear();
        HelpCommandDisplay commandDisplay = new HelpCommandDisplay();
        commandDisplay.addCommandDisplay(displayPane);
    }

    /**
     * Shows the command tab in the help window, and performs a keyword search for the specified command.
     * @param word The command word to be searched.
     */
    public void showCommandsForWord(String word) {
        commandButton.setSelected(true);
        displayPane.getChildren().clear();
        HelpCommandDisplay commandDisplay = new HelpCommandDisplay();
        commandDisplay.addCommandDisplay(displayPane);
        commandDisplay.identifyHelpList(word);
    }

    @FXML
    private void showIntro() {
        displayPane.getChildren().clear();
        addIntroText();
    }

    @FXML
    private void showDevGuide() {
        displayPane.getChildren().clear();
        addDevGuideText();
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
