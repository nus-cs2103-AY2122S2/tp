package seedu.address.ui;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import javafx.application.Platform;
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
import seedu.address.storage.ReminderPersons;

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
    private MatchWindow matchWindow;
    private FavouriteWindow favouriteWindow;
    private ViewImageWindow viewImageWindow;
    private StatisticsWindow statisticsWindow;
    private int count = 0;
    private ReminderWindow reminderWindow;
    // timer to track & launch reminders
    private Timer timer;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private MenuItem statsMenuItem;

    @FXML
    private MenuItem favouriteMenuItem;

    @FXML
    private MenuItem reminderMenuItem;

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
        matchWindow = new MatchWindow(logic);
        viewImageWindow = new ViewImageWindow(logic);
        favouriteWindow = new FavouriteWindow(logic);
        statisticsWindow = new StatisticsWindow(logic);
        reminderWindow = new ReminderWindow(logic);
        timer = new Timer();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
        setAccelerator(statsMenuItem, KeyCombination.valueOf("F2"));
        setAccelerator(favouriteMenuItem, KeyCombination.valueOf("F3"));
        setAccelerator(reminderMenuItem, KeyCombination.valueOf("F4"));
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
     * Opens the match window or focuses on it if it's already opened.
     */
    @FXML
    public void handleMatch() {
        if (!matchWindow.isShowing()) {
            matchWindow.show();
        } else {
            matchWindow.focus();
        }
    }

    /**
     * Opens the view image window or focuses on it if it's already opened after refreshing its contents.
     */
    @FXML
    public void handleViewImage() {
        viewImageWindow.setup();
        if (!viewImageWindow.isShowing()) {
            viewImageWindow.show();
        } else {
            viewImageWindow.focus();
        }
    }

    /**
     * Opens the favourites window or focuses on it if it's already opened.
     */
    @FXML
    public void handleFavourites() {
        if (!favouriteWindow.isShowing()) {
            favouriteWindow.show();
        } else {
            favouriteWindow.focus();
        }
    }

    /**
     * Opens the reminder window or focuses on it if it's already opened.
     */
    @FXML
    public void handleReminders() {
        if (!reminderWindow.isShowing()) {
            reminderWindow.show();
        } else {
            reminderWindow.focus();
        }
    }

    /**
     * Fills data of pie chart and opens the statistics window.
     */
    @FXML
    public void handleStatistics() {
        statisticsWindow.fillPieChart();
        if (!statisticsWindow.isShowing()) {
            statisticsWindow.show();
        } else {
            statisticsWindow.focus();
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
        matchWindow.hide();
        favouriteWindow.hide();
        viewImageWindow.hide();
        statisticsWindow.hide();
        reminderWindow.hide();
        primaryStage.hide();
        closeReminders();
    }

    public PersonListPanel getPersonListPanel() {
        return personListPanel;
    }

    private void launchReminders() {
        Timer newTimer = new Timer();
        RemindersTask tasks = new RemindersTask();
        // launch the Reminder window only when there are active reminders
        if (!ReminderPersons.getInstance().isEmpty()) {
            newTimer.scheduleAtFixedRate(tasks, 60_000, 60_000);
            // cancel any previous instances of Timer
            timer.cancel();
            // set the newly created Timer
            timer = newTimer;
        // else cancel the recurring Reminder window
        } else {
            timer.cancel();
            newTimer.cancel();
        }
    }

    private void closeReminders() {
        timer.cancel();
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

            if (commandResult.isShowMatch()) {
                handleMatch();
            }


            if (commandResult.isShowImage()) {
                handleViewImage();
            }

            if (commandResult.isShowFavourites()) {
                handleFavourites();
            }

            if (commandResult.isShowStatistics()) {
                handleStatistics();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            if (commandResult.isShowReminders()) {
                launchReminders();
                handleReminders();
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }

    private class RemindersTask extends TimerTask {
        @Override
        public void run() {
            Platform.runLater(() -> {
                showReminders();
            });
        }

        private void showReminders() {
            if (!reminderWindow.isShowing()) {
                reminderWindow.show();
            } else {
                reminderWindow.focus();
            }
        }
    }
}
