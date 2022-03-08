package seedu.contax.ui.onboarding;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import seedu.contax.commons.core.LogsCenter;
import seedu.contax.ui.HelpWindow;
import seedu.contax.ui.UiPart;

public class OnboardingPrompt extends UiPart<Stage> {


    private static final String FXML = "onboarding/OnboardingPrompt.fxml";
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

    /**
     * Creates a prompt to onboarding guide
     * @param root stage to use as for root of prompt
     * @param mainWindow stage of the mainWindow
     */
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
     * Creates a new onboarding guide.
     */
    public OnboardingPrompt(Stage mainWindow) {
        this(new Stage(), mainWindow);
    }

    /**
     * Show the onboarding guide prompt
     */
    public void show() {
        logger.fine("Showing onboarding prompt.");
        getRoot().show();
        getRoot().setX(mainWindow.getX() + (mainWindow.getWidth() - getRoot().getWidth()) / 2);
        getRoot().setY(mainWindow.getY() + (mainWindow.getHeight() - getRoot().getHeight()) / 2);
    }

    /**
     * Handles the event when the yes button is clicked
     */
    public void handleYes() {
        onboardingWindow.setSize(mainWindow.getHeight(), mainWindow.getWidth());
        onboardingWindow.translate(mainWindow.getX(), mainWindow.getY());
        onboardingWindow.show();
        mainWindow.hide();
        getRoot().hide();
    }

    /**
     * Handles the event when the no button is clicked
     */
    public void handleNo() {
        getRoot().hide();
    }
}
