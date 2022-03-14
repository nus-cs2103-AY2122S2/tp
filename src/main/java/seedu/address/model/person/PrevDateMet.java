package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;

/**
 * Represents a Person's previous met up date.
 * Guarantees: immutable; is valid as declared in {@link #isValidPrevDateMet(String)}
 */
public class PrevDateMet {

    public static final String MESSAGE_CONSTRAINTS =
            "Previous Date Met should only contain numbers and hyphens, in the format of YYYY-MM-DD";
    public static final String VALIDATION_REGEX = "^([0-9]{4})(-)(0[1-9]|1[0-2])(-)(0[1-9]|1[1-9]|2[1-9]|3[0-1])$";
    public final LocalDate value;

    /**
     * Constructs a {@code PrevDateMet}.
     *
     * @param prevDateMet A valid previous date met.
     */
    public PrevDateMet(String prevDateMet) {
        requireNonNull(prevDateMet);
        checkArgument(isValidPrevDateMet(prevDateMet), MESSAGE_CONSTRAINTS);
        value = LocalDate.parse(prevDateMet);
    }

    /**
     * Returns true if a given string is a valid previous date met.
     */
    public static boolean isValidPrevDateMet(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Method to get today's date in the event where user does not
     * specify the date last met when adding a client.
     *
     * @return String representation of today's date in YYYY-MM-DD format.
     */
    public static String getTodaysDate() {
        return LocalDate.now().toString();
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PrevDateMet // instanceof handles nulls
                && value.equals(((PrevDateMet) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
