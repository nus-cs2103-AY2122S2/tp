package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.appointment.Appointment;

/**
 * Unmodifiable view of appointment history book
 */
public interface ReadOnlyAppointmentHistoryBook {
    /**
     * Returns an unmodifiable view of the appointment history list.
     * This list will not contain any duplicate appointment history.
     */
    ObservableList<Appointment> getAppointmentHistoryList();

    /**
     * Updates the appointment history list.
     * @param model The model object.
     */
    void updateAppointmentHistoryList(Model model);
}
