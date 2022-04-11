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
    public static final String VALIDATION_REGEX = "^([0-9]{4})(-)([0-9]{2})(-)([0-9]{2})$";
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
     * Returns true if a given string is a date that exists.
     */
    public static boolean isDatePossible(String test) {
        LocalDate.parse(test);
        return true;
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

    /**
     * Method to compare PrevDateMet with other PrevDateMet.
     * Returns 0 if date is equal, -1 if this PrevDateMet is before and 1 if it is after.
     *
     * @param otherDate Another PrevDateMet to compare to.
     * @return Integer indicating if PrevDateMet is equal, before or after otherDate
     */
    public int compare(PrevDateMet otherDate) {
        if (this.equals(otherDate)) {
            return 0;
        } else if (this.value.isBefore(otherDate.value)) {
            return -1;
        } else {
            return 1;
        }

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
