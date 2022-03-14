package seedu.contax.model.appointment;

import java.time.LocalDateTime;
import java.util.function.Predicate;

/**
 * Tests that a {@code Appointment} falls between the given date time range.
 * It includes appointments that extend beyond the period, and will include all appointments that contain
 * some sub-range of the filtered date range.
 */
public class DateRangePredicate implements Predicate<Appointment> {
    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Constructs a DateRangePredicate object.
     *
     * @param start The start of the range to filter.
     * @param end The end of the range to filter.
     */
    public DateRangePredicate(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean test(Appointment appointment) {
        LocalDateTime appointmentStart = appointment.getStartDateTime().value;
        LocalDateTime appointmentEnd = appointment.getEndDateTime();

        return (appointmentStart.isBefore(start) && appointmentEnd.isAfter(end))
                || (appointmentStart.isAfter(start) && appointmentEnd.isBefore(end))
                || (appointmentStart.isAfter(start) && appointmentStart.isBefore(end))
                || (appointmentEnd.isAfter(start) && appointmentEnd.isBefore(end));
    }
}
