package seedu.contax.ui.onboarding;

import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.contax.model.OnboardingStep;
import seedu.contax.model.person.UniquePersonList;
import seedu.contax.ui.PersonListPanel;
import seedu.contax.ui.UiPart;

public class OnboardingWindow extends UiPart<Stage> {

    private static final String FXML = "OnboardingWindow.fxml";
    private Stage stage;
    private Stage mainWindow;
    private PersonListPanel personListPanel;
    private OnboardingStoryManager storyManager;
    private Overlay overlay;
    private OnboardingCommandBox commandBox;
    private OnboardingInstruction instructionLabel;
    private UniquePersonList persons;

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
        labelPlaceholder.setManaged(false);
        stage = root;
        this.mainWindow = mainWindow;
        this.overlay = new Overlay();
        this.commandBox = new OnboardingCommandBox();
        this.instructionLabel = new OnboardingInstruction();
        this.storyManager = new OnboardingStoryManager();
        this.persons = new UniquePersonList();
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


    public OnboardingWindow(Stage mainWindow) {
        this(new Stage(), mainWindow);
    }

    /**
     * Shows the Onboarding window.
     */
    public void show() {
        fillInner();
        getRoot().show();
        getRoot().centerOnScreen();
        processInstructionPosition(0);
    }

    /**
     * Set the size of Onboarding window
     * @param height Height to set to
     * @param width Width to set to
     */
    public void setSize(double height, double width) {
        getRoot().setHeight(height);
        getRoot().setWidth(width);
    }

    /**
     * Translate Onboarding window
     * @param x x to translate to
     * @param y y to translate to
     */
    public void translate(double x, double y) {
        getRoot().setX(x);
        getRoot().setY(y);
    }

    /**
     * Cast an overlap over the stage, except for the given node.
     * @param node Node to be excluded from overlap
     */
    public void showOnly(Region node) {
        overlay.showOnly(node.layoutXProperty(), node.layoutYProperty(),
                node.heightProperty(), node.widthProperty(),
                parentPane.heightProperty(), parentPane.widthProperty());
    }

    /**
     * Cast an overlap over the stage
     */
    public void coverAll() {
        overlay.cover(parentPane.layoutXProperty(), parentPane.layoutYProperty(),
                parentPane.heightProperty(), parentPane.widthProperty());
    }

    /**
     * Fills up the placeholders in this window.
     */
    public void fillInner() {
        labelPlaceholder.getChildren().add(overlay.getRoot());

        labelPlaceholder.getChildren().add(instructionLabel.getRoot());
        personListPanel = new PersonListPanel(persons.asUnmodifiableObservableList());
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());

        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    /**
     * Process the given OnboardingStep and translate it to stage
     * @param step The OnboardingStep to be processed
     */
    public void processStep(OnboardingStep step) {
        if (step != null) {
            instructionLabel.setText(step.getDisplayMessage());
            instructionLabel.setSize(step.getMessageHeight(),
                    step.getMessageWidth(), stage.heightProperty(), stage.widthProperty());
            processOverlayOption(step.getOverlayOption());
            processHighlightOption(step.getHighlightOption());
            if (step.getPersonOperation() == 1) {
                persons.add(step.getPerson());
            } else if (step.getPersonOperation() == 2) {
                mainWindow.show();
                stage.hide();
            }

            if (step.getCommand() != null) {
                if (!commandBox.getText().equals(step.getCommand())) {
                    if (!commandBox.getText().equals("")) {
                        instructionLabel.setText("Please type: " + step.getCommand());
                    }
                    return;
                } else {
                    OnboardingStep s = storyManager.getNextStep();
                    processStep(s);
                    step.setEventType(s.getPositionOption());
                }
            }
            storyManager.stepFront();
        }

    }

    /**
     * Highlight the stage based on the given option
     * @param option Highlight option
     */
    public void processHighlightOption(int option) {
        if (option == 0) {
            commandBox.unhighlight();
            commandBox.clear();
            personList.setStyle("-fx-border-width: 0px");
        } else if (option == 1) {
            commandBox.highlight();
        } else if (option == 2) {
            commandBox.unhighlight();
            commandBox.clear();
            personList.setStyle("-fx-border-color: yellow; -fx-border-width: 5px");
        }
    }

    /**
     * Overlay the stage based on the given option
     * @param option Overlay option
     */
    public void processOverlayOption(int option) {
        if (option == 0) {
            coverAll();
        } else if (option == 1) {
            showOnly(menuBar);
        } else if (option == 2) {
            showOnly(commandBoxPlaceholder);
        } else if (option == 3) {
            showOnly(personList);
        }
    }

    /**
     * Moves the InstructionLabel based on the given option
     * @param option Position option
     */
    public void processInstructionPosition(int option) {
        if (option == 0) {
            instructionLabel.setCenter(stage.heightProperty(), stage.widthProperty());
        } else if (option == 1) {
            instructionLabel.translate(menuBar.layoutXProperty(), menuBar.layoutYProperty());
        } else if (option == 2) {
            instructionLabel.translate(commandBoxPlaceholder.layoutXProperty(),
                    commandBoxPlaceholder.layoutYProperty());
        } else if (option == 3) {
            instructionLabel.translate(resultDisplayPlaceholder.layoutXProperty(),
                    resultDisplayPlaceholder.layoutYProperty());
        } else if (option == 4) {
            System.out.println(resultDisplayPlaceholder.getMaxHeight());
            instructionLabel.translate(
                    personList.layoutXProperty().add(0),
                    personList.layoutYProperty().add(
                            resultDisplayPlaceholder.heightProperty().multiply(1.5)
                    ));
        }
    }
}
