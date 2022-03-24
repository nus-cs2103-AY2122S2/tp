package seedu.contax.model.chrono;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents an entry in the Schedule. This class only implements temporal-related operations required for
 * {@code DisjointScheduleItemList} and {@code Schedule} maintenance. It does not specify any problem-domain
 * specific logic.
 */
public abstract class ScheduleItem implements TemporalComparable {
    private final TimeRange period;

    /**
     * Constructs a {@code ScheduleItem}.
     *
     * @param period The time period over which this ScheduleItem occupies.
     */
    public ScheduleItem(TimeRange period) {
        requireNonNull(period);
        this.period = period;
    }

    /**
     * Constructs a {@code ScheduleItem}.
     *
     * @param start The start Date-Time of this ScheduleItem.
     * @param end The end Date-Time of this ScheduleItem.
     */
    public ScheduleItem(LocalDateTime start, LocalDateTime end) {
        requireNonNull(start);
        requireNonNull(end);
        this.period = new TimeRange(start, end);
    }

    public LocalDateTime getStartDateTime() {
        return period.getStartDateTime();
    }

    public LocalDateTime getEndDateTime() {
        return period.getEndDateTime();
    }

    /**
     * Returns true if this {@code ScheduleItem} overlaps with {@code other}, that is, the start time of one
     * ScheduleItem is strictly before the end time (start time + duration) of the other ScheduleItem.
     * Note that ScheduleItems are not considered to be overlapping if the start time of one ScheduleItem
     * is exactly the end time of the other.
     *
     * @param other The other {@code ScheduleItem} to compare against.
     * @return True if both ScheduleItems overlap, otherwise false.
     */
    public boolean isOverlapping(ScheduleItem other) {
        requireNonNull(other);
        if (this.equals(other)) {
            return true;
        }

        final LocalDateTime otherStartDateTime = other.getStartDateTime();
        final LocalDateTime selfStartDateTime = getStartDateTime();

        if (otherStartDateTime.isAfter(selfStartDateTime.minusSeconds(1))) {
            // In this case, other.startDateTime is after this.startDateTime.
            return otherStartDateTime.isBefore(getEndDateTime());
        }

        // other.startDateTime is strictly before this.startDateTime, need to check if other.endDateTime
        // overflows into this.startDateTime
        return (other.getEndDateTime().isAfter(selfStartDateTime));
    }

    /**
     * Returns true if both ScheduleItems have the same time range.
     * This is a stronger notion of equality, and implementing subclasses should minimally check for this
     * condition in the equals function.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ScheduleItem)) {
            return false;
        }

        ScheduleItem otherItem = (ScheduleItem) other;
        return otherItem.getStartDateTime().equals(this.getStartDateTime())
                && otherItem.getEndDateTime().equals(this.getEndDateTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(period);
    }

    @Override
    public LocalDateTime getComparableDateTime() {
        return this.getStartDateTime();
    }

    @Override
    public int compareTo(TemporalComparable other) {
        return this.getStartDateTime().compareTo(other.getComparableDateTime());
    }

}
