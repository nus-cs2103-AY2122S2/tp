package seedu.ibook.model.item;

import static seedu.ibook.commons.util.AppUtil.checkArgument;

import java.util.Objects;

import seedu.ibook.commons.core.Distinguishable;
import seedu.ibook.model.product.Price;
import seedu.ibook.model.product.Product;

/**
 * Encapsulates Product information about {@code ExpiryDate} and {@code Quantity}.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Item extends ItemDescriptor implements Comparable<Item>, Distinguishable<Item> {

    private static final String ITEMS_MUST_BE_EQUAL_CONSTRAINT = "Items must be equal";

    private final Product product;

    /**
     * Every field must be present and not null.
     */
    public Item(Product product, ExpiryDate expiryDate) {
        super(expiryDate);
        this.product = product;
    }

    /**
     * Every field must be present and not null.
     */
    public Item(Product product, ExpiryDate expiryDate, Quantity quantity) {
        super(expiryDate, quantity);
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    /**
     * Checks if adding two items together satisfies the quantity constraint.
     */
    public boolean allowedToAdd(Item newItem) {
        return getQuantity().add(newItem.getQuantity()).inRange();
    }

    /**
     * Adds two items together.
     * @param newItem Item to add.
     */
    public Item add(Item newItem) {
        checkArgument(this.isSame(newItem), ITEMS_MUST_BE_EQUAL_CONSTRAINT);
        Quantity newQuantity = getQuantity().add(newItem.getQuantity());
        return new Item(getProduct(), getExpiryDate(), newQuantity);
    }

    /**
     * Set the quantity of the item.
     */
    public Item setQuantity(Quantity newQuantity) {
        return new Item(getProduct(), getExpiryDate(), newQuantity);
    }

    /**
     * Checks if the quantity of the item is zero.
     */
    public boolean isEmpty() {
        return getQuantity().isEmpty();
    }

    /**
     * Checks if the item has expired.
     * @return true if the item has expired
     */
    public boolean isExpired() {
        return getExpiryDate().isPast();
    }

    public Price getDiscountedPrice() {
        return product.getDiscountedPrice(getExpiryDate());
    }

    public boolean expiresBefore(ExpiryDate toCheck) {
        return getExpiryDate().within(toCheck);
    }

    /**
     * Returns true if both items have the same product and expiry date.
     * This defines a weaker notion of equality between two items.
     */
    public boolean isSame(Item otherItem) {
        if (otherItem == this) {
            return true;
        }

        return otherItem != null
            && otherItem.getProduct().equals(getProduct())
            && otherItem.getExpiryDate().equals(getExpiryDate());
    }

    /**
     * Returns true if both items have the same product, expiry date, and quantity.
     * This defines a stronger notion of equality between two items.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Item)) {
            return false;
        }

        Item otherItem = (Item) other;
        return otherItem.getProduct().equals(getProduct())
            && otherItem.getExpiryDate().equals(getExpiryDate())
            && otherItem.getQuantity().equals(getQuantity());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(getProduct(), getExpiryDate(), getQuantity());
    }

    @Override
    public int compareTo(Item o) {
        return getExpiryDate().compareTo(o.getExpiryDate());
    }

}
