package seedu.contax.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.contax.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.contax.model.appointment.exceptions.OverlappingAppointmentException;

/**
 * A list of appointments that enforces that its elements cannot have overlapping periods and does not allow
 * nulls. This check is performed using {@link Appointment#isOverlapping(Appointment)}, and will be performed
 * upon any modification to the list. Note that {@link #remove(Appointment)} uses Appointment#equals(Object)
 * to find the target appointment to remove.
 *
 * Supports a minimal set of list operations.
 */

public class DisjointAppointmentList implements Iterable<Appointment> {

    private final ObservableList<Appointment> appointments;
    private final ObservableList<Appointment> readOnlyAppointments;

    /**
     * Constructs a {@code DisjointAppointmentList}.
     */
    public DisjointAppointmentList() {
        this.appointments = FXCollections.observableArrayList();
        this.readOnlyAppointments = FXCollections.unmodifiableObservableList(this.appointments);
    }

    /**
     * Replaces the entire list of appointments with {@code appointments}.
     *
     * @param appointments The new list of appointments to use.
     */
    public void setAppointments(List<Appointment> appointments) {
        requireNonNull(appointments);
        this.appointments.setAll(appointments);
    }

    /**
     * Returns true if the list of appointments contains {@code target}.
     *
     * @param target The {@code appointment} object to check.
     * @return True if the list of appointments contains {@code target}, otherwise false.
     */
    public boolean contains(Appointment target) {
        requireNonNull(target);
        return this.appointments.contains(target);
    }

    /**
     * Adds the appointment {@code target} to this list of appointments.
     *
     * @param target The {@code appointment} object to add to the list.
     */
    public void add(Appointment target) {
        requireNonNull(target);
        if (contains(target)) {
            throw new OverlappingAppointmentException();
        }

        appointments.add(target);
    }

    /**
     * Replaces the appointment {@code target} in the list with {@code newAppointment}.
     *  {@code target} must exist in the list.
     *  {@code newAppointment} must not overlap with any appointment in the list excluding {@code target}.
     *
     * @param target The {@code appointment} to replace.
     * @param newAppointment The {@code appointment} to replace {@code target} with.
     */
    public void setAppointment(Appointment target, Appointment newAppointment) {
        requireAllNonNull(target, newAppointment);

        int indexOfTarget = appointments.indexOf(target);
        if (indexOfTarget < 0) {
            throw new OverlappingAppointmentException();
        }

        long clashing = appointments.stream().filter((appointment) -> (
                appointment.isOverlapping(newAppointment) && !appointment.equals(target))).count();

        if (clashing > 0) {
            throw new OverlappingAppointmentException();
        }

        appointments.set(indexOfTarget, newAppointment);
    }

    // TODO [APPOINTMENT V1.2] : Implement removal
    public void remove(Appointment target) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    /**
     * Returns the number of appointments in this list.
     *
     * @return The number of appointments in this list.
     */
    public int size() {
        return this.appointments.size();
    }

    /**
     * Returns a read-only {@code ObservableList} of the appointments in this list.
     *
     * @return A Read-Only list of appointments in this list.
     */
    public ObservableList<Appointment> asUnmodifiableObservableList() {
        return readOnlyAppointments;
    }

    @Override
    public Iterator<Appointment> iterator() {
        return appointments.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DisjointAppointmentList // instanceof handles nulls
                && appointments.equals(((DisjointAppointmentList) other).appointments));
    }

    @Override
    public int hashCode() {
        return appointments.hashCode();
    }
}
