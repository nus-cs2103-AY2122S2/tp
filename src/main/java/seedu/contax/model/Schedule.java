package seedu.contax.model;

import static java.util.Objects.requireNonNull;
import static seedu.contax.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.contax.model.appointment.Appointment;
import seedu.contax.model.appointment.DisjointAppointmentList;
import seedu.contax.model.chrono.TimeRange;

/**
 * Wraps all data at the Schedule level.
 * Overlapping appointments are not allowed, which is enforced by {@link DisjointAppointmentList}.
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

    /**
     * Replaces the contents of the appointment list with {@code appointments}.
     * {@code appointments} must not contain overlapping appointments.
     */
    public void setAppointments(List<Appointment> appointments) {
        this.appointments.setAppointments(appointments);
    }

    /**
     * Resets the existing data of this {@code Schedule} to {@code newData}.
     */
    public void resetData(ReadOnlySchedule newData) {
        requireNonNull(newData);

        setAppointments(newData.getAppointmentList());
    }

    /**
     * Adds an appointment to the schedule.
     * There must not exist any appointment in the schedule that overlaps with {@code target}.
     */
    public void addAppointment(Appointment target) {
        appointments.add(target);
    }

    /**
     * Replaces the given appointment {@code target} in the list with {@code editedAppointment}.
     * {@code target} must exist in the schedule.
     * The appointment {@code editedAppointment} must not overlap with an existing appointment in the
     * schedule.
     */
    public void setAppointment(Appointment target, Appointment editedAppointment) {
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

    // ====== Read Methods ===================================================================================

    @Override
    public ObservableList<Appointment> getAppointmentList() {
        return appointments.asUnmodifiableObservableList();
    }

    @Override
    public boolean hasAppointment(Appointment target) {
        requireNonNull(target);
        return appointments.contains(target);
    }

    @Override
    public boolean hasOverlappingAppointment(Appointment target) {
        requireNonNull(target);
        return appointments.containsOverlapping(target);
    }

    @Override
    public List<TimeRange> findSlotsBetweenAppointments(LocalDateTime start, LocalDateTime end,
                                                        int minimumDuration) {
        requireAllNonNull(start, end, minimumDuration);
        return appointments.findAvailableSlotsInRange(start, end, minimumDuration);
    }

    // ====== Util Methods ===================================================================================

    @Override
    public String toString() {
        return appointments.size() + " appointments";
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
