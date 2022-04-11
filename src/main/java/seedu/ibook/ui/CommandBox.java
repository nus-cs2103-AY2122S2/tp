package seedu.ibook.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * CommandBox Ui class.
 */
public class CommandBox extends UiComponent<TextField> {

    private static final String FXML = "CommandBox.fxml";

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox}.
     *
     * @param mainWindow The {@code MainWindow} that this component resides on.
     */
    public CommandBox(MainWindow mainWindow) {
        super(FXML, mainWindow);
    }

    /**
     * Handles the Enter button pressed event in the command line.
     */
    @FXML
    private void handleCommandEntered() {
        String commandText = commandTextField.getText();
        if (commandText.equals("")) {
            return;
        }

        getMainWindow().hidePopup();
        getMainWindow().executeCommand(commandText);
        commandTextField.setText("");
    }

}
