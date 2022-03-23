package seedu.address.model.schedule;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the name of a schedule
 */
public class ScheduleName {
    public static final String MESSAGE_CONSTRAINTS =
            "Schedule Descriptions should only contain alphanumeric characters and spaces, and it should not be blank";

    /**
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String scheduleName;

    /**
     * Constructs a schedule name.
     */
    public ScheduleName(String scheduleName) {
        requireNonNull(scheduleName);
        checkArgument(isValidScheduleName(scheduleName), MESSAGE_CONSTRAINTS);
        this.scheduleName = scheduleName;
    }

    public String getScheduleName() {
        return scheduleName;
    }

    public static boolean isValidScheduleName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return scheduleName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ScheduleName // instanceof handles nulls
                && scheduleName.equals(((ScheduleName) other).scheduleName)); // state check
    }

    @Override
    public int hashCode() {
        return scheduleName.hashCode();
    }
}
