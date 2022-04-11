package seedu.unite.ui;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import seedu.unite.commons.core.GuiSettings;
import seedu.unite.commons.core.LogsCenter;
import seedu.unite.logic.Logic;
import seedu.unite.logic.commands.CommandResult;
import seedu.unite.logic.commands.exceptions.CommandException;
import seedu.unite.logic.parser.exceptions.ParseException;
import seedu.unite.model.Model;
import seedu.unite.ui.general.GeneralDisplay;
import seedu.unite.ui.theme.DarkTheme;
import seedu.unite.ui.theme.LightTheme;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private final Stage primaryStage;
    private final Logic logic;
    private final UiManager uiManager;
    // Independent Ui parts residing in this Ui container
    private PersonListPanel personListPanel;
    private ResultDisplay resultDisplay;
    private final HelpWindow helpWindow;
    private final AddTagWindow addTagWindow;
    private final AddProfileWindow addProfileWindow;
    private GeneralDisplay generalDisplay;

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

    @FXML
    private StackPane generalDisplayPlaceholder;

    @FXML
    private Menu addMenu;

    @FXML
    private Menu newTagMenu;

    @FXML
    private Menu themeMenu;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic, UiManager uiManager) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;
        this.uiManager = uiManager;
        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        setAccelerators();

        helpWindow = new HelpWindow();

        addTagWindow = new AddTagWindow(new Stage(), logic);
        addTagWindow.getRoot().initOwner(primaryStage);
        addTagWindow.getRoot().initModality(Modality.WINDOW_MODAL);

        addProfileWindow = new AddProfileWindow(new Stage(), logic);
        addProfileWindow.getRoot().initOwner(primaryStage);
        addProfileWindow.getRoot().initModality(Modality.WINDOW_MODAL);

        addMenu.setVisible(false);
        newTagMenu.setVisible(false);
        themeMenu.setVisible(false);
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
        personListPanel = new PersonListPanel(logic.getFilteredPersonList(), logic, uiManager);
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getUniteFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());

        generalDisplay = new GeneralDisplay(logic, uiManager);
        generalDisplayPlaceholder.getChildren().add(generalDisplay.getRoot());
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
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleAddTag() {
        if (!addTagWindow.isShowing()) {
            addTagWindow.show();
        } else {
            addTagWindow.focus();
        }
    }

    /**
     * Opens the add profile window or focuses on it if it's already opened
     */
    @FXML
    public void handleAddProfile() {
        if (!addProfileWindow.isShowing()) {
            addProfileWindow.show();
        } else {
            addProfileWindow.focus();
        }
    }

    /**
     * Switch the theme to dark theme.
     */
    @FXML
    public void handleSwitchDarkTheme() {
        new DarkTheme().applyTheme(primaryStage, addTagWindow, addProfileWindow);
    }

    /**
     * Switch the theme to light theme.
     */
    @FXML
    public void handleSwitchLightTheme() {
        new LightTheme().applyTheme(primaryStage, addTagWindow, addProfileWindow);
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
        primaryStage.hide();
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.unite.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            Model model = logic.getModel();
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());
            if (model.isMouseUxEnabled()) {
                addMenu.setVisible(true);
                newTagMenu.setVisible(true);
                themeMenu.setVisible(true);
                personListPanel.toggleRightClick(true);
                generalDisplay.getTagList().toggleRightClick(true);
            } else {
                addMenu.setVisible(false);
                newTagMenu.setVisible(false);
                themeMenu.setVisible(false);
                personListPanel.toggleRightClick(false);
                generalDisplay.getTagList().toggleRightClick(false);
            }

            // when the command is a delete command, and the current profile is displaying the person being deleted,
            // the profile will reset and the current selection after deletion is cleared.
            if (model.isRemoveProfile() && generalDisplay.getProfileDisplayPlaceholder().isVisible()
                    && generalDisplay.getProfile().getPerson().isSamePerson(model.getPerson())) {
                generalDisplay.resetProfile();
                personListPanel.getPersonListView().getSelectionModel().clearSelection();
            }

            if (model.isRemoveProfile() && generalDisplay.getTagListPlaceholder().isVisible()) {
                generalDisplay.getTagList().getTagListView().refresh();
            }

            if (model.isShowProfile()) {
                generalDisplay.setProfile(logic.getModel().getPerson());
                personListPanel.getPersonListView().scrollTo(generalDisplay.getProfile().getIndex().getZeroBased());
                personListPanel.getPersonListView().getSelectionModel().select(generalDisplay
                        .getProfile().getIndex().getZeroBased());
            }

            if (model.isShowTagList()) {
                generalDisplay.setTagList(logic.getModel().getTagList());
            }

            if (model.isSwitchTheme()) {
                model.getTheme().applyTheme(primaryStage, addTagWindow, addProfileWindow);
            }

            if (model.isShowGrabResult()) {
                generalDisplay.setGrabResult(model.getGrabResult());
            }

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

    public GeneralDisplay getGeneralDisplay() {
        return this.generalDisplay;
    }

    public PersonListPanel getPersonListPanel() {
        return this.personListPanel;
    }

    public ResultDisplay getResultDisplay() {
        return this.resultDisplay;
    }
}
