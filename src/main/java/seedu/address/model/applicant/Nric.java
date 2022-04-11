package seedu.address.model.applicant;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's Nric in the address book.
 */
public class Nric {

    public static final String MESSAGE_CONSTRAINTS = "Nric must be a string with the format "
            + "[Char][7 numeric digits][Char]";

    /*
     * The Nric must be in the format [Char][7 numeric digits][Char].
     */
    public static final String VALIDATION_REGEX = "^[a-zA-z]\\d{7}[a-zA-Z]$";

    public final String value;

    /**
     * Constructs a Nric object.
     *
     * @param nric A valid Nric string
     */
    public Nric(String nric) {
        requireNonNull(nric);
        checkArgument(isValidNric(nric), MESSAGE_CONSTRAINTS);
        value = nric.toUpperCase();
    }

    /**
     * Returns true if the input string is a valid Nric.
     */
    public static boolean isValidNric(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.applicant.Nric
                && value.toLowerCase().equals(((seedu.address.model.applicant.Nric) other).value.toLowerCase()));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

