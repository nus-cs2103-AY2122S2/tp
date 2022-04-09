package seedu.contax.model.chrono;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.Objects;

import seedu.contax.commons.util.DateUtil;

/**
 * Represents an entry in the Schedule. This class only implements temporal-related operations required for
 * comparing Temporal items and {@code Schedule} maintenance. It does not specify any problem-domain
 * specific logic.
 * {@code ScheduleItem} objects are immutable.
 */
public abstract class ScheduleItem implements TemporalComparable {
    private final TimeRange period;

    /**
     * Constructs a {@code ScheduleItem}.
     *
     * @param period The time period over which this {@code ScheduleItem} spans.
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

    /** Returns the start Date-Time of this ScheduleItem. */
    public LocalDateTime getStartDateTime() {
        return period.getStartDateTime();
    }

    /** Returns the end Date-Time of this ScheduleItem. */
    public LocalDateTime getEndDateTime() {
        return period.getEndDateTime();
    }

    /**
     * Returns true if this {@code ScheduleItem} overlaps with {@code other}, that is, the start Date-Time of
     * one ScheduleItem is strictly before the end Date-Time (start time + duration) of the other ScheduleItem.
     * Note that ScheduleItems are not considered to be overlapping if the start of one ScheduleItem
     * is exactly the end of the other.
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

        if (DateUtil.isAfterOrEqual(otherStartDateTime, selfStartDateTime)) {
            // It is not overlapping if the other item starts exactly at this item's end time
            return otherStartDateTime.isBefore(getEndDateTime());
        }

        // other.startDateTime is strictly before this.startDateTime, need to check if other.endDateTime
        // overflows into this.startDateTime
        return (other.getEndDateTime().isAfter(selfStartDateTime));
    }

    /**
     * Returns true if both ScheduleItems span exactly the same time range.
     * This is a stronger notion of equality, and extending subclasses should minimally check for this
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
