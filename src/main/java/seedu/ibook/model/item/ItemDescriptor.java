package seedu.ibook.model.item;

import static seedu.ibook.commons.util.CollectionUtil.requireAllNonNull;

import seedu.ibook.model.product.Product;

public class ItemDescriptor {

    private final ExpiryDate expiryDate;
    private final Quantity quantity;

    /**
     * Constructs a new {@code ItemDescriptor} with default quantity.
     *
     * @param expiryDate The expiring date.
     */
    public ItemDescriptor(ExpiryDate expiryDate) {
        requireAllNonNull(expiryDate);
        this.expiryDate = expiryDate;
        this.quantity = new Quantity(1);
    }

    /**
     * Constructs a new {@code ItemDescriptor} with given quantity.
     *
     * @param expiryDate The expiring date.
     * @param quantity The quantity.
     */
    public ItemDescriptor(ExpiryDate expiryDate, Quantity quantity) {
        requireAllNonNull(expiryDate, quantity);
        assert quantity.getQuantity() <= Quantity.MAX_QUANTITY;

        this.expiryDate = expiryDate;
        this.quantity = quantity;
    }

    public ExpiryDate getExpiryDate() {
        return expiryDate;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public Item toItem(Product product) {
        return new Item(product, getExpiryDate(), getQuantity());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        builder.append("ExpiryDate: ")
                .append(getExpiryDate())
                .append("; Quantity: ")
                .append(getQuantity());

        return builder.toString();
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ItemDescriptor)) {
            return false;
        }

        // state check
        ItemDescriptor e = (ItemDescriptor) other;
        return quantity.equals(e.quantity)
                && expiryDate.equals(e.expiryDate);
    }

}
