package seedu.ibook.ui.table;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import seedu.ibook.model.product.Product;
import seedu.ibook.ui.UiPart;
import seedu.ibook.ui.popup.PopupDelete;
import seedu.ibook.ui.popup.PopupUpdate;

/**
 * A card showing the details of a {@code Product}.
 */
public class ProductCard extends UiPart<HBox> {

    private static final String FXML = "Table/ProductCard.fxml";

    private final int index;
    private final Product product;

    private final PopupUpdate popupUpdate;
    private final PopupDelete popupDelete;

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
     * @param popupUpdate The popup responsible for update.
     * @param popupDelete The popup responsible for delete.
     */
    ProductCard(int index, Product product,
                PopupUpdate popupUpdate,
                PopupDelete popupDelete) {
        super(FXML);
        this.index = index;
        this.product = product;
        this.popupUpdate = popupUpdate;
        this.popupDelete = popupDelete;
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
        if (popupUpdate.isShowing()) {
            popupUpdate.hide();
        }
        popupUpdate.show(index, product);
    }

    @FXML
    private void handlePopupDelete() {
        if (popupDelete.isShowing()) {
            popupDelete.hide();
        }
        popupDelete.show(index, product);
    }

}
