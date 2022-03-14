package seedu.contax.ui.onboarding;

import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.contax.logic.parser.*;
import seedu.contax.model.onboarding.OnboardingStep;
import seedu.contax.model.person.Person;
import seedu.contax.model.person.UniquePersonList;
import seedu.contax.ui.PersonListPanel;
import seedu.contax.ui.UiPart;

/**
 * The Onboarding Window. Provides a step-by-step guide for first time users
 * on the basic functions of ContaX.
 */
public class OnboardingWindow extends UiPart<Stage> {

    private static final String FXML = "onboarding/OnboardingWindow.fxml";
    private Stage stage;
    private Stage mainWindow;
    private PersonListPanel personListPanel;
    private OnboardingStoryManager storyManager;
    private Overlay overlay;
    private OnboardingCommandBox commandBox;
    private OnboardingInstruction instructionLabel;
    private UniquePersonList persons;
    private FilteredList<Person> filteredPersons;

    private AddCommandParser p;
    private String errorMessage;

    @FXML
    private StackPane parentPane;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane personListPanelPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    @FXML
    private VBox personList;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private StackPane labelPlaceholder;

    @FXML
    private MenuBar menuBar;

    /**
     * Creates a OnboardingWindow
     * @param root Stage to use as the root of the OnboardingWindow.
     * @param mainWindow The window that OnboardingWindow was opened from
     */
    public OnboardingWindow(Stage root, Stage mainWindow) {
        super(FXML, root);
        stage = root;
        this.mainWindow = mainWindow;
        this.overlay = new Overlay();
        this.commandBox = new OnboardingCommandBox();
        this.instructionLabel = new OnboardingInstruction();
        this.storyManager = new OnboardingStoryManager();
        this.persons = new UniquePersonList();
        OnboardingUtil.populateWithSample(persons);
        filteredPersons = persons.asUnmodifiableObservableList().filtered(null);
        fillInner();
        initStoryManager(storyManager);
    }

    public OnboardingWindow(Stage mainWindow) {
        this(new Stage(), mainWindow);
    }

    /**
     * Shows this window
     */
    public void show() {
        getRoot().show();
        processInstructionPosition(0);
    }

    /**
     * Hides this window
     */
    public void hide() {
        storyManager.reset();
        processStep(storyManager.start());
        clearPersonList();
        mainWindow.show();
        stage.hide();
    }

    /**
     * Set this window size to the given height and width.
     * @param height height to set to
     * @param width width to set to
     */
    public void setSize(double height, double width) {
        getRoot().setHeight(height);
        getRoot().setWidth(width);
    }

    /**
     * Translate the this window to the given x and y.
     * @param x x to translate to
     * @param y y to translate to
     */
    public void translate(double x, double y) {
        getRoot().setX(x);
        getRoot().setY(y);
    }

    /**
     * Fills up the placeholders in this window.
     */
    private void fillInner() {
        labelPlaceholder.getChildren().add(overlay.getRoot());
        labelPlaceholder.getChildren().add(instructionLabel.getRoot());
        personListPanel = new PersonListPanel(filteredPersons);
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    /**
     * Clears the person list
     */
    private void clearPersonList() {
        persons = new UniquePersonList();
        personListPanelPlaceholder.getChildren().remove(0);
        personListPanel = new PersonListPanel(persons.asUnmodifiableObservableList());
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());
    }

    /**
     * Displays an overlay over this window which covers every Node, except the given Node.
     * @param node Node to be excluded from the overlay
     */
    private void showOnly(Region node) {
        overlay.showOnly(node.layoutXProperty(), node.layoutYProperty(),
                node.heightProperty(), node.widthProperty(),
                parentPane.heightProperty(), parentPane.widthProperty());
    }

    /**
     * Displays an overlay over this window which covers every Node.
     */
    private void coverAll() {
        overlay.cover(parentPane.layoutXProperty(), parentPane.layoutYProperty(),
                parentPane.heightProperty(), parentPane.widthProperty());
    }

    /**
     * Initialize the onboarding story manager by processing the first step and settings up event listeners
     * @param mng
     */
    private void initStoryManager(OnboardingStoryManager mng) {
        processStep(storyManager.start());
        parentPane.setOnMouseClicked((event -> {
            OnboardingStep s = storyManager.handleMouseClick();
            if (s != null) {
                processStep(s);
                processInstructionPosition(s.getPositionOption());
            }
        }));

        commandBox.getRoot().setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                OnboardingStep s = storyManager.handleEnter();
                if (s != null) {
                    processStep(s);
                    processInstructionPosition(s.getPositionOption());
                }
            }
        });
    }

    /**
     * Highlights a node in this window the stage based on the given option
     * <br><br>
     * Options are as follows:
     * <br>- 0: clear all highlights
     * <br>- 1: highlight only the command box
     * <br>- 2: highlight only the person list
     * @param option Highlight option
     */
    private void processHighlightOption(int option) {
        switch (option) {
        case 0:
            commandBox.unhighlight();
            commandBox.clear();
            personList.setStyle("-fx-border-width: 0px");
            break;
        case 1:
            commandBox.highlight();
            break;
        case 2:
            commandBox.unhighlight();
            commandBox.clear();
            personList.setStyle("-fx-border-color: yellow; -fx-border-width: 5px");
            break;
        default:
            break;
        }
    }

    /**
     * Displays an overlay on this window, whose position is dependant on the given option
     * <br><br>
     * Options are as follows:
     * <br>- 0: cover all
     * <br>- 1: cover all, showing only the menu bar
     * <br>- 2: cover all, showing only the command box
     * <br>- 3: cover all, showing only the person list
     * @param option the overlay option
     */
    private void processOverlayOption(int option) {
        switch (option) {
        case 0:
            coverAll();
            break;
        case 1:
            showOnly(menuBar);
            break;
        case 2:
            showOnly(commandBoxPlaceholder);
            break;
        case 3:
            showOnly(personList);
            break;
        default:
            break;
        }
    }

    /**
     * Moves the InstructionLabel to a position based on the given option.
     * <br><br>
     * Options are as follows:
     * <br>- 0: center of window
     * <br>- 1: top right of menu bar
     * <br>- 2: top right of command box
     * <br>- 3: top right of result display
     * <br>- 4: middle right of result display
     * @param option Position option
     */
    private void processInstructionPosition(int option) {
        switch (option) {
        case 0:
            instructionLabel.setCenter(stage.heightProperty(), stage.widthProperty());
            break;
        case 1:
            instructionLabel.translate(menuBar.layoutXProperty(), menuBar.layoutYProperty());
            break;
        case 2:
            instructionLabel.translate(commandBoxPlaceholder.layoutXProperty(),
                    commandBoxPlaceholder.layoutYProperty());
            break;
        case 3:
            instructionLabel.translate(resultDisplayPlaceholder.layoutXProperty(),
                    resultDisplayPlaceholder.layoutYProperty());
            break;
        case 4:
            instructionLabel.translate(
                    personList.layoutXProperty().add(0),
                    personList.layoutYProperty().add(
                            resultDisplayPlaceholder.heightProperty().multiply(1.5)
                    ));
            break;
        default:
            break;
        }
    }

    /**
     * Processes an operation on this window based on the given operation id
     * <br><br>
     * Options are as follows:
     * <br>- 0: close this window and open main window
     * <br>- 1: format the given message
     * <br>- 2: find the latest person
     * <br>- 3: format command for enforcement of  user input
     * <br>- 5: remove latest person
     * <br>- 6: list all persons
     */
    private void processOperation(int option, OnboardingStep step) {
        switch (option) {
        case 0:
            hide();
            break;
        case 1:
            instructionLabel.setText(String.format(step.getDisplayMessage(),
                    OnboardingUtil.getLastestPersonName(persons)));
            break;
        case 2:
            instructionLabel.setText(String.format(step.getDisplayMessage(),
                    OnboardingUtil.getLastestPersonName(persons)));
            filteredPersons.setPredicate((p) -> p.isSamePerson(OnboardingUtil.getLatestPerson(persons)));
            break;
        case 3:
            step.setDisplayMessage(String.format(step.getDisplayMessage(),
                    OnboardingUtil.getLastestPersonName(persons)));
            instructionLabel.setText(step.getDisplayMessage());
            step.setCommand(String.format(step.getCommand(),
                    OnboardingUtil.getLastestPersonName(persons)));
            break;
        case 4:
            instructionLabel.setText(String.format(step.getDisplayMessage(),
                    OnboardingUtil.getLastestPersonName(persons)));
            persons.remove(OnboardingUtil.getLatestPerson(persons));
            break;
        case 5:
            filteredPersons.setPredicate(null);
            break;
        default:
            break;
        }
    }

    /**
     * Enforces the user input to be equals to be given step's command.
     * Returns 0 when the user input does not satisfy the given condition, and 1 if it does.
     * @param step step to be checked with
     * @param isCustom if the user input is to be matched with a hard coded string
     * @return an integer denoting the result of check
     */
    private int enforceUserInput(OnboardingStep step, boolean isCustom) {
        if (!commandBox.getText().equals(step.getCommand())) {
            if (commandBox.getText().equals("")) {
                return 0;
            }

            if (!isCustom) {
                instructionLabel.setText("Please type: " + step.getCommand());
                return 0;
            }

            if (!OnboardingUtil.isCommandValid(commandBox.getText(), instructionLabel)) {
                System.out.println("INVALID");
                return 0;
            }
            if (OnboardingUtil.processCommand(step, commandBox.getText(),
                    instructionLabel, persons) == -1) {
                return 0;
            };

            OnboardingStep s = storyManager.getNextStep();
            processStep(s);
            step.setEventType(s.getPositionOption());
        } else {
            OnboardingStep s = storyManager.getNextStep();
            processStep(s);
            step.setEventType(s.getPositionOption());
        }
        return 1;
    }

    /**
     * Process the given onboarding step, and translate it to a set of actions taken in this window
     * @param step the OnboardingStep to be processed
     */
    private void processStep(OnboardingStep step) {
        if (step == null) {
            return;
        }
        String displayMessage = step.getDisplayMessage();
        double messageHeight = step.getMessageHeight();
        double messageWidth = step.getMessageWidth();
        int overlayOption = step.getOverlayOption();
        int highlightOption = step.getHighlightOption();
        int operationId = step.getOperationId();

        instructionLabel.setText(displayMessage);
        instructionLabel.setSize(messageHeight, messageWidth, stage.heightProperty(), stage.widthProperty());
        processOverlayOption(overlayOption);
        processHighlightOption(highlightOption);
        processOperation(operationId, step);

        if (step.getCommand() != null) {
            if (enforceUserInput(step, step.isCommandCustom()) == 0) {
                return;
            }
        }

        storyManager.stepFront();
    }
}
