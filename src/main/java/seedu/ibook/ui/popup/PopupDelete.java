package seedu.ibook.ui.popup;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seedu.ibook.logic.commands.DeleteCommand;
import seedu.ibook.model.product.Product;
import seedu.ibook.ui.MainWindow;

public class PopupDelete extends Popup {

    private static final String FXML = "popup/PopupDelete.fxml";

    private int index;

    @FXML
    private Label message;

    public PopupDelete(MainWindow.CommandExecutor commandExecutor) {
        super(FXML, commandExecutor);
    }

    /**
     * Show the popup window.
     * @param index Index of the product.
     * @param product The product to be deleted.
     */
    public void show(int index, Product product) {
        super.show();
        this.index = index;
        message.setText("Are you sure you want to delete " + product.getName() + "?");
    }

    @FXML
    private void handleDeleteProduct() {
        String commandText = DeleteCommand.COMMAND_WORD + " " + index;
        execute(commandText);
    }
}
