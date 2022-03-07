package seedu.contax.model;

import javafx.collections.ObservableList;
import seedu.contax.model.appointment.Appointment;

/**
 * Unmodifiable view of a schedule
 */
public interface ReadOnlySchedule {

    /**
     * Returns an unmodifiable view of the list of appointments.
     * This list will not contain any overlapping appointments.
     */
    ObservableList<Appointment> getAppointmentList();

}
