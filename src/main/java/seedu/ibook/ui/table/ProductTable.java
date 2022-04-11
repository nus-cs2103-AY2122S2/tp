package seedu.ibook.ui.table;

import java.util.ArrayList;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import seedu.ibook.model.product.Product;
import seedu.ibook.ui.MainWindow;
import seedu.ibook.ui.UiComponent;


/**
 * The {@code ProductTable} that is containing {@code ProductCard}.
 */
public class ProductTable extends UiComponent<VBox> {

    private static final String FXML = "table/ProductTable.fxml";

    private final ObservableList<Product> filteredIBook;

    private final ArrayList<ProductCard> productCards = new ArrayList<>();

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
        productCards.clear();

        if (filteredIBook.isEmpty()) {
            getTableState();
        } else {
            for (int i = 0; i < filteredIBook.size(); i++) {
                Product product = filteredIBook.get(i);
                ProductCard productCard = new ProductCard(i + 1, product, getMainWindow());
                content.getChildren().add(productCard.getRoot());
                productCards.add(productCard);
            }
        }
    }

    private void getTableState() {
        if (getMainWindow().hasActiveFilter()) {
            NotFoundProductTableState notFoundState = new NotFoundProductTableState(getMainWindow());
            content.getChildren().add(notFoundState.getRoot());
        } else {
            EmptyProductTableState emptyState = new EmptyProductTableState(getMainWindow());
            content.getChildren().add(emptyState.getRoot());
        }
    }

    private class Listener implements ListChangeListener<Product> {
        @Override
        public void onChanged(Change<? extends Product> c) {
            populateField();
        }
    }
}
