package seedu.contax.model;

import javafx.collections.ObservableList;
import seedu.contax.model.appointment.Appointment;

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

}
