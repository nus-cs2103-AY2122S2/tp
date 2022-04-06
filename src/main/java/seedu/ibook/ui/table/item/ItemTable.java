package seedu.ibook.ui.table.item;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import seedu.ibook.model.item.Item;
import seedu.ibook.model.product.Product;
import seedu.ibook.ui.MainWindow;
import seedu.ibook.ui.UiComponent;
import seedu.ibook.ui.table.ProductCard;

/**
 * The {@code ItemTable} that is containing {@code ItemCard}
 */
public class ItemTable extends UiComponent<VBox> {

    private static final String FXML = "table/item/ItemTable.fxml";

    private final ProductCard productCard;
    private final Product product;
    private final int productIndex;

    private final ObservableList<Item> filteredItem;

    private final Listener listener = new Listener();

    @FXML
    private VBox content;

    /**
     * Initializes a {@code ItemTable}.
     *
     * @param mainWindow The {@code MainWindow} that this component resides on.
     * @param product The {@code Product} associated with this table.
     * @param productIndex The index of the product.
     */
    public ItemTable(MainWindow mainWindow, ProductCard productCard, Product product, int productIndex) {
        super(FXML, mainWindow);
        this.productCard = productCard;
        this.product = product;
        this.productIndex = productIndex;
        this.filteredItem = product.getFilteredItems();
        filteredItem.addListener(listener);
        populateField();
    }

    /**
     * Removes listener from the table.
     */
    public void removeListener() {
        filteredItem.removeListener(listener);
    }

    private void populateField() {
        content.getChildren().clear();
        if (filteredItem.isEmpty()) {
            EmptyItemTableState emptyState = new EmptyItemTableState(productIndex, product, getMainWindow());
            content.getChildren().add(emptyState.getRoot());
        } else {
            for (int i = 0; i < filteredItem.size(); i++) {
                Item item = filteredItem.get(i);
                ItemCard itemCard = new ItemCard(productIndex, i + 1, product, item, getMainWindow());
                content.getChildren().add(itemCard.getRoot());
            }
        }
    }

    private class Listener implements ListChangeListener<Item> {
        @Override
        public void onChanged(Change<? extends Item> c) {
            populateField();
            productCard.populateField();
        }
    }

}
