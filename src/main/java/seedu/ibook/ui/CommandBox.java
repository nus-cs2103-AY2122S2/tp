package seedu.ibook.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import seedu.ibook.ui.popup.PopupAdd;

/**
 * CommandBox Ui class.
 */
public class CommandBox extends UiPart<HBox> {

    private static final String FXML = "CommandBox.fxml";

    private final MainWindow.CommandExecutor commandExecutor;
    private final PopupAdd popupAdd;

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with a {@code CommandExecutor}
     * and a {@code popupAdd}.
     *
     * @param commandExecutor Function to execute command.
     * @param popupAdd PopupAdd window to create product.
     */
    public CommandBox(MainWindow.CommandExecutor commandExecutor,
                      PopupAdd popupAdd) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        this.popupAdd = popupAdd;
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

        commandExecutor.execute(commandText);
        commandTextField.setText("");
    }

    /**
     * Handles the add product button clicked event.
     */
    @FXML
    private void handleAddProductClicked() {
        if (popupAdd.isShowing()) {
            popupAdd.focus();
        } else {
            popupAdd.show();
        }
    }

}
