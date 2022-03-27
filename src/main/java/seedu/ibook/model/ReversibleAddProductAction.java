package seedu.ibook.model;

import static java.util.Objects.requireNonNull;

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
        requireNonNull(product);

        this.product = product;
    }

    @Override
    public void performForwardAction(IBook iBook) {
        requireNonNull(iBook);

        iBook.addProduct(product);
    }

    @Override
    public void performBackwardAction(IBook iBook) {
        requireNonNull(iBook);

        iBook.removeProduct(product);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ReversibleAddProductAction
                    && product.equals(((ReversibleAddProductAction) other).product));
    }
}
