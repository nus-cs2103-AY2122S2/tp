package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
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
import seedu.address.logic.commands.misc.InfoPanelTypes;
import seedu.address.logic.commands.misc.ViewTab;
import seedu.address.logic.inputhistory.InputHistoryResult;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.student.Student;
import seedu.address.ui.infopanel.InfoPanel;
import seedu.address.ui.infopanel.LessonInfoPanel;
import seedu.address.ui.infopanel.RecurringLessonInfoPanel;
import seedu.address.ui.infopanel.StudentInfoPanel;
import seedu.address.ui.listpanel.LessonListPanel;
import seedu.address.ui.listpanel.ListPanel;
import seedu.address.ui.listpanel.StudentListPanel;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";
    private static final double MAX_DIVIDER_POSITION = 0.5;

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private LessonListPanel lessonListPanel;
    private StudentListPanel studentListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private InfoPanel infoPanel;

    @FXML
    private StackPane commandBoxPlaceholder;
    @FXML
    private MenuItem helpMenuItem;
    @FXML
    private StackPane lessonListPanelPlaceholder;
    @FXML
    private StackPane studentListPanelPlaceholder;
    @FXML
    private StackPane infoPanelPlaceholder;
    @FXML
    private StackPane resultDisplayPlaceholder;
    @FXML
    private StackPane statusbarPlaceholder;
    @FXML
    private TabPane listPane;
    @FXML
    private Tab studentTab;
    @FXML
    private Tab lessonTab;
    @FXML
    private SplitPane splitPane;

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

        // Set max size for left list to 50% of the window
        listPane.maxWidthProperty().bind(splitPane.widthProperty().multiply(MAX_DIVIDER_POSITION));
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
        updateAndPopulateLessonList();
        updateAndPopulateStudentList();

        createResultDisplay();
        createStatusBarFooter();
        createCommandBox();
    }

    private void createResultDisplay() {
        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());
    }

    private void createStatusBarFooter() {
        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getStudentBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());
    }

    private void createCommandBox() {
        CommandBox commandBox = new CommandBox(this::executeCommand, this::getPreviousUserInput,
                this::getNextUserInput);
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

    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        saveGuiSettings();
        helpWindow.hide();
        primaryStage.hide();
    }

    private void saveGuiSettings() {
        double windowWidth = primaryStage.getWidth();
        double windowHeight = primaryStage.getHeight();
        int windowXPos = (int) primaryStage.getX();
        int windowYPos = (int) primaryStage.getY();
        GuiSettings guiSettings = new GuiSettings(windowWidth, windowHeight, windowXPos, windowYPos);
        logic.setGuiSettings(guiSettings);
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
            updateAndPopulateLessonList();
            updateAndPopulateStudentList();
            if (!commandResult.toggleTo().equals(ViewTab.NONE)) {
                toggleTab(commandResult.toggleTo());
            }

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            if (commandResult.isUpdateInfoPanel()) {
                InfoPanelTypes infoPanelType = commandResult.getInfoPanelType();
                handleInfoPanelUpdate(infoPanelType);
            }

            addNewUserInputToHistory(commandText);

            return commandResult;
        } catch (CommandException commandException) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(commandException.getMessage());

            if (!commandException.toggleTo().equals(ViewTab.NONE)) {
                toggleTab(commandException.toggleTo());
            }

            throw commandException;
        } catch (ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());

            throw e;
        }
    }

    /**
     * Toggles to the tab provided by the enum value.
     * @param toggleTo Provided {@code ViewTab} to toggle to
     */
    public void toggleTab(ViewTab toggleTo) {
        requireNonNull(toggleTo);
        if (toggleTo.equals(ViewTab.LESSON)) {
            logger.info("Toggling to Lesson Tab");
            toggleLessonTab();
        } else if (toggleTo.equals(ViewTab.STUDENT)) {
            logger.info("Toggling to Student Tab");
            toggleStudentTab();
        } else {
            logger.severe("Something went wrong when toggling the tabs");
            assert false;
        }
    }

    /**
     * Toggles to student tab.
     */
    public void toggleStudentTab() {
        listPane.getSelectionModel().select(studentTab);
    }

    /**
     * Toggles to student tab.
     */
    public void toggleLessonTab() {
        listPane.getSelectionModel().select(lessonTab);
    }

    // User Input History methods
    private void addNewUserInputToHistory(String userInput) {
        logic.addNewUserInputToHistory(userInput);
    }

    private InputHistoryResult getPreviousUserInput() {
        return logic.getPreviousInput();
    }

    private InputHistoryResult getNextUserInput() {
        return logic.getNextInput();
    }

    // List Panel Methods
    private void updateAndPopulateStudentList() {
        populateListPanelWithStudents(logic.getFilteredStudentList());
    }

    private void updateAndPopulateLessonList() {
        populateListPanelWithLessons(logic.getFilteredLessonList());
    }

    private void populateListPanelWithLessons(ObservableList<Lesson> list) {
        lessonListPanel = new LessonListPanel(list);
        populateLessonListPanel(lessonListPanel);
    }

    private void populateLessonListPanel(ListPanel newListPanel) {
        lessonListPanelPlaceholder.getChildren().add(newListPanel.getRoot());
    }

    private void populateListPanelWithStudents(ObservableList<Student> list) {
        studentListPanel = new StudentListPanel(list);
        populateStudentListPanel(studentListPanel);
    }

    private void populateStudentListPanel(ListPanel newListPanel) {
        studentListPanelPlaceholder.getChildren().add(newListPanel.getRoot());
    }

    // Info Panel Methods
    /**
     * Updates the InfoPanel.
     */
    private void handleInfoPanelUpdate(InfoPanelTypes infoPanelTypes) {
        requireNonNull(infoPanelTypes);
        switch (infoPanelTypes) {
        case STUDENT:
            logger.info("Updating InfoPanel with selected student");
            Student selectedStudent = logic.getSelectedStudent();
            populateInfoPanelWithStudent(selectedStudent);
            break;
        case LESSON:
            logger.info("Updating InfoPanel with selected lesson");
            Lesson selectedLesson = logic.getSelectedLesson();
            populateInfoPanelWithLesson(selectedLesson);
            break;
        case EMPTY:
            logger.info("Clearing InfoPanel");
            clearInfoPanel();
            break;
        case REFRESH:
            logger.info("Refreshing InfoPanel");
            if (!infoPanelPlaceholder.getChildren().isEmpty()) {
                refreshInfoPanel();
            }
            break;
        default:
            logger.severe("Something went wrong with handling the InfoPanels");
            assert false;
        }
    }

    private void clearInfoPanel() {
        infoPanelPlaceholder.getChildren().clear();
    }

    private void refreshInfoPanel() {
        requireNonNull(infoPanel);
        if (infoPanel instanceof RecurringLessonInfoPanel || infoPanel instanceof LessonInfoPanel) {
            Lesson lesson = logic.getSelectedLesson();
            populateInfoPanelWithLesson(lesson);
        } else if (infoPanel instanceof StudentInfoPanel) {
            Student student = logic.getSelectedStudent();
            populateInfoPanelWithStudent(student);
        } else {
            logger.severe("Something went wrong when refreshing the InfoPanel");
            assert false;
        }
    }

    private void populateInfoPanelWithStudent(Student selectedStudent) {
        requireNonNull(selectedStudent);
        infoPanel = new StudentInfoPanel(selectedStudent);
        StudentInfoPanel studentInfoPanel = (StudentInfoPanel) this.infoPanel;
        populateInfoPanel(studentInfoPanel);
    }

    private void populateInfoPanelWithLesson(Lesson selectedLesson) {
        requireNonNull(selectedLesson);
        if (selectedLesson.isRecurring()) {
            infoPanel = new RecurringLessonInfoPanel(selectedLesson);
            RecurringLessonInfoPanel lessonInfoPanel = (RecurringLessonInfoPanel) this.infoPanel;
            populateInfoPanel(lessonInfoPanel);
            return;
        } else if (!selectedLesson.isRecurring()) {
            infoPanel = new LessonInfoPanel(selectedLesson);
            LessonInfoPanel lessonInfoPanel = (LessonInfoPanel) this.infoPanel;
            populateInfoPanel(lessonInfoPanel);
            return;
        }
        logger.severe("Something went wrong when populating InfoPanel with Lesson");
        assert false;
    }

    private void populateInfoPanel(InfoPanel newInfoPanel) {
        infoPanelPlaceholder.getChildren().clear();
        infoPanelPlaceholder.getChildren().add(newInfoPanel.getRoot());
    }
}
