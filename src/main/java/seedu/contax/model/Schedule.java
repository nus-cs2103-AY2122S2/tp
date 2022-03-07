package seedu.contax.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.contax.model.appointment.Appointment;
import seedu.contax.model.appointment.DisjointAppointmentList;

/**
 * Wraps all data at the Schedule level.
 * Overlapping appointments are not allowed, and is checked by .isOverlapping
 */
public class Schedule implements ReadOnlySchedule {

    private final DisjointAppointmentList appointments;

    /**
     * Constructs an empty {@code Schedule}.
     */
    public Schedule() {
        appointments = new DisjointAppointmentList();
    }

    /**
     * Creates a Schedule using the Appointments in the {@code toBeCopied}
     */
    public Schedule(ReadOnlySchedule toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the appointment list with {@code appointments}.
     * {@code appointments} must not contain overlapping appointments.
     */
    public void setAppointments(List<Appointment> appointments) {
        this.appointments.setAppointments(appointments);
    }

    /**
     * Resets the existing data of this {@code Schedule} with {@code newData}.
     */
    public void resetData(ReadOnlySchedule newData) {
        requireNonNull(newData);

        setAppointments(newData.getAppointmentsList());
    }

    //// appointment-level operations

    /**
     * Returns true if an appointment with the same identity as {@code target} exists in the schedule.
     */
    public boolean hasAppointment(Appointment target) {
        requireNonNull(target);
        return appointments.contains(target);
    }

    /**
     * Adds an appointment to the schedule.
     * An overlapping appointment must not already exist in the schedule.
     */
    public void addPerson(Appointment target) {
        appointments.add(target);
    }

    /**
     * Replaces the given appointment {@code target} in the list with {@code editedAppointment}.
     * {@code target} must exist in the schedule.
     * The appointment {@code editedAppointment} must not overlap with an existing appointment in the
     * schedule.
     */
    public void setPerson(Appointment target, Appointment editedAppointment) {
        requireNonNull(target);
        requireNonNull(editedAppointment);

        appointments.setAppointment(target, editedAppointment);
    }

    /**
     * Removes {@code target} from this {@code Schedule}.
     * {@code target} must exist in the schedule.
     */
    public void removeAppointment(Appointment target) {
        appointments.remove(target);
    }

    //// util methods

    @Override
    public String toString() {
        return appointments.size() + " persons";
    }

    @Override
    public ObservableList<Appointment> getAppointmentsList() {
        return appointments.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Schedule // instanceof handles nulls
                && appointments.equals(((Schedule) other).appointments));
    }

    @Override
    public int hashCode() {
        return appointments.hashCode();
    }
}
