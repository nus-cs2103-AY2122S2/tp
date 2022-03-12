package seedu.address.ui;

import java.awt.Desktop;
import java.io.File;
import java.nio.file.Path;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private PersonListPanel personListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private MessageWindow messageWindow;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

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
        messageWindow = new MessageWindow();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
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

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
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
     * Handles the tasks associated with allowing the user to load a file from CSV.
     */
    @FXML
    public void handleLoadFromCsv() {
        if (!Desktop.isDesktopSupported()) {
            messageWindow.show("Desktop not supported.");
            return;
        }

        logger.info("Loading from csv");
        Path inputCsvFilePath = handleLoadFile();

        if (inputCsvFilePath == null) {
            return;
        }

        try {
            logic.readAddressBookFromCsv(inputCsvFilePath);
            messageWindow.show(
                    "Loaded successfully. Type a command (e.g. list) to save this permanently.");
        } catch (CommandException err) {
            messageWindow.show("Error in loading: " + err.getMessage());
        }
    }

    /**
     * OHandles the tasks associated with allowing the user to save a file to CSV.
     */
    @FXML
    public void handleSaveToCsv() {
        if (!Desktop.isDesktopSupported()) {
            messageWindow.show("Desktop not supported.");
            return;
        }

        logger.info("Saving to csv");
        Path outputCsvFilePath = handleSaveFile();

        if (outputCsvFilePath == null) {
            return;
        }

        try {
            logic.saveAddressBookToCsv(outputCsvFilePath);
            messageWindow.show("Saved successfully.");
        } catch (CommandException err) {
            messageWindow.show("Error in saving: " + err.getMessage());
        }
    }

    /**
     * Method that handles the GUI aspect of allowing the user to select a path to load CSV file from.
     *
     * @return the Path of the CSV file.
     */
    public Path handleLoadFile() {

        // initialise the file chooser
        JFileChooser chooser = new JFileChooser();

        // current working directory
        File cwd = new java.io.File(".");

        // chooser settings
        chooser.setCurrentDirectory(cwd);
        chooser.setDialogTitle("Select a ClientConnect CSV file...");
        chooser.setAcceptAllFileFilterUsed(false); // disable allowing acceptance of all files

        // only allow CSV files
        FileNameExtensionFilter filter = new FileNameExtensionFilter("ClientConnect CSV files", "csv");
        chooser.setFileFilter(filter);

        // opens the dialog
        int returnVal = chooser.showOpenDialog(null);
        Path chosenFilePath = null;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            chosenFilePath = chooser.getSelectedFile().toPath().toAbsolutePath();
        }
        return chosenFilePath;

    }

    /**
     * Method that handles the GUI aspect of allowing the user to select a path to save a CSV file to.
     *
     * @return the Path of the CSV file.
     */
    public Path handleSaveFile() {

        // initialise the file chooser
        JFileChooser chooser = new JFileChooser();

        chooser.setSelectedFile(new File("ClientConnectData.csv"));

        // chooser settings
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Save CSV file to ...");
        chooser.setSelectedFile(new File("ClientConnectData.csv"));

        // opens the dialog
        int returnVal = chooser.showSaveDialog(null);
        Path chosenDirectoryPath = null;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            chosenDirectoryPath = chooser.getSelectedFile().toPath().toAbsolutePath();
        }
        return chosenDirectoryPath;
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
        messageWindow.hide();
        primaryStage.hide();
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
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowHelp()) {
                handleHelp();
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
