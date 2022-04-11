package seedu.address.model.tutorial;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the day of week which a Tutorial is on in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDay(String)}
 */
public class Day {
    public static final String MESSAGE_CONSTRAINTS = "Days should be spelt in full "
            + "or with 3-letter abbreviation";
    public static final String VALIDATION_REGEX = "(?i)(Monday|Tuesday|Wednesday|Thursday|"
            + "Friday|Saturday|Sunday|Mon|Tue|Wed|Thu|Fri|Sat|Sun)";
    public final String day;

    /**
     * Constructs a {@code Day}.
     *
     * @param value represents the position of the day in the week.
     */
    public Day(String value) {
        requireNonNull(value);
        checkArgument(isValidDay(value), MESSAGE_CONSTRAINTS);
        day = setProperDay(value);
    }

    /**
     * Returns true if given String represents any day of the week spelt in full or 3-letter abbreviation.
     */
    public static boolean isValidDay(String value) {
        return value.matches(VALIDATION_REGEX);
    }

    private String setProperDay(String day) {
        String fullDay;
        switch (day.toUpperCase()) {

        case ("MON"):
        case ("MONDAY"):
            fullDay = "Monday";
            break;
        case ("TUE"):
        case ("TUESDAY"):
            fullDay = "Tuesday";
            break;
        case ("WED"):
        case ("WEDNESDAY"):
            fullDay = "Wednesday";
            break;
        case ("THU"):
        case ("THURSDAY"):
            fullDay = "Thursday";
            break;
        case ("FRI"):
        case ("FRIDAY"):
            fullDay = "Friday";
            break;
        case ("SAT"):
        case ("SATURDAY"):
            fullDay = "Saturday";
            break;
        case ("SUN"):
        case ("SUNDAY"):
            fullDay = "Sunday";
            break;
        default:
            fullDay = "Monday";
        }
        return fullDay;
    }

    @Override
    public String toString() {
        return day;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Day // instanceof handles nulls
                && day.equals(((Day) other).day)); // state check
    }

    @Override
    public int hashCode() {
        return day.hashCode();
    }
}
