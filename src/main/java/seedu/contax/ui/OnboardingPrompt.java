package seedu.contax.ui;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import seedu.contax.commons.core.LogsCenter;

import java.util.logging.Logger;

public class OnboardingPrompt extends UiPart<Stage> {


    private static final String FXML = "OnboardingPrompt.fxml";
    private static final String DISPLAY_MESSAGE = "Welcome to ContaX!\nWill you like to take a quick tour?";
    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);

    private Stage mainWindow;

    private OnboardingWindow onboardingWindow;

    @FXML
    private Label displayMessage;

    @FXML
    private Button yesButton;

    @FXML
    private Button noButton;


    public OnboardingPrompt(Stage root, Stage mainWindow) {
        super(FXML, root);
        displayMessage.setText(DISPLAY_MESSAGE);
        this.mainWindow = mainWindow;
        onboardingWindow = new OnboardingWindow(mainWindow);
        displayMessage.setMinHeight(Region.USE_PREF_SIZE);
        displayMessage.minHeightProperty().bind(mainWindow.heightProperty().multiply(0.1));
        displayMessage.minWidthProperty().bind(mainWindow.widthProperty().multiply(0.3));


        displayMessage.setAlignment(Pos.CENTER);
    }

    /**
     * Creates a new HelpWindow.
     */
    public OnboardingPrompt(Stage mainWindow) {
        this(new Stage(), mainWindow);
    }

    public void show() {
        logger.fine("Showing onboarding prompt.");
        getRoot().show();
//        getRoot().centerOnScreen();
        getRoot().setX(mainWindow.getX() + (mainWindow.getWidth() - getRoot().getWidth()) / 2);
        getRoot().setY(mainWindow.getY() + (mainWindow.getHeight() - getRoot().getHeight()) / 2);
    }


    public void handleYes() {
        onboardingWindow.setSize(mainWindow.getHeight(), mainWindow.getWidth());
        onboardingWindow.translate(mainWindow.getX(), mainWindow.getY());
        onboardingWindow.show();
        mainWindow.hide();
        getRoot().hide();
    }

    public void handleNo() {
        getRoot().hide();
    }
}
