package seedu.ibook.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * CommandBox Ui class.
 */
public class CommandBox extends UiComponent<HBox> {

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

        getMainWindow().executeCommand(commandText);
        commandTextField.setText("");
    }

}
