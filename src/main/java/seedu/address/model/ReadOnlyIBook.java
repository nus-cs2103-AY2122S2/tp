package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.product.Product;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyIBook {

    /**
     * Returns an unmodifiable view of the products list.
     * This list will not contain any duplicate products.
     */
    ObservableList<Product> getProductList();

}
