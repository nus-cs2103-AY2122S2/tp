package seedu.ibook.model;

import javafx.collections.ObservableList;
import seedu.ibook.model.product.Product;

/**
 * Unmodifiable view of an ibook
 */
public interface ReadOnlyIBook {

    /**
     * Returns an unmodifiable view of the products list.
     * This list will not contain any duplicate products.
     */
    ObservableList<Product> getProductList();

}
