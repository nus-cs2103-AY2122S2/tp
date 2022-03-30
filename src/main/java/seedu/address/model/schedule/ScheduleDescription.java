package seedu.address.model.schedule;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the description of a schedule
 */
public class ScheduleDescription {

    public static final String MESSAGE_CONSTRAINTS =
            "Schedule description should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^(?!\\s*$).+";

    public final String description;

    /**
     * Constructs a {@code ScheduleDescription}.
     * @param description A valid description.
     */
    public ScheduleDescription(String description) {
        requireNonNull(description);
        checkArgument(isValidScheduleDescription(description), MESSAGE_CONSTRAINTS);
        this.description = description;
    }

    /**
     * Returns true if a given string is a valid description.
     */
    public static boolean isValidScheduleDescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return description;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ScheduleDescription // instanceof handles nulls
                && description.equals(((ScheduleDescription) other).description)); // state check
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }

}
