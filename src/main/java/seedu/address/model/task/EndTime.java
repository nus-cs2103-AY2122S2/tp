package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Represents a Task's end time in the schedule book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEndTime(String)}
 */
public class EndTime {
    public static final String MESSAGE_CONSTRAINTS = "The end time should be in the following format HH:MM "
            + "and adhere to the following constraints: \n"
            + "1. HH should be a 2-digit numeric in the range of 00 to 23 \n"
            + "2. MM should be a 2-digit numeric in the range of 00 to 59";

    public final String value;

    /**
     * Constructs a {@code EndTime}.
     *
     * @param time A valid EndTime.
     */
    public EndTime(String time) {
        requireNonNull(time);
        checkArgument(isValidEndTime(time), MESSAGE_CONSTRAINTS);
        value = time;
    }

    /**
     * Returns true if the given time is valid.
     */
    public static boolean isValidEndTime(String time) {
        try {
            DateTimeFormatter customFormat = DateTimeFormatter
                    .ofPattern("HH:mm").withResolverStyle(ResolverStyle.STRICT);
            LocalTime.parse(time, customFormat);
        } catch (DateTimeParseException ex) {
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EndTime // instanceof handles nulls
                && value.equals(((EndTime) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value;
    }
}

