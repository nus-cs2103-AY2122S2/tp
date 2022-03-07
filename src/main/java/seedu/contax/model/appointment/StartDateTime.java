package seedu.contax.model.appointment;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an {@link Appointment}'s starting DateTime in the schedule.
 * Guarantees: Immutable; is always a valid {@link LocalDateTime}; Seconds field is always zeroed.
 */
public class StartDateTime {
    public static final String DATETIME_FORMAT = "dd-MM-yyyy HH:mm";
    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern(DATETIME_FORMAT);

    public final LocalDateTime dateTime;

    /**
     * Constructs a {@code StartDateTime}
     *
     * @param dateTime Any non-null DateTime object.
     */
    public StartDateTime(LocalDateTime dateTime) {
        requireNonNull(dateTime);
        this.dateTime = dateTime.withSecond(0).withNano(0);
    }

    @Override
    public String toString() {
        return DATETIME_FORMATTER.format(dateTime);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StartDateTime // instanceof handles nulls
                && dateTime.equals(((StartDateTime) other).dateTime)); // state check
    }

    @Override
    public int hashCode() {
        return dateTime.hashCode();
    }
}
