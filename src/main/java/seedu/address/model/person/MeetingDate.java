package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;

/**
 * Represents a Person's upcoming meeting date.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class MeetingDate {

    public static final String MESSAGE_CONSTRAINTS =
            "Meeting date should only contain numbers and hyphens, in the format of YYYY-MM-DD";
    public static final String VALIDATION_REGEX = "^([0-9]{4})(-)(0[1-9]|1[0-2])(-)(0[1-9]|1[0-9]|2[0-9]|3[0-1])$";
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
