package seedu.contax.model.chrono;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.function.Predicate;

import seedu.contax.commons.util.DateUtil;

/**
 * Tests that a {@code ScheduleItem} falls between the given date time range.
 * It returns true if the ScheduleItem being tested contains some sub-range of the predicate's date range.
 */
public class DateRangePredicate implements Predicate<ScheduleItem> {
    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Constructs a DateRangePredicate object.
     *
     * @param start The start of the range to filter.
     * @param end The end of the range to filter.
     */
    public DateRangePredicate(LocalDateTime start, LocalDateTime end) {
        requireNonNull(start);
        requireNonNull(end);

        this.start = start;
        this.end = end;
    }

    @Override
    public boolean test(ScheduleItem scheduleItem) {
        LocalDateTime appointmentStart = scheduleItem.getStartDateTime();
        LocalDateTime appointmentEnd = scheduleItem.getEndDateTime();

        return (DateUtil.isBeforeOrEqual(appointmentStart, start) && DateUtil.isAfterOrEqual(appointmentEnd, end))
                || (DateUtil.isAfterOrEqual(appointmentStart, start) && DateUtil.isBeforeOrEqual(appointmentEnd, end))
                || (DateUtil.isAfterOrEqual(appointmentStart, start) && DateUtil.isBeforeOrEqual(appointmentStart, end))
                || (DateUtil.isAfterOrEqual(appointmentEnd, start) && DateUtil.isBeforeOrEqual(appointmentEnd, end));
    }
}
