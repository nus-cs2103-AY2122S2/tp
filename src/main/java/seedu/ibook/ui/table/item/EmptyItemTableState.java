package seedu.ibook.ui.table.item;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import seedu.ibook.model.product.Product;
import seedu.ibook.ui.MainWindow;
import seedu.ibook.ui.UiComponent;

/**
 * The state of {@code ItemTable} when it is empty.
 */
public class EmptyItemTableState extends UiComponent<VBox> {

    private static final String FXML = "table/item/EmptyItemTableState.fxml";

    private final int productIndex;
    private final Product product;

    /**
     * Initializes the empty state of {@code ItemTable}.
     *
     * @param productIndex Index of the {@code Product}
     * @param product The {@code Product}.
     * @param mainWindow The {@code MainWindow} that this component resides on.
     */
    public EmptyItemTableState(int productIndex, Product product, MainWindow mainWindow) {
        super(FXML, mainWindow);
        this.productIndex = productIndex;
        this.product = product;
    }

    /**
     * Handles the add item button clicked event.
     */
    @FXML
    private void handleAddItemClicked() {
        getMainWindow().showPopupAddItem(productIndex, product);
    }
}
