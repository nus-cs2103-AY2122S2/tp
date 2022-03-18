package seedu.ibook.ui.table;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import seedu.ibook.model.product.Product;
import seedu.ibook.ui.MainWindow;
import seedu.ibook.ui.UiComponent;

/**
 * The main content of the table.
 */
public class TableContent extends UiComponent<ScrollPane> {

    private static final String FXML = "Table/TableContent.fxml";

    @FXML
    private VBox content;

    /**
     * Initializes a {@code TableContent}.
     *
     * @param mainWindow The {@code MainWindow} that this component resides on.
     */
    TableContent(MainWindow mainWindow) {
        super(FXML, mainWindow);
        populateField();
    }

    private void populateField() {
        ObservableList<Product> filteredIBook = getMainWindow().getFilteredIBook();
        filteredIBook.addListener(new Listener());
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
