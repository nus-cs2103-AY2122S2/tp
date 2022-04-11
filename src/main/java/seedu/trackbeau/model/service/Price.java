package seedu.trackbeau.model.service;
import static java.util.Objects.requireNonNull;
import static seedu.trackbeau.commons.util.AppUtil.checkArgument;

/**
 * Represents a Service's price in trackBeau.
 * Guarantees: immutable; is valid as declared in {@link #isValidPrice(String)}
 */
public class Price {


    public static final String MESSAGE_CONSTRAINTS =
            "Price should only contain numbers, at most 2 decimal places and have a value that is greater than 0.";
    public static final String VALIDATION_REGEX = "^\\s*(?=.*[1-9])\\d*(?:\\.\\d{1,2})?\\s*$";
    public final Double value;

    /**
     * Constructs a {@code Price}.
     *
     * @param price A valid price.
     */
    public Price(String price) {
        requireNonNull(price);
        checkArgument(isValidPrice(price), MESSAGE_CONSTRAINTS);
        value = Double.parseDouble(price);
    }

    /**
     * Constructs a {@code Price}.
     *
     * @param price A valid price.
     */
    public Price(Double price) {
        requireNonNull(price);
        value = price;
    }

    /**
     * Returns true if a given string is a valid price.
     */
    public static boolean isValidPrice(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Price // instanceof handles nulls
                && value.equals(((Price) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

