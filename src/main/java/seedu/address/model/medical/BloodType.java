package seedu.address.model.medical;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's blood type in MedBook.
 * Guarantees: immutable; is valid as declared in {@link #isValidBloodType(String)}
 */
public class BloodType {

    public static final String MESSAGE_CONSTRAINTS = "Blood type must be either A, B, AB or O.";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[A|B|AB|O]";

    public final String value;

    /**
     * Constructs an {@code BloodType}.
     *
     * @param address A valid address.
     */
    public BloodType(String address) {
        requireNonNull(address);
        checkArgument(isValidBloodType(address), MESSAGE_CONSTRAINTS);
        value = address;
    }

    /**
     * Returns true if given string is valid.
     */
    public static boolean isValidBloodType(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BloodType // instanceof handles nulls
                && value.equals(((BloodType) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
