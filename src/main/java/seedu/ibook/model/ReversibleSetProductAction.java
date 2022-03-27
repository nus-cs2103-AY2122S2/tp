package seedu.ibook.model;

import seedu.ibook.model.product.Product;

/**
 * A wrapper class for reversible version of {@code IBook#setProduct(Product, Product)} method.
 */
public class ReversibleSetProductAction extends ReversibleIBookAction {

    private final Product productToUpdate;
    private final Product updatedProduct;

    /**
     * Class constructor.
     * @param productToUpdate the product to update in the iBook,
     * @param updatedProduct the product {@code productToUpdate} needs to update to.
     */
    public ReversibleSetProductAction(Product productToUpdate, Product updatedProduct) {
        this.productToUpdate = productToUpdate;
        this.updatedProduct = updatedProduct;
    }

    @Override
    public void performForwardAction(IBook iBook) {
        iBook.setProduct(productToUpdate, updatedProduct);
    }

    @Override
    public void performBackwardAction(IBook iBook) {
        iBook.setProduct(updatedProduct, productToUpdate);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ReversibleSetProductAction
                    && productToUpdate.equals(((ReversibleSetProductAction) other).productToUpdate)
                    && updatedProduct.equals(((ReversibleSetProductAction) other).updatedProduct));
    }
}
