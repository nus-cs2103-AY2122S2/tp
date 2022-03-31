package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;

import seedu.address.model.person.exceptions.ComparatorException;
/**
 * Represents a Person's upcoming meeting date.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class MeetingDate {

    public static final String MESSAGE_CONSTRAINTS =
            "Meeting date should only contain numbers and hyphens, in the format of YYYY-MM-DD";
    public static final String VALIDATION_REGEX = "^([0-9]{4})(-)([0-9]{2})(-)([0-9]{2})$";
    public final LocalDate value;

    /**
     * Constructs a {@code MeetingDate}.
     *
     * @param date A valid meeting date.
     */
    public MeetingDate(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        value = LocalDate.parse(date);
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
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
     * Method to compare MeetingDate with other MeetingDate.
     * Returns 0 if date is equal, -1 if this MeetingDate is before and 1 if it is after.
     *
     * @param otherDate Another MeetingDate to compare to.
     * @return Integer indicating if MeetingDate is equal, before or after otherDate.
     * @throws ComparatorException if hits else statement which it should not reach.
     */
    public int compare(MeetingDate otherDate) throws ComparatorException {
        if (this.equals(otherDate)) {
            return 0;
        } else if (this.value.isBefore(otherDate.value)) {
            return -1;
        } else if (this.value.isAfter(otherDate.value)) {
            return 1;
        } else {
            throw new ComparatorException();
        }
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MeetingDate // instanceof handles nulls
                && value.equals(((MeetingDate) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
