package seedu.contax.model.chrono;

import static java.util.Objects.requireNonNull;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents an anchored period of time, from a start date-time to an end date-time inclusive.
 * This is in contrast to {@link java.time.Period}, which is not anchored to a start time.
 * {@code TimeRange} objects are immutable.
 */
public class TimeRange implements TemporalComparable {

    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Constructs a {@code TimeRange} object.
     *
     * @param start The start date-time of the range.
     * @param end The end date-time of the range.
     */
    public TimeRange(LocalDateTime start, LocalDateTime end) {
        requireNonNull(start);
        requireNonNull(end);
        if (end.isBefore(start)) {
            throw new IllegalArgumentException("End date must be after start date");
        }

        this.start = start;
        this.end = end;
    }

    public LocalDateTime getStartDateTime() {
        return start;
    }

    public LocalDateTime getEndDateTime() {
        return end;
    }

    /**
     * Returns the duration in minutes within this time range.
     *
     * @return The number of minutes between the start and end date.
     */
    public long getDuration() {
        return Duration.between(start, end).toMinutes();
    }

    @Override
    public LocalDateTime getComparableDateTime() {
        return start;
    }

    @Override
    public int compareTo(TemporalComparable o) {
        return this.getComparableDateTime().compareTo(o.getComparableDateTime());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TimeRange)) {
            return false;
        }

        TimeRange otherRange = (TimeRange) other;
        return otherRange.start.equals(start)
                && otherRange.end.equals(end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}
