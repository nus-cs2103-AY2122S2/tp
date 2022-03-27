package seedu.ibook.model;

import static java.util.Objects.requireNonNull;

import seedu.ibook.model.product.Product;

/**
 * A wrapper class for reversible version of {@code IBook#removeProduct(Product)} method.
 */
public class ReversibleRemoveProductAction extends ReversibleIBookAction {

    private final Product product;

    /**
     * Class constructor.
     * @param product the product to remove from the iBook.
     */
    public ReversibleRemoveProductAction(Product product) {
        requireNonNull(product);

        this.product = product;
    }

    @Override
    public void performForwardAction(IBook iBook) {
        requireNonNull(iBook);

        iBook.removeProduct(product);
    }

    @Override
    public void performBackwardAction(IBook iBook) {
        requireNonNull(iBook);

        iBook.addProduct(product);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ReversibleRemoveProductAction
                && product.equals(((ReversibleRemoveProductAction) other).product));
    }
}
