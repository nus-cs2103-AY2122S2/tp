package seedu.contax.ui.onboarding;

import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.contax.model.Model;
import seedu.contax.model.ModelManager;
import seedu.contax.model.onboarding.OnboardingStep;
import seedu.contax.model.onboarding.OnboardingStory;
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
    private Model model;

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
        this.model = new ModelManager();
        OnboardingUtil.populateWithSample(model);
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
        processInstructionPosition(OnboardingStory.PositionOption.CENTER);
    }

    /**
     * Hides this window
     */
    public void hide() {
        storyManager.reset();
        processStep(storyManager.start());
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
        personListPanel = new PersonListPanel(model.getFilteredPersonList());
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    /**
     * Displays an overlay over this window which covers every Node, except the given Node.
     * <br><br>
     * Option as follows:
     * <br>- Overlay.showOverlay.BOTH: both overlays visible
     * <br>- Overlay.showOverlay.BOTTOM: shows only the bottom overlay
     * <br>- Overlay.showOverlay.TOP: shows only the top overlay
     * @param node Node to be excluded from the overlay
     */
    private void showOnly(Region node, Overlay.ShowOverlay option) {
        overlay.showOnly(node.layoutXProperty(), node.layoutYProperty(),
                node.heightProperty(), node.widthProperty(),
                parentPane.heightProperty(), parentPane.widthProperty(), option);
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
     * <br>- OnboardingStory.HighlightOption.CLEAR_ALL: clear all highlights
     * <br>- OnboardingStory.HighlightOption.COMMAND_BOX: highlight only the command box
     * <br>- OnboardingStory.HighlightOption.PERSON_LIST: highlight only the person list
     * @param option Highlight option
     */
    private void processHighlightOption(OnboardingStory.HighlightOption option) {
        switch (option) {
        case CLEAR_ALL:
            commandBox.unhighlight();
            commandBox.clear();
            personList.setStyle("-fx-border-width: 0px");
            break;
        case COMMAND_BOX:
            commandBox.highlight();
            break;
        case PERSON_LIST:
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
     * <br>- OnboardingStory.OverlayOption.ALL: cover all
     * <br>- OnboardingStory.OverlayOption.SHOW_MENU_BAR: cover all, showing only the menu bar
     * <br>- OnboardingStory.OverlayOption.SHOW_COMMAND_BOX: cover all, showing only the command box
     * <br>- OnboardingStory.OverlayOption.SHOW_PERSON_LIST: cover all, showing only the person list
     * @param option the overlay option
     */
    private void processOverlayOption(OnboardingStory.OverlayOption option) {
        switch (option) {
        case ALL:
            coverAll();
            break;
        case SHOW_MENU_BAR:
            showOnly(menuBar, Overlay.ShowOverlay.BOTH);
            break;
        case SHOW_COMMAND_BOX:
            showOnly(commandBoxPlaceholder, Overlay.ShowOverlay.BOTH);
            break;
        case SHOW_PERSON_LIST:
            showOnly(personList, Overlay.ShowOverlay.TOP);
            break;
        default:
            break;
        }
    }

    /**
     * Moves the InstructionLabel to a position based on the given option.
     * <br><br>
     * Options are as follows:
     * <br>- OnboardingStory.PositionOption.CENTER: center of window
     * <br>- OnboardingStory.PositionOption.MENU_BAR_TOP: top right of menu bar
     * <br>- OnboardingStory.PositionOption.COMMAND_BOX_TOP: top right of command box
     * <br>- OnboardingStory.PositionOption.RESULT_DISPLAY_TOP: top right of result display
     * <br>- OnboardingStory.PositionOption.PERSON_LIST_MIDDLE: middle right of result display
     * @param option Position option
     */
    private void processInstructionPosition(OnboardingStory.PositionOption option) {
        switch (option) {
        case CENTER:
            instructionLabel.setCenter(stage.heightProperty(), stage.widthProperty());
            break;
        case MENU_BAR_TOP:
            instructionLabel.translate(menuBar.layoutXProperty(), menuBar.layoutYProperty());
            break;
        case COMMAND_BOX_TOP:
            instructionLabel.translate(commandBoxPlaceholder.layoutXProperty(),
                    commandBoxPlaceholder.layoutYProperty());
            break;
        case RESULT_DISPLAY_TOP:
            instructionLabel.translate(resultDisplayPlaceholder.layoutXProperty(),
                    resultDisplayPlaceholder.layoutYProperty());
            break;
        case PERSON_LIST_MIDDLE:
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

        if (storyManager.isAtlast()) {
            hide();
            return;
        }

        instructionLabel.setText(step.getDisplayMessage());
        instructionLabel.setSize(step.getMessageHeight(), step.getMessageWidth(),
                stage.heightProperty(), stage.widthProperty());
        processOverlayOption(step.getOverlayOption());
        processHighlightOption(step.getHighlightOption());

        if (step.getCommandInstruction() != null) {
            String errorMessage = step.getCommandInstruction().apply(model, commandBox);
            if (errorMessage != null) {
                instructionLabel.setText(errorMessage);
                return;
            }
        }

        if (step.getLabelInstruction() != null) {
            instructionLabel.setText(step.getLabelInstruction().apply(model));
        }

        if (step.getCommand() != null && enforceUserInput(step, step.isCommandCustom()) == 0) {
                return;
        }
        storyManager.stepFront();
    }
}
