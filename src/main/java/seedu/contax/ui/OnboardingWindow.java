package seedu.contax.ui;

import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.contax.logic.Logic;

public class OnboardingWindow extends UiPart<Stage> {

    private static final String FXML = "OnboardingWindow.fxml";
    private Overlay overlay;
    private OnboardingCommandBox commandBox;

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


    public OnboardingWindow(Stage root) {
        super(FXML, root);
        this.overlay = new Overlay();
        this.commandBox = new OnboardingCommandBox();
    }

    public OnboardingWindow() {
        this(new Stage());
    }

    public void show() {
        fillInner();
        getRoot().show();
        getRoot().centerOnScreen();
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
        parentPane.getChildren().add(overlay.getRoot());
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }
}
