package seedu.ibook.model.item;

import static java.util.Objects.requireNonNull;
import static seedu.ibook.commons.util.AppUtil.checkArgument;

public class Quantity {

    public static final Integer MAX_QUANTITY = 999999;
    public static final String MESSAGE_CONSTRAINTS =
        "Quantities should only be of non-negative integers and at most " + MAX_QUANTITY;
    public static final String SMALLER_THAN_CONSTRAINT =
        "Subtracted quantity must be smaller than or equal to the current quantity";

    /*
     * Must be a non-negative integer at most Quantity.MAX_QUANTITY.
     */
    public static final String VALIDATION_REGEX = "0*(?:0|[1-9]\\d{0,5})";

    public final Integer quantity;

    /**
     * Constructs a {@code Quantity}.
     *
     * @param quantity A valid string quantity.
     */
    public Quantity(String quantity) {
        requireNonNull(quantity);
        checkArgument(isValidQuantity(quantity), MESSAGE_CONSTRAINTS);
        this.quantity = Integer.parseInt(quantity);
    }

    /**
     * Constructs a {@code Quantity}.
     *
     * @param quantity A valid integer quantity.
     */
    public Quantity(Integer quantity) {
        requireNonNull(quantity);
        this.quantity = quantity;
    }

    /**
     * Returns true if a given string is a valid quantity.
     */
    public static boolean isValidQuantity(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public boolean isEmpty() {
        return this.quantity == 0;
    }

    /**
     * Adds both quantities.
     *
     * @param other Quantity to add.
     */
    public Quantity add(Quantity other) {
        return new Quantity(quantity + other.getQuantity());
    }

    /**
     * Subtracts from the current quantity.
     * @param other Quantity to subtract.
     */
    public Quantity subtract(Quantity other) {
        checkArgument(this.quantity >= other.quantity, SMALLER_THAN_CONSTRAINT);
        return new Quantity(quantity - other.getQuantity());
    }

    /**
     * Checks if the quantity is in acceptable range.
     * @return true if the quantity is in acceptable range.
     */
    public boolean inRange() {
        return quantity >= 0 && quantity < Quantity.MAX_QUANTITY;
    }

    @Override
    public String toString() {
        return String.format("%d", quantity);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Quantity // instanceof handles nulls
            && quantity.equals(((Quantity) other).quantity)); // state check
    }

    @Override
    public int hashCode() {
        return quantity.hashCode();
    }

}
