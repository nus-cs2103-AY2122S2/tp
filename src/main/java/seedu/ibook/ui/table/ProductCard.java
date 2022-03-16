package seedu.ibook.ui.table;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import seedu.ibook.model.product.Product;
import seedu.ibook.ui.MainWindow;
import seedu.ibook.ui.UiComponent;

/**
 * A card showing the details of a {@code Product}.
 */
public class ProductCard extends UiComponent<HBox> {

    private static final String FXML = "Table/ProductCard.fxml";

    private final int index;
    private final Product product;

    @FXML
    private Label indexLabel;
    @FXML
    private Label name;
    @FXML
    private Label category;
    @FXML
    private Label expiryDate;
    @FXML
    private Label price;
    @FXML
    private Label description;

    /**
     * Initializes a {@code ProductCard}.
     *
     * @param index Index of the {@code Product} in the filtered list.
     * @param product The {@code Product}.
     * @param mainWindow The Main Window that this component resides on.
     */
    ProductCard(int index, Product product, MainWindow mainWindow) {
        super(FXML, mainWindow);
        this.index = index;
        this.product = product;
        populateField();
    }

    private void populateField() {
        indexLabel.setText(String.valueOf(index));
        name.setText(product.getName().toString());
        category.setText(product.getCategory().toString());
        expiryDate.setText(product.getExpiryDate().toString());
        price.setText(product.getPrice().toString());
        description.setText(product.getDescription().toString());
    }

    @FXML
    private void handlePopupUpdate() {
        getMainWindow().showPopupUpdate(index, product);
    }

    @FXML
    private void handlePopupDelete() {
        getMainWindow().showPopupDelete(index, product);
    }

}
