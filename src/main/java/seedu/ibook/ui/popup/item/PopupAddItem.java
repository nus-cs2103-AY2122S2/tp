package seedu.ibook.ui.popup.item;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import seedu.ibook.logic.commands.AddItemCommand;
import seedu.ibook.logic.parser.CliSyntax;
import seedu.ibook.model.product.Product;
import seedu.ibook.ui.MainWindow;
import seedu.ibook.ui.popup.Popup;

/**
 * Popup responsible to add item.
 */
public class PopupAddItem extends Popup {

    private static final String FXML = "popup/item/PopupAddItem.fxml";

    private int productIndex;

    @FXML
    private Label name;
    @FXML
    private TextField expiryDate;
    @FXML
    private TextField quantity;

    /**
     * Initializes a new {@code PopupAddItem} window.
     *
     * @param mainWindow The {@code MainWindow} that this component resides on.
     */
    public PopupAddItem(MainWindow mainWindow) {
        super(FXML, mainWindow);
    }

    /**
     * Shows the popup window.
     *
     * @param productIndex Index of the product.
     * @param product The product of the item.
     */
    public void show(int productIndex, Product product) {
        super.show();
        this.productIndex = productIndex;
        name.setText(product.getName().toString());
        expiryDate.setText("");
        quantity.setText("");
    }

    @FXML
    private void handleAddItem() {
        String commandText = AddItemCommand.COMMAND_WORD
                + " " + productIndex
                + " " + CliSyntax.PREFIX_EXPIRY_DATE.getPrefix()
                + expiryDate.getText()
                + " " + CliSyntax.PREFIX_QUANTITY.getPrefix()
                + quantity.getText();

        execute(commandText);
    }

}
