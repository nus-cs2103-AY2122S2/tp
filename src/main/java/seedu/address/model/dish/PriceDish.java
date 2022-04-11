package seedu.address.model.dish;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Dish's price in FoodOnWheels.
 * Guarantees: immutable; is valid as declared in {@link #isValidPrice(String)}
 */
public class PriceDish {


    public static final String MESSAGE_CONSTRAINTS =
            "Prices should only be in 2 decimal places, i.e. 123.90, and within the limits 0.00 to 99999.99 inclusive";
    public static final String VALIDATION_REGEX = "\\d{1,5}\\.[0-9]{2}";
    public final double value;

    /**
     * Constructs a {@code PriceDish}.
     *
     * @param price A valid price.
     */
    public PriceDish(String price) {
        requireNonNull(price);
        checkArgument(isValidPrice(price), MESSAGE_CONSTRAINTS);
        value = Double.parseDouble(price);
    }

    /**
     * Returns true if a given string is a valid price.
     */
    public static boolean isValidPrice(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return String.format("%.2f", value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PriceDish // instanceof handles nulls
                && value == ((PriceDish) other).value); // state check
    }

    @Override
    public int hashCode() {
        return ((Double) value).hashCode();
    }

}
