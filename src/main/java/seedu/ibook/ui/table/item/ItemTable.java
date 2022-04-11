package seedu.ibook.ui.table.item;

import java.util.ArrayList;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import seedu.ibook.model.item.FilteredItemList;
import seedu.ibook.model.item.Item;
import seedu.ibook.model.product.Product;
import seedu.ibook.ui.MainWindow;
import seedu.ibook.ui.UiComponent;

/**
 * The {@code ItemTable} that is containing {@code ItemCard}
 */
public class ItemTable extends UiComponent<VBox> {

    private static final String FXML = "table/item/ItemTable.fxml";

    private final Product product;
    private final int productIndex;

    private final FilteredItemList filteredItem;
    private final ArrayList<ItemCard> itemCards = new ArrayList<>();

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
    public ItemTable(MainWindow mainWindow, Product product, int productIndex) {
        super(FXML, mainWindow);
        this.product = product;
        this.productIndex = productIndex;
        this.filteredItem = product.getFilteredItems();
        filteredItem.addListener(listener);
        populateField();
    }

    private void populateField() {
        content.getChildren().clear();
        itemCards.clear();
        if (filteredItem.isEmpty()) {
            EmptyItemTableState emptyState = new EmptyItemTableState(productIndex, product, getMainWindow());
            content.getChildren().add(emptyState.getRoot());
        } else {
            for (int i = 0; i < filteredItem.size(); i++) {
                Item item = filteredItem.get(i);
                ItemCard itemCard = new ItemCard(productIndex, i + 1, product, item, getMainWindow());
                content.getChildren().add(itemCard.getRoot());
                itemCards.add(itemCard);
            }
        }
    }

    private class Listener implements ListChangeListener<Item> {
        @Override
        public void onChanged(Change<? extends Item> c) {
            populateField();
        }
    }

}
