package seedu.address.model.medical;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;


/**
 * Represents a patient's weight in MedBook.
 * Guarantees: immutable; is valid as declared in {@link #isValidWeight(String)}
 */
public class Weight {

    public static final String MESSAGE_CONSTRAINTS = "Invalid height input. "
            + "We recommend inputting height using kilograms (eg. 70 kg), "
            + "but we allow any other unit or special weight values (eg. using other units). "
            + "Weight parameter can take any values, and it should not be blank.";

    /*
     * The first character of the Weight must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs a {@code Weight}.
     *
     * @param weight A valid Weight.
     */
    public Weight(String weight) {
        requireNonNull(weight);
        checkArgument(isValidWeight(weight), MESSAGE_CONSTRAINTS);
        value = weight;
    }

    /**
     * Returns true if given string is valid.
     */
    public static boolean isValidWeight(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Weight // instanceof handles nulls
                && value.equals(((Weight) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
