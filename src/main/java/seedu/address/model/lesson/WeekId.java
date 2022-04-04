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

    public final String value;

    /**
     * Constructs a {@code WeekId}.
     *
     * @param weekId A valid weekId.
     */
    public WeekId(String weekId) {
        requireNonNull(weekId);
        checkArgument(isValidWeekId(weekId), MESSAGE_CONSTRAINTS);
        this.value = weekId;
    }

    /**
     * Returns true if a given string is a valid week ID.
     */
    public static boolean isValidWeekId(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof WeekId // instanceof handles nulls
                && value.equals(((WeekId) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
