package seedu.ibook.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class CommandBox extends UiPart<HBox> {

    private static final String FXML = "CommandBox.fxml";

    private final MainWindow.CommandExecutor commandExecutor;
    private final MainWindow.Popup popup;

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(MainWindow.CommandExecutor commandExecutor,
                      MainWindow.Popup popup) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        this.popup = popup;
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandEntered() {
        String commandText = commandTextField.getText();
        if (commandText.equals("")) {
            return;
        }

        commandExecutor.execute(commandText);
        commandTextField.setText("");
    }

    @FXML
    private void openPopup() {
        popup.show();
    }

}
