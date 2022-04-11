package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

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
import seedu.address.model.person.Person;

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
    private ContactDetailsPanel contactDetailsPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private ConfirmWindow confirmWindow;
    private MeetingListPanel meetingListPanel;
    private ContactMeetingsPanel contactMeetingsPanel;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane panelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane meetingListPanelPlaceholder;

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
        confirmWindow = new ConfirmWindow(logic);
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     *
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

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
        panelPlaceholder.getChildren().add(personListPanel.getRoot());

        meetingListPanel = new MeetingListPanel(logic.getMeetingList(), logic.getPersonList());
        meetingListPanelPlaceholder.getChildren().add(meetingListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeHomePageCommand);
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
     * Opens the contacts confirmation window or focuses on it if it's already opened.
     */
    @FXML
    public void handleContactsClearConfirmation(boolean isMeetingClear) {
        if (!confirmWindow.isShowing()) {
            confirmWindow.show(isMeetingClear);
        } else {
            confirmWindow.focus();
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
        primaryStage.hide();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Switches the page to display the lists of all contacts and meetings.
     */
    private void loadHomePage() {
        personListPanel = new PersonListPanel(logic.getFilteredPersonList());
        CommandBox commandBox = new CommandBox(this::executeHomePageCommand);
        meetingListPanel = new MeetingListPanel(logic.getMeetingList(), logic.getPersonList());

        panelPlaceholder.getChildren().removeAll();
        commandBoxPlaceholder.getChildren().removeAll();
        meetingListPanelPlaceholder.getChildren().removeAll();

        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
        panelPlaceholder.getChildren().add(personListPanel.getRoot());
        meetingListPanelPlaceholder.getChildren().add(meetingListPanel.getRoot());

        commandBox.setFocused();
    }

    /**
     * Switches the screen to display the contact information of a specific person.
     */
    private void loadContactDetailsPage(Person personToDisplay) {
        contactDetailsPanel = new ContactDetailsPanel(personToDisplay);
        CommandBox commandBox = new CommandBox(this::executeContactDetailsPageCommand);
        contactMeetingsPanel = new ContactMeetingsPanel(logic.getMeetingList(), logic.getPersonList(), personToDisplay);

        panelPlaceholder.getChildren().removeAll();
        commandBoxPlaceholder.getChildren().removeAll();
        meetingListPanelPlaceholder.getChildren().removeAll();

        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
        panelPlaceholder.getChildren().add(contactDetailsPanel.getRoot());
        meetingListPanelPlaceholder.getChildren().add(contactMeetingsPanel.getRoot());

        commandBox.setFocused();
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.address.logic.Logic#executeHomePageCommand(String)
     */
    private CommandResult executeHomePageCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.executeHomePageCommand(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            if (commandResult.requiresConfirmation()) {
                handleContactsClearConfirmation(commandResult.isMeetingClear());
            }

            if (commandResult.isLoadPersonList()) {
                loadHomePage();
            }

            if (commandResult.isLoadContactDetails()) {
                Person personToEdit = commandResult.getPerson();
                requireNonNull(personToEdit);
                loadContactDetailsPage(personToEdit);
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.address.logic.Logic#executeHomePageCommand(String)
     */
    private CommandResult executeContactDetailsPageCommand(String commandText) throws CommandException, ParseException {
        try {
            Person currentPerson = contactDetailsPanel.getPerson();
            CommandResult commandResult = logic.executeContactDetailsPageCommand(commandText, currentPerson);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            if (commandResult.isLoadPersonList()) {
                loadHomePage();
            }

            if (commandResult.isLoadContactDetails()) {
                Person personToEdit = commandResult.getPerson();
                requireNonNull(personToEdit);
                loadContactDetailsPage(personToEdit);
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
