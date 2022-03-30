package seedu.ibook.ui.table.item;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import seedu.ibook.model.item.Item;
import seedu.ibook.model.product.Product;
import seedu.ibook.ui.MainWindow;
import seedu.ibook.ui.UiComponent;

/**
 * A card showing the details of an {@code Item}.
 */
public class ItemCard extends UiComponent<HBox> {

    private static final String FXML = "table/item/ItemCard.fxml";

    private final Item item;

    private final int productIndex;
    private final int itemIndex;
    private final Product product;


    @FXML
    private Label indexLabel;
    @FXML
    private Label expiryDate;
    @FXML
    private Label quantity;

    /**
     * Initializes a {@code ProductCard}.
     *
     * @param productIndex Index of the {@code Product}
     * @param itemIndex Index of the {@code Item} in the filtered list.
     * @param product The {@code Product}.
     * @param item The {@code Item}.
     * @param mainWindow The Main Window that this component resides on.
     */
    ItemCard(int productIndex, int itemIndex, Product product,
             Item item, MainWindow mainWindow) {
        super(FXML, mainWindow);
        this.productIndex = productIndex;
        this.itemIndex = itemIndex;
        this.product = product;
        this.item = item;
        populateField();
    }

    private void populateField() {
        indexLabel.setText(productIndex + "-" + itemIndex);
        expiryDate.setText(item.getExpiryDate().toString());
        quantity.setText(item.getQuantity().toString());
    }

    @FXML
    private void handlePopupManageItem() {
        getMainWindow().showPopupManageItem(productIndex, itemIndex, product, item);
    }

}
