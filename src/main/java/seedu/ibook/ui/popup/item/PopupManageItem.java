package seedu.ibook.ui.popup.item;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import seedu.ibook.commons.core.index.CompoundIndex;
import seedu.ibook.logic.commands.DeleteItemCommand;
import seedu.ibook.logic.commands.UpdateItemCommand;
import seedu.ibook.logic.parser.CliSyntax;
import seedu.ibook.model.item.Item;
import seedu.ibook.model.product.Product;
import seedu.ibook.ui.MainWindow;
import seedu.ibook.ui.popup.Popup;

public class PopupManageItem extends Popup {

    private static final String FXML = "popup/item/PopupManageItem.fxml";

    private int productIndex;
    private int itemIndex;

    @FXML
    private Label name;
    @FXML
    private TextField expiryDate;
    @FXML
    private TextField quantity;

    /**
     * Initializes a new {@code PopupUpdateItem} window.
     *
     * @param mainWindow The {@code MainWindow} that this component resides on.
     */
    public PopupManageItem(MainWindow mainWindow) {
        super(FXML, mainWindow);
    }

    /**
     * Shows the popup window.
     *
     * @param productIndex Index of the product.
     * @param itemIndex Index of the item.
     * @param product The product of the item.
     * @param item The Item.
     */
    public void show(int productIndex, int itemIndex, Product product, Item item) {
        super.show();
        this.productIndex = productIndex;
        this.itemIndex = itemIndex;
        name.setText(product.getName().toString());
        expiryDate.setText(item.getExpiryDate().toString());
        quantity.setText(item.getQuantity().toString());
    }

    @FXML
    private void handleUpdateItem() {
        String commandText = UpdateItemCommand.COMMAND_WORD
                + " " + productIndex + CompoundIndex.SEPARATOR + itemIndex
                + " " + CliSyntax.PREFIX_EXPIRY_DATE.getPrefix()
                + expiryDate.getText()
                + " " + CliSyntax.PREFIX_QUANTITY.getPrefix()
                + quantity.getText();

        execute(commandText);
    }

    @FXML
    private void handleDeleteItem() {
        String commandText = DeleteItemCommand.COMMAND_WORD
                + " " + productIndex + CompoundIndex.SEPARATOR + itemIndex;

        execute(commandText);
    }
}
