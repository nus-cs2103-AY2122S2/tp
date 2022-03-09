package seedu.trackbeau.model.customer;

import static java.util.Objects.requireNonNull;
import static seedu.trackbeau.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's hair type in the trackbeau book.
 * Guarantees: immutable; is valid as declared in {@link #isValidHairType(String)}
 */
public class HairType {

    public static final String MESSAGE_CONSTRAINTS = "HairTypes can be any values and it should not be blank";

    /*
     * The first character of the trackbeau must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code hairType}.
     *
     * @param hairType A valid hair type.
     */
    public HairType(String hairType) {
        requireNonNull(hairType);
        checkArgument(isValidHairType(hairType), MESSAGE_CONSTRAINTS);
        value = hairType;
    }

    /**
     * Returns true if a given string is a valid hair type.
     */
    public static boolean isValidHairType(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HairType // instanceof handles nulls
                && value.equals(((HairType) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
