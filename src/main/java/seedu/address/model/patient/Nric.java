package seedu.address.model.patient;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's NRIC in Medbook.
 * Guarantees: immutable; is valid as declared in {@link #isValidNric(String)}
 */
public class Nric {
    public static final String MESSAGE_CONSTRAINTS =
            "NRIC should be alphanumeric, starting with either S, T, F, G, M, followed by 7 digits "
                    + "and ending with any character.";
    public static final String VALIDATION_REGEX = "^[STFGM]\\d{7}[A-Z]$";
    public final String value;

    /**
     * Constructs a {@code Nric}.
     *
     * @param nric A valid National Registration Identity Card number.
     */
    public Nric(String nric) {
        requireNonNull(nric);
        checkArgument(isValidNric(nric), MESSAGE_CONSTRAINTS);
        value = nric;
    }

    /**
     * Returns true if a given string is a valid phone number.
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
                || (other instanceof Nric // instanceof handles nulls
                && value.equals(((Nric) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
