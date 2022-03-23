package seedu.contax.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.contax.commons.util.CollectionUtil.requireAllNonNull;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.contax.model.appointment.exceptions.AppointmentNotFoundException;
import seedu.contax.model.appointment.exceptions.OverlappingAppointmentException;
import seedu.contax.model.chrono.ScheduleItem;
import seedu.contax.model.chrono.TimeRange;

/**
 * A list of appointments that enforces that its elements cannot have overlapping periods and does not allow
 * nulls. This check is performed using {@link Appointment#isOverlapping(ScheduleItem)}, and will be performed
 * upon any modification to the list. It also enforces a chronological ordering of the appointments in the
 * list, done by sorting {@link Appointment#getStartDateTime()} since appointments are disjoint.
 *
 * Note that {@link #remove(Appointment)} uses Appointment#equals(Object) to find the target appointment to
 * remove.
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

        if (!isAppointmentListDisjoint(appointments)) {
            throw new OverlappingAppointmentException();
        }

        this.appointments.setAll(appointments);
        this.appointments.sort(Appointment::compareTo);
    }

    /**
     * Replaces the entire list of appointments with {@code toCopy}.
     *
     * @param toCopy The {@code DisjointAppointmentList} from which the list of appointments should be
     *               created from.
     */
    public void setAppointments(DisjointAppointmentList toCopy) {
        requireNonNull(toCopy);
        this.appointments.setAll(toCopy.appointments);
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
     * Returns true if the list of appointments contains some appointment that overlaps with {@code target}.
     *
     * @param target The {@code appointment} object to check against.
     * @return True if the list of appointments contains some appointment that overlaps with {@code target},
     *         otherwise false.
     */
    public boolean containsOverlapping(Appointment target) {
        requireNonNull(target);
        long overlappingAppointmentCount = appointments.stream().filter((appointment) -> (
                appointment.isOverlapping(target))).count();

        return overlappingAppointmentCount > 0;
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

        // Check for overlapping appointments
        long overlappingAppointmentCount = appointments.stream().filter((appointment) -> (
                appointment.isOverlapping(target))).count();

        if (overlappingAppointmentCount > 0) {
            throw new OverlappingAppointmentException();
        }

        appointments.add(target);
        shiftAppointmentToPosition(appointments.size() - 1);
    }

    /**
     * Replaces the appointment {@code target} in the list with {@code newAppointment}.
     * {@code target} must exist in the list.
     * {@code newAppointment} must not overlap with any appointment in the list excluding {@code target}.
     *
     * @param target The {@code Appointment} to replace.
     * @param newAppointment The {@code Appointment} to replace {@code target} with.
     * @throws AppointmentNotFoundException If {@code target} cannot be found in the list.
     */
    public void setAppointment(Appointment target, Appointment newAppointment) {
        requireAllNonNull(target, newAppointment);

        int indexOfTarget = appointments.indexOf(target);
        if (indexOfTarget < 0) {
            throw new AppointmentNotFoundException();
        }

        long clashing = appointments.stream().filter((appointment) -> (
                appointment.isOverlapping(newAppointment) && !appointment.equals(target))).count();

        if (clashing > 0) {
            throw new OverlappingAppointmentException();
        }

        appointments.set(indexOfTarget, newAppointment);
        shiftAppointmentToPosition(indexOfTarget);
    }

    /**
     * Removes the appointment {@code target} from the list of appointments.
     *
     * @param target The appointment to remove from the list.
     * @throws AppointmentNotFoundException If {@code target} cannot be found in the list.
     */
    public void remove(Appointment target) {
        requireNonNull(target);

        int indexOfTarget = appointments.indexOf(target);
        if (indexOfTarget < 0) {
            throw new AppointmentNotFoundException();
        }

        appointments.remove(indexOfTarget);
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

    /**
     * Returns a list of slots within the supplied start and end DateTime search window which are of at least
     * {@code minimumDuration} minutes that are not occupied by any appointments.
     *
     * @param start The start of the search window.
     * @param end The end of the search window.
     * @param minimumDuration The minimum size of the empty slot.
     * @return A list of slots between appointments that are of at least {@code minimumDuration} minutes.
     */
    public List<TimeRange> findAvailableSlotsInRange(LocalDateTime start, LocalDateTime end,
                                                     int minimumDuration) {
        requireAllNonNull(start, end, minimumDuration);
        if (minimumDuration <= 0) {
            throw new IllegalArgumentException("Duration has to be a positive integer");
        }

        ArrayList<TimeRange> slotsFound = new ArrayList<>();
        if (!(start.isBefore(end))) {
            return slotsFound;
        }

        if (size() == 0) {
            slotsFound.add(new TimeRange(start, end));
            return slotsFound;
        }

        Appointment firstAppointment = appointments.get(0);
        Appointment lastAppointment = appointments.get(size() - 1);
        if (Duration.between(start, firstAppointment.getStartDateTime()).toMinutes() >= minimumDuration) {
            slotsFound.add(new TimeRange(start, getEarlier(firstAppointment.getStartDateTime(), end)));
        }
        findAvailableSlotsBetweenAppointments(slotsFound, start, end, minimumDuration);
        if (Duration.between(lastAppointment.getEndDateTime(), end).toMinutes() >= minimumDuration) {
            slotsFound.add(new TimeRange(getLater(start, lastAppointment.getEndDateTime()), end));
        }

        return slotsFound;
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

    /**
     * Returns true if there are overlapping appointments in the supplied list.
     *
     * @param target The list to check for overlapping appointments.
     * @return True if there are overlapping appointments, false otherwise.
     */
    private boolean isAppointmentListDisjoint(List<Appointment> target) {
        for (int i = 0; i < target.size() - 1; i++) {
            for (int j = i + 1; j < target.size(); j++) {
                if (target.get(i).isOverlapping(target.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Performs the shifting operation in insertion sort, omitting the insert into list component of
     * insertion sort, and shifts the appointment at the given {@code index} into its sorted position.
     * Note that this function may only be called if only the given appointment at {@code index} is out
     * of position.
     *
     * @param index The appointment to shift into place. This must be a valid index in the appointments list.
     */
    private void shiftAppointmentToPosition(int index) {
        assert (index >= 0 && index < appointments.size());

        Appointment target = appointments.get(index);
        int otherIndex = index;

        // Determine direction
        if (index - 1 >= 0 && target.compareTo(appointments.get(index - 1)) < 0) {
            // target < index - 1, shift left
            otherIndex = index - 1;
            while (otherIndex >= 0 && target.compareTo(appointments.get(otherIndex)) < 0) {
                appointments.set(otherIndex + 1, appointments.get(otherIndex)); // Right Shift
                otherIndex--;
            }
            appointments.set(otherIndex + 1, target);
        } else if (index + 1 < appointments.size() && target.compareTo(appointments.get(index + 1)) > 0) {
            // target > index + 1
            otherIndex = index + 1;
            while (otherIndex < appointments.size() && target.compareTo(appointments.get(otherIndex)) > 0) {
                appointments.set(otherIndex - 1, appointments.get(otherIndex)); // Left Shift
                otherIndex++;
            }
            appointments.set(otherIndex - 1, target);
        }
    }

    /**
     * Returns a list of slots between appointments, within the supplied start and end DateTime search window,
     * that are of at least {@code minimumDuration} minutes.
     *
     * @param resultList The list that slots found should be added to.
     * @param start The start of the search window.
     * @param end The end of the search window.
     * @param minimumDuration The minimum size of the empty slot.
     */
    private void findAvailableSlotsBetweenAppointments(ArrayList<TimeRange> resultList, LocalDateTime start,
                                           LocalDateTime end, long minimumDuration) {
        for (int i = 0; i < size() - 1; i++) {
            LocalDateTime gapStart = getLater(appointments.get(i).getEndDateTime(), start);
            LocalDateTime gapEnd = getEarlier(appointments.get(i + 1).getStartDateTime(), end);

            if (!(gapEnd.isAfter(start))) {
                continue;
            } else if (gapStart.isAfter(end)) {
                return;
            }

            long minutesBetween = gapStart.until(gapEnd, ChronoUnit.MINUTES);
            if (minutesBetween >= minimumDuration) {
                resultList.add(new TimeRange(gapStart, gapEnd));
            }
        }
    }

    private LocalDateTime getEarlier(LocalDateTime dateTime1, LocalDateTime dateTime2) {
        if (dateTime1.isAfter(dateTime2)) {
            return dateTime2;
        }
        return dateTime1;
    }

    private LocalDateTime getLater(LocalDateTime dateTime1, LocalDateTime dateTime2) {
        if (dateTime1.isAfter(dateTime2)) {
            return dateTime1;
        }
        return dateTime2;
    }
}
