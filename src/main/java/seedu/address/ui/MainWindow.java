package seedu.address.ui;

import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.BookNames;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.SwitchCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    // This is also a Ui part in MainWindow.
    protected static ResultDisplay resultDisplay;
    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private PersonListPanel personListPanel;
    private HelpWindow helpWindow;
    private AddWindow addWindow;
    private EditWindow editWindow;

    private StatusBarFooter statusBarFooter;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem switchMenuItem;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private MenuItem addMenuItem;

    @FXML
    private MenuItem editMenuItem;

    @FXML
    private StackPane personListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        setAccelerators();

        helpWindow = new HelpWindow();

        // Pass the logic into AddWindow so we can use it to execute commands as well
        addWindow = new AddWindow(logic);
        editWindow = new EditWindow(logic);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
        setAccelerator(switchMenuItem, KeyCombination.valueOf("F10"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

        /*
         * TODO: the code below can be removed once the bug reported here
         * https://bugs.openjdk.java.net/browse/JDK-8131666
         * is fixed in later version of SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will
         * not work when the focus is in them because the key event is consumed by
         * the TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is
         * in CommandBox or ResultDisplay.
         */
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                menuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        personListPanel = new PersonListPanel(logic.getFilteredPersonList());
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());
        Path defaultPath = logic.getAddressBookFilePath();
        Path archivePath = logic.getArchivedAddressBookFilePath();
        statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath(), defaultPath, archivePath);
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }

    /**
     * Opens the add window or focuses on it if it's already opened.
     */
    @FXML
    public void handleAdd() {
        if (!addWindow.isShowing()) {
            addWindow.show();
        } else {
            addWindow.focus();
        }
    }

    /**
     * Opens the edit window or focuses on it if it's already opened.
     */
    @FXML
    public void handleEdit() {
        if (!editWindow.isShowing()) {
            editWindow.show();
        } else {
            editWindow.focus();
        }
    }

    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        addWindow.hide();
        primaryStage.hide();
    }

    /**
     * GUI alternative to activate a SwitchCommand.
     */
    @FXML
    private void handleSwitchMenu() throws CommandException, ParseException {
        logger.info("Switch Menu Item fired!!!");
        executeCommand(SwitchCommand.COMMAND_WORD);
    }
    /**
     * Function to perform operations involving logic object
     */
    private void handleSwitch() throws CommandException, ParseException {
        logger.info("Handle Switch fired!");
        logic.switchAddressBook();

        resultDisplay.setFeedbackToUser("Switched to: " + statusBarFooter.updateBookPath());

        boolean isDefaultNext = StatusBarFooter.isArchiveBook();
        if (isDefaultNext) {
            switchMenuItem.setText(String.format("Switch to %s", BookNames.BOOKNAME_DEFAULT));
        } else {
            switchMenuItem.setText(String.format("Switch to %s", BookNames.BOOKNAME_ARCHIVED));
        }
    }

    /**
     * Copy to the clipboard the selected person's information.
     */
    private void handleCopy(CommandResult result) {
        logger.info("Copied to clipboard!");
        String copiedText = result.getFeedbackToUser();
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(copiedText);
        clipboard.setContent(content);
    }

    public PersonListPanel getPersonListPanel() {
        return personListPanel;
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.address.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());

            if (commandResult.isCopyCommand()) {
                resultDisplay.setFeedbackToUser("Successfully copied to clipboard!\n");
                handleCopy(commandResult);
            } else {
                resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());
            }

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isShowAdd()) {
                handleAdd();
            }

            if (commandResult.isShowEdit()) {
                handleEdit();
            }

            if (commandResult.isSwitchCommand()) {
                handleSwitch();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
