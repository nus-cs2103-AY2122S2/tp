package seedu.ibook.model.item;

import static seedu.ibook.commons.util.AppUtil.checkArgument;
import static seedu.ibook.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.ibook.commons.core.Distinguishable;

/**
 * Encapsulates Product information about {@code ExpiryDate} and {@code Quantity}.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Item implements Comparable<Item>, Distinguishable<Item> {

    private static final String ITEMS_MUST_BE_EQUAL_CONSTRAINT = "Items must be equal";

    private final ExpiryDate expiryDate;
    private final Quantity quantity;

    /**
     * Every field must be present and not null.
     */
    public Item(ExpiryDate expiryDate) {
        requireAllNonNull(expiryDate);
        this.expiryDate = expiryDate;
        this.quantity = new Quantity(1);
    }

    /**
     * Every field must be present and not null.
     */
    public Item(ExpiryDate expiryDate, Quantity quantity) {
        requireAllNonNull(expiryDate, quantity);
        this.expiryDate = expiryDate;
        this.quantity = quantity;
    }

    public ExpiryDate getExpiryDate() {
        return expiryDate;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    /**
     * Adds two items together.
     * @param newItem Item to add.
     */
    public Item add(Item newItem) {
        checkArgument(this.isSame(newItem), ITEMS_MUST_BE_EQUAL_CONSTRAINT);
        Quantity newQuantity = quantity.add(newItem.getQuantity());
        return new Item(expiryDate, newQuantity);
    }

    /**
     * Set the quantity of the item.
     */
    public Item setQuantity(Quantity newQuantity) {
        return new Item(expiryDate, newQuantity);
    }

    /**
     * Increase the quantity of the item.
     */
    public Item increment(Quantity quantity) {
        Quantity newQuantity = this.quantity.add(quantity);
        return setQuantity(newQuantity);
    }

    /**
     * Decrease the quantity of the item.
     */
    public Item decrement(Quantity quantity) {
        Quantity newQuantity = this.quantity.subtract(quantity);
        return setQuantity(newQuantity);
    }

    /**
     * Checks if the quantity of the item is zero.
     */
    public boolean isEmpty() {
        return quantity.isEmpty();
    }

    public boolean isExpired() {
        return expiryDate.isPast();
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
        return otherItem.getExpiryDate().equals(getExpiryDate())
            && otherItem.getQuantity().equals(getQuantity());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(expiryDate, quantity);
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
    public int compareTo(Item o) {
        return expiryDate.compareTo(o.getExpiryDate());
    }
}
