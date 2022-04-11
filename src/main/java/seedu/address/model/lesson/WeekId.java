package seedu.address.model.lesson;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

//@@author jxt00
/**
 * Represents a Lesson's week ID in the TAssist.
 * Guarantees: immutable; is valid as declared in {@link #isValidWeekId(String)}
 */
public class WeekId {
    public static final String MESSAGE_CONSTRAINTS =
            "Week IDs should only contain numbers between 1 and 13 inclusive, and it should not be blank";
    public static final String VALIDATION_REGEX = "^(0?[1-9]|1[0123])$";
    public final Integer value;

    /**
     * Constructs a {@code WeekId}.
     *
     * @param weekId A valid weekId.
     */
    public WeekId(String weekId) {
        requireNonNull(weekId);
        checkArgument(isValidWeekId(weekId), MESSAGE_CONSTRAINTS);
        this.value = Integer.valueOf(weekId);
    }

    /**
     * Returns true if a given string is a valid week ID.
     */
    public static boolean isValidWeekId(String test) {
        if (test.matches(VALIDATION_REGEX)) {
            try {
                Integer.valueOf(test);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof WeekId
                && value.equals(((WeekId) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
