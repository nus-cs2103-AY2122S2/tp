package seedu.ibook.model.actions;

import static java.util.Objects.requireNonNull;
import static seedu.ibook.commons.util.CollectionUtil.requireAllNonNull;

import seedu.ibook.model.IBook;
import seedu.ibook.model.item.Item;
import seedu.ibook.model.product.Product;

/**
 * A wrapper class for reversible version of {@code IBook#removeItem(Product, Item)} method.
 */
public class ReversibleRemoveItemAction extends ReversibleIBookAction {

    private final Product product;
    private final Item item;

    /**
     * Class constructor.
     * @param product the product of which an item is to be deleted.
     * @param item the item to be deleted.
     */
    public ReversibleRemoveItemAction(Product product, Item item) {
        requireAllNonNull(product, item);

        this.product = product;
        this.item = item;
    }

    @Override
    public void performForwardAction(IBook iBook) {
        requireNonNull(iBook);

        iBook.removeItem(product, item);
    }

    @Override
    public void performBackwardAction(IBook iBook) {
        requireNonNull(iBook);

        iBook.addItem(product, item);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ReversibleRemoveItemAction
                && product.equals(((ReversibleRemoveItemAction) other).product)
                && item.equals(((ReversibleRemoveItemAction) other).item));
    }
}
