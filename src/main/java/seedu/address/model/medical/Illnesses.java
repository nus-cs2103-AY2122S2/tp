package seedu.address.model.medical;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's illnesses in the MedBook.
 * Guarantees: immutable; is valid as declared in {@link #isValidIllnesses(String)}
 */
public class Illnesses {

    public static final String MESSAGE_CONSTRAINTS = "Illnesses can take any values, and it should not be blank";

    /*
     * The first character of the illness must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code Illnesses}.
     *
     * @param illness Information about the patient's illnesses.
     */
    public Illnesses(String illness) {
        requireNonNull(illness);
        checkArgument(isValidIllnesses(illness), MESSAGE_CONSTRAINTS);
        value = illness;
    }

    /**
     * Returns true if given string is valid.
     */
    public static boolean isValidIllnesses(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Illnesses // instanceof handles nulls
                && value.equals(((Illnesses) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
