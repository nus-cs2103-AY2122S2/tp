package seedu.ibook.ui.table;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import seedu.ibook.model.product.Product;
import seedu.ibook.ui.MainWindow;
import seedu.ibook.ui.UiComponent;

/**
 * The {@code Product} table that is containing {@code ProductCard}.
 */
public class ProductTable extends UiComponent<VBox> {

    private static final String FXML = "Table/ProductTable.fxml";

    private final ObservableList<Product> filteredIBook;

    @FXML
    private VBox content;

    /**
     * Initializes a {@code ProductTable}.
     *
     * @param mainWindow The {@code MainWindow} that this component resides on.
     */
    public ProductTable(MainWindow mainWindow) {
        super(FXML, mainWindow);

        filteredIBook = getMainWindow().getFilteredIBook();
        filteredIBook.addListener(new Listener());

        populateField();
    }

    private void populateField() {
        getMainWindow().populateFilters();
        content.getChildren().clear();
        for (int i = 0; i < filteredIBook.size(); i++) {
            Product product = filteredIBook.get(i);
            ProductCard productCard = new ProductCard(i + 1, product, getMainWindow());
            content.getChildren().add(productCard.getRoot());
        }
    }

    private class Listener implements ListChangeListener<Product> {
        @Override
        public void onChanged(Change<? extends Product> c) {
            populateField();
        }
    }

}
