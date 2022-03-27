package seedu.contax.ui;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.contax.commons.core.GuiListContentType;
import seedu.contax.commons.core.GuiSettings;
import seedu.contax.commons.core.LogsCenter;
import seedu.contax.logic.Logic;
import seedu.contax.logic.commands.CommandResult;
import seedu.contax.logic.commands.exceptions.CommandException;
import seedu.contax.logic.parser.exceptions.ParseException;
import seedu.contax.model.person.Person;
import seedu.contax.model.tag.Tag;
import seedu.contax.ui.appointment.AppointmentListPanel;
import seedu.contax.ui.onboarding.OnboardingPrompt;

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
    private ListPanel<Person> personListPanel;
    private AppointmentListPanel appointmentListPanel;
    private ListPanel<Tag> tagListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private StatusBarFooter statusBarFooter;

    private OnboardingPrompt onboardingPrompt;
    // Flag indicating the type of model currently being displayed in the contentList
    private GuiListContentType currentListType;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private MenuItem onboardingMenuItem;

    @FXML
    private TabPane tabbedPanelPlaceholder;

    @FXML
    private Tab appointmentTab;

    @FXML
    private Tab tagTab;

    @FXML
    private Tab personTab;

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
        onboardingPrompt = new OnboardingPrompt(primaryStage);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
        setAccelerator(onboardingMenuItem, KeyCombination.valueOf("F2"));
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
        statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        personListPanel = new ListPanel<>(logic.getFilteredPersonList(), PersonCard::new);
        appointmentListPanel = new AppointmentListPanel(logic.getScheduleItemList());
        tagListPanel = new ListPanel<>(logic.getFilteredTagList(), TagCard::new);

        fillTabsFromPanels();

        changeTabFocus(GuiListContentType.PERSON);

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    /**
     * Creates the tabs and sets it in the Tab Pane.
     */
    private void fillTabsFromPanels() {
        appointmentTab.setContent(appointmentListPanel.getRoot());
        personTab.setContent(personListPanel.getRoot());
        tagTab.setContent(tagListPanel.getRoot());
        tabbedPanelPlaceholder.getTabs().add(personTab);
        tabbedPanelPlaceholder.getTabs().add(appointmentTab);
        tabbedPanelPlaceholder.getTabs().add(tagTab);

        // Event listener to synchronise selected tab when user clicks on it
        tabbedPanelPlaceholder.getSelectionModel().selectedItemProperty().addListener((
                observableValue, oldTab, newTab) -> {
            if (newTab.equals(appointmentTab)) {
                currentListType = GuiListContentType.APPOINTMENT;
            } else if (newTab.equals(personTab)) {
                currentListType = GuiListContentType.PERSON;
            } else if (newTab.equals(tagTab)) {
                currentListType = GuiListContentType.TAG;
            }
        });
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
     * Changes the type of model being displayed in the tab pane.
     *
     * @param contentType The specified tab the UI should display.
     * */
    private void changeTabFocus(GuiListContentType contentType) {
        if (contentType.equals(GuiListContentType.UNCHANGED) || contentType.equals(currentListType)) {
            return;
        }

        currentListType = contentType;

        if (contentType == GuiListContentType.PERSON) {
            tabbedPanelPlaceholder.getSelectionModel().select(0);
        } else if (contentType == GuiListContentType.APPOINTMENT) {
            tabbedPanelPlaceholder.getSelectionModel().select(1);
        } else if (contentType == GuiListContentType.TAG) {
            tabbedPanelPlaceholder.getSelectionModel().select(2);
        }
        changeFooterContentType(contentType);

    }

    /**
     * Changes the footer save file location path to the corresponding file path for the type of model being
     * displayed in the content list.
     *
     * @param contentType The type of content the UI is currently displaying.
     */
    private void changeFooterContentType(GuiListContentType contentType) {
        if (contentType == GuiListContentType.PERSON || contentType == GuiListContentType.TAG) {
            statusBarFooter.setSaveLocation(logic.getAddressBookFilePath());
        } else if (contentType == GuiListContentType.APPOINTMENT) {
            statusBarFooter.setSaveLocation(logic.getScheduleFilePath());
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
     * Sets and opens the onboarding guide window and hides the main window
     */
    @FXML
    public void handleOnboarding() {
        onboardingPrompt.show();
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

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.contax.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());
            changeTabFocus(commandResult.getUiContentType());

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
