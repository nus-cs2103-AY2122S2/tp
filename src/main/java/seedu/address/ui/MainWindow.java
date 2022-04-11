package seedu.address.ui;

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
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.client.Client;

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
    private ClientListPanel clientListPanel;
    private MeetingListPanel meetingListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private TutorialWindow tutorialWindow;
    private ClientDisplay clientDisplay;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane clientListPanelPlaceholder;

    @FXML
    private StackPane meetingListPanelPlaceholder;

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
        tutorialWindow = new TutorialWindow();
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
        clientListPanel = new ClientListPanel(logic.getClientList());
        clientListPanelPlaceholder.getChildren().add(clientListPanel.getRoot());

        meetingListPanel = new MeetingListPanel(logic.getFilteredMeetingList(), logic.isShowAllMeetings());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        if (logic.getClientList().size() == 0) {
            resultDisplay.setFeedbackToUser("Add client by using the add command!");
            tutorialWindow.show();
        }

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
     * Opens the tutorial window or focuses on it if it's already opened.
     */
    @FXML
    public void handleTutorial() {
        if (!tutorialWindow.isShowing()) {
            tutorialWindow.show();
        } else {
            tutorialWindow.focus();
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
        tutorialWindow.hide();
        primaryStage.hide();
    }

    /**
     * List meetings.
     */
    private void showMeetings() {
        meetingListPanelPlaceholder.getChildren().clear();
        meetingListPanel = new MeetingListPanel(logic.getFilteredMeetingList(), logic.isShowAllMeetings());
        meetingListPanelPlaceholder.getChildren().add(meetingListPanel.getRoot());

        if (logic.getFilteredMeetingList().size() == 0) {
            if (logic.getAddressBook().getMeetingList().size() == 0) {
                resultDisplay.setFeedbackToUser("No meetings found.\n"
                        + "Add meetings by using the `addMeeting` command!");
            } else {
                resultDisplay.setFeedbackToUser("No upcoming meetings found.\n"
                        + "View all meetings (including past meetings) by using the `meetings all/` command!\n"
                        + "Add meetings by using the `addMeeting` command!\n");
            }
        }
    }

    /**
     * List clients.
     */
    private void showClients() {
        clientListPanelPlaceholder.getChildren().add(clientListPanel.getRoot());

        if (logic.getClientList().size() == 0) {
            resultDisplay.setFeedbackToUser("Add client by using the add command!");
        }
    }

    /**
     * Display client.
     */
    private void showClientByIndex(Index index) {
        clientDisplay = new ClientDisplay(logic.getClientList().get(index.getZeroBased()));
        meetingListPanelPlaceholder.getChildren().clear();
        meetingListPanelPlaceholder.getChildren().add(clientDisplay.getRoot());
    }

    /**
     * Display client.
     */
    private void showClient(Client client) {
        clientDisplay = new ClientDisplay(client);
        meetingListPanelPlaceholder.getChildren().clear();
        meetingListPanelPlaceholder.getChildren().add(clientDisplay.getRoot());
    }

    /**
     * Hide client.
     */
    private void hideClient() {
        meetingListPanelPlaceholder.getChildren().clear();
    }


    /**
     * Display sorted clients.
     */
    private void updateClients() {
        clientListPanelPlaceholder.getChildren().clear();
        clientListPanel = new ClientListPanel(logic.getClientList());
        clientListPanelPlaceholder.getChildren().add(clientListPanel.getRoot());
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

            clientListPanel.setClientCount();

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isShowTutorial()) {
                handleTutorial();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            if (commandResult.isShowMeetings()) {
                showMeetings();
            }

            if (commandResult.isShowClients()) {
                showClients();
            }

            if (commandResult.isSortClients()) {
                updateClients();
                clientListPanel.setSortCriteria(commandResult.getSortCriteria());
            }

            if (commandResult.isShowClient()) {
                showClientByIndex(commandResult.getIndexToShow());
            }

            if (commandResult.isUpdateClient()
                    && logic.getDisplayedClient().equals(commandResult.getClientToUpdate())) {
                showClient(commandResult.getClientToUpdate());
            }

            if (commandResult.isDeleteClient() && logic.getDisplayedClient()
                    .equals(commandResult.getClientToDelete())) {
                hideClient();
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
