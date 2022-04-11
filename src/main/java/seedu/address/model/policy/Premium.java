package seedu.address.model.policy;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Client's premium amount in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPremium(String)}
 */
public class Premium {

    public static final String MESSAGE_CONSTRAINTS =
            "Premium amount should only contain numbers";
    public static final String VALIDATION_REGEX = "\\d+";
    public final String value;

    /**
     * Constructs a {@code Premium}.
     *
     * @param premium A valid premium amount.
     */
    public Premium(String premium) {
        requireNonNull(premium);
        checkArgument(isValidPremium(premium), MESSAGE_CONSTRAINTS);
        value = premium;
    }

    /**
     * Returns true if a given string is a valid Premium amount.
     */
    public static boolean isValidPremium(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /** Returns the premium amount */
    public int getValue() {
        return Integer.parseInt(value);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Premium // instanceof handles nulls
                && value.equals(((Premium) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
