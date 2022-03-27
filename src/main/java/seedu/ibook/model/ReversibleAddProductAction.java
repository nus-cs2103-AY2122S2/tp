package seedu.ibook.model;

import seedu.ibook.model.product.Product;

/**
 * A wrapper class for reversible version of {@code IBook#addProduct(Product)} method.
 */
public class ReversibleAddProductAction extends ReversibleIBookAction {

    private final Product product;

    /**
     * Class constructor.
     * @param product the product to add into the iBook.
     */
    public ReversibleAddProductAction(Product product) {
        this.product = product;
    }

    @Override
    public void performForwardAction(IBook iBook) {
        iBook.addProduct(product);
    }

    @Override
    public void performBackwardAction(IBook iBook) {
        iBook.removeProduct(product);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ReversibleAddProductAction
                    && product.equals(((ReversibleAddProductAction) other).product));
    }
}
