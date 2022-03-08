package seedu.contax.ui.onboarding;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import seedu.contax.ui.UiPart;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class OnboardingCommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "onboarding/OnboardingCommandBox.fxml";

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public OnboardingCommandBox() {
        super(FXML);
        commandTextField.setStyle("-fx-border-width: 5px");

    }

    public void highlight() {
        commandTextField.setStyle("-fx-border-color: yellow; -fx-border-width: 5px");
    }

    public void unhighlight() {
        commandTextField.setStyle("-fx-border-color: #383838; -fx-border-width: 5px");
    }

    public void disable() {
        commandTextField.setDisable(true);
    }

    public void enable() {
        commandTextField.setDisable(false);
    }

    public void clear() {
        commandTextField.setText("");
    }

    public String getText() {
        return commandTextField.getText();
    }
}

