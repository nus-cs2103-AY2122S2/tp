package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.UniqueAppointmentList;

/**
 * Wraps all data at the appointment-book level
 * Duplicates are not allowed (by .isSameAppointment comparison)
 */
public class AppointmentBook implements ReadOnlyAppointmentBook {
    private final UniqueAppointmentList appointments;

    {
        this.appointments = new UniqueAppointmentList();
    }

    public AppointmentBook() {
    }

    /**
     * Creates an AddressBook using the Appointments in the {@code toBeCopied}
     */
    public AppointmentBook(ReadOnlyAppointmentBook toBeCopied) {
        this();
        this.resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the appointment list with {@code appointment}.
     * {@code appointment} must not contain duplicate appointments.
     */
    public void setAppointments(List<Appointment> appointments) {
        this.appointments.setAppointments(appointments);
    }
    /**
     * Resets the existing data of this {@code AppointmentBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAppointmentBook newData) {
        requireNonNull(newData);

        this.setAppointments(newData.getAppointmentList());
    }

    //// appointment-level operations

    /**
     * Returns true if an appointment with the same identity as {@code appointment} exists in the appointment book.
     */
    public boolean hasAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return this.appointments.contains(appointment);
    }

    /**
     * Adds an appointment to the appointment book.
     * The insurance must not already exist in the appointment book.
     */
    public void addAppointment(Appointment a) {
        this.appointments.add(a);
    }

    /**
     * Replaces the given insurance {@code target} in the list with {@code editedInsurance}.
     * {@code target} must exist in the insurance book.
     * The insurance identity of {@code editedInsurance} must not be the same as another
     * existing insurance in the insurance book.
     */
    public void setAppointment(Appointment target, Appointment editedAppointment) {
        requireNonNull(editedAppointment);

        this.appointments.setAppointment(target, editedAppointment);
    }

    /**
     * Removes {@code key} from this {@code AppointmentBook}.
     * {@code key} must exist in the appointment book.
     */
    public void removeAppointment(Appointment key) {
        this.appointments.remove(key);
    }

    /**
     * Sorts the appointment list.
     * @param comparator Criteria for sorting.
     */
    public void sortAppointment(Comparator<Appointment> comparator) {
        this.appointments.sort(comparator);
    }

    //// util methods

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (Iterator<Appointment> it = appointments.iterator(); it.hasNext(); ) {
            Appointment appointment = it.next();
            builder.append(appointment.toString());
            builder.append("\n");
        }
        return builder.toString();
    }

    @Override
    public ObservableList<Appointment> getAppointmentList() {
        return this.appointments.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AppointmentBook // instanceof handles nulls
                && this.appointments.equals(((AppointmentBook) other).appointments));
    }

    @Override
    public int hashCode() {
        return this.appointments.hashCode();
    }
}
