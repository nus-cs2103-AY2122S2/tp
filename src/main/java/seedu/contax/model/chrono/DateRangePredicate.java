package seedu.contax.model.chrono;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.function.Predicate;

import seedu.contax.commons.util.DateUtil;

/**
 * Tests if a {@code ScheduleItem} falls between the given date time range.
 * It returns true if the ScheduleItem being tested contains some sub-range of the predicate's date range.
 */
public class DateRangePredicate implements Predicate<ScheduleItem> {
    private final LocalDateTime rangeStart;
    private final LocalDateTime rangeEnd;

    /**
     * Constructs a DateRangePredicate object.
     *
     * @param start The start of the range to filter.
     * @param end The end of the range to filter.
     */
    public DateRangePredicate(LocalDateTime start, LocalDateTime end) {
        requireNonNull(start);
        requireNonNull(end);

        this.rangeStart = start;
        this.rangeEnd = end;
    }

    @Override
    public boolean test(ScheduleItem scheduleItem) {
        LocalDateTime targetStart = scheduleItem.getStartDateTime();
        LocalDateTime targetEnd = scheduleItem.getEndDateTime();

        return (DateUtil.isBeforeOrEqual(targetStart, rangeStart) && DateUtil.isAfterOrEqual(targetEnd, rangeEnd))
                || (DateUtil.isAfterOrEqual(targetStart, rangeStart) && DateUtil.isBeforeOrEqual(targetEnd, rangeEnd))
                || (DateUtil.isAfterOrEqual(targetStart, rangeStart)
                    && DateUtil.isBeforeOrEqual(targetStart, rangeEnd))
                || (DateUtil.isAfterOrEqual(targetEnd, rangeStart) && DateUtil.isBeforeOrEqual(targetEnd, rangeEnd));
    }
}
