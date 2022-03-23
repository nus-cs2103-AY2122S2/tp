package seedu.contax.model.appointment;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.contax.model.chrono.TemporalComparable;

/**
 * Represents an {@link Appointment}'s starting DateTime in the schedule.
 * Guarantees: Immutable; is always a valid {@link LocalDateTime}; Seconds field is always zeroed.
 */
public class StartDateTime implements TemporalComparable {
    public static final String DATETIME_FORMAT = "dd-MM-yyyy HH:mm";
    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern(DATETIME_FORMAT);
    public static final String MESSAGE_CONSTRAINTS = "DateTime has to be valid";

    public final LocalDateTime value;

    /**
     * Constructs a {@code StartDateTime}.
     *
     * @param dateTime Any non-null DateTime object.
     */
    public StartDateTime(LocalDateTime dateTime) {
        requireNonNull(dateTime);
        this.value = dateTime.withSecond(0).withNano(0);
    }

    @Override
    public String toString() {
        return DATETIME_FORMATTER.format(value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StartDateTime // instanceof handles nulls
                && value.equals(((StartDateTime) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public LocalDateTime getComparableDateTime() {
        return this.value;
    }

    @Override
    public int compareTo(TemporalComparable o) {
        return this.value.compareTo(o.getComparableDateTime());
    }
}
