package seedu.contax.model;

import java.time.LocalDateTime;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.contax.model.appointment.Appointment;
import seedu.contax.model.chrono.TimeRange;

/**
 * Unmodifiable view of a schedule.
 */
public interface ReadOnlySchedule {

    /**
     * Returns an unmodifiable view of the list of appointments.
     * This list will not contain any overlapping appointments.
     */
    ObservableList<Appointment> getAppointmentList();

    /**
     * Returns true if an appointment that overlaps with {@code target} exists in the schedule.
     */
    boolean hasOverlappingAppointment(Appointment target);

    /**
     * Returns true if an appointment that is equals to {@code target} exists in the schedule.
     */
    boolean hasAppointment(Appointment target);

    /**
     * Returns a list of appointments that can allow another appointment of {@code minimumDuration} to be
     * slotted between it and the subsequent appointment in the schedule, subject to the supplied start and
     * end DateTime search window.
     *
     * @param start The start of the search window.
     * @param end The end of the search window.
     * @param minimumDuration The minimum size of the empty slot.
     * @return A list of appointments that can allow another appointment of {@code minimumDuration} to be
     *         slotted between it and the subsequent appointment.
     */
    List<TimeRange> findSlotsBetweenAppointments(LocalDateTime start, LocalDateTime end,
                                                 int minimumDuration);
}
