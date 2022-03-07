package seedu.contax.ui;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.MenuBar;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.contax.logic.Logic;
import seedu.contax.logic.OnboardingStoryManager;
import seedu.contax.model.OnboardingStep;
import seedu.contax.model.person.UniquePersonList;

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
            if(event.getCode() == KeyCode.ENTER) {
                OnboardingStep s = storyManager.handleEnter();
                if(s != null) {
                    processStep(s);
                    processInstructionPosition(s.getPositionOption());
                }
            }
        });
    }


    public OnboardingWindow(Stage mainWindow) {
        this(new Stage(), mainWindow);
    }

    public void show() {
        fillInner();
        getRoot().show();
        getRoot().centerOnScreen();
        processInstructionPosition(0);
    }

    public void setSize(double height, double width) {
        getRoot().setHeight(height);
        getRoot().setWidth(width);
    }

    public void translate(double x, double y) {
        getRoot().setX(x);
        getRoot().setY(y);
    }

    public void showOnly(Region node) {
        overlay.showOnly(node.layoutXProperty(), node.layoutYProperty(),
                node.heightProperty(), node.widthProperty(),
                parentPane.heightProperty(), parentPane.widthProperty());
    }

    public void coverAll() {
        overlay.cover(parentPane.layoutXProperty(), parentPane.layoutYProperty(),
                parentPane.heightProperty(), parentPane.widthProperty());
    }

    public void fillInner() {
        labelPlaceholder.getChildren().add(overlay.getRoot());

        labelPlaceholder.getChildren().add(instructionLabel.getRoot());
        personListPanel = new PersonListPanel(persons.asUnmodifiableObservableList());
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());

        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    public void processStep(OnboardingStep step) {
        if (step != null) {
            instructionLabel.setText(step.getDisplayMessage());
            instructionLabel.setSize(step.getMessageHeight(), step.getMessageWidth(), stage.heightProperty(), stage.widthProperty());
            processOverlayOption(step.getOverlayOption());
            processHighlightOption(step.getHighlightOption());
            if (step.getPersonOperation() == 1) {
                persons.add(step.getPerson());
            } else if (step.getPersonOperation() == 2) {
                mainWindow.show();
                stage.hide();
            }

            System.out.println(step.getCommand());

            if(step.getCommand() != null) {
                if(!commandBox.getText().equals(step.getCommand())) {
                    if(!commandBox.getText().equals("")) {
                        instructionLabel.setText("Please type: " + step.getCommand());
                    }
                    return;
                } else {
                    OnboardingStep s = storyManager.getNextStory();
                    processStep(s);
                    step.setEventType(s.getPositionOption());
                }
            }
            storyManager.stepFront();
        }

    }

    public void processHighlightOption(int option) {
        if(option == 0) {
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

    public void processOverlayOption(int option) {
        if(option == 0) {
            coverAll();
        } else if (option == 1) {
            showOnly(menuBar);
        } else if (option == 2) {
            showOnly(commandBoxPlaceholder);
        } else if (option == 3) {
            showOnly(personList);
        }
    }

    public void processInstructionPosition(int option) {
        if (option == 0) {
            instructionLabel.setCenter(stage.heightProperty(), stage.widthProperty());
        } else if (option == 1) {
            instructionLabel.translate(menuBar.layoutXProperty(), menuBar.layoutYProperty());
        } else if (option == 2) {
            instructionLabel.translate(commandBoxPlaceholder.layoutXProperty(), commandBoxPlaceholder.layoutYProperty());
        } else if (option == 3) {
            instructionLabel.translate(resultDisplayPlaceholder.layoutXProperty(), resultDisplayPlaceholder.layoutYProperty());
        }
    }
}
