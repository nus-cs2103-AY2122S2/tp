package seedu.contax.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.contax.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Objects;

import seedu.contax.model.person.Person;

/**
 * Represents an appointment in the schedule.
 *
 */
public class Appointment {

    // Appointment identification fields
    private final StartDateTime startDateTime;

    // Data fields
    private final Name name;
    private final Duration duration;
    private final Person person;

    /**
     * Constructs an {@code Appointment}.
     * The fields {@code name, startDateTime, duration} must be present and not null.
     * The {@code person} argument is optional, and may be null.
     *
     * @param name A valid Appointment Name.
     * @param startDateTime A valid Appointment Starting DateTime.
     * @param duration A valid Appointment Duration.
     * @param person A valid Person or null.
     */
    public Appointment(Name name, StartDateTime startDateTime, Duration duration, Person person) {
        requireAllNonNull(name, startDateTime, duration);

        this.name = name;
        this.startDateTime = startDateTime;
        this.duration = duration;
        this.person = person;
    }

    public Name getName() {
        return this.name;
    }

    public StartDateTime getStartDateTime() {
        return this.startDateTime;
    }

    public Duration getDuration() {
        return this.duration;
    }

    public Person getPerson() {
        return person;
    }

    /**
     * Returns the ending DateTime of this appointment.
     *
     * @return The end DateTime of this appointment.
     */
    public LocalDateTime getEndDateTime() {
        return getStartDateTime().dateTime.plusMinutes(getDuration().duration);
    }

    /**
     * Returns true if both appointments overlap, that is, the start time of an appointment is before the
     * (start time + duration) of the other appointment.
     *
     * @param other The other {@code Appointment} to compare against.
     * @return True if both appointments overlap, otherwise false.
     */
    public boolean isOverlapping(Appointment other) {
        requireNonNull(other);
        if (this.equals(other)) {
            return true;
        }

        return (getEndDateTime().isBefore(other.getStartDateTime().dateTime)
                || other.getEndDateTime().isBefore(getStartDateTime().dateTime));
    }

    /**
     * Returns true if both appointments have the same name and data fields.
     * This defines a stronger notion of equality between two appointments.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Appointment)) {
            return false;
        }

        Appointment otherPerson = (Appointment) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getStartDateTime().equals(getStartDateTime())
                && otherPerson.getDuration().equals(getDuration())
                && otherPerson.getPerson().equals(getPerson());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, startDateTime, duration, person);
    }

    @Override
    public String toString() {
        return getName()
                + "; Start Date Time: "
                + getStartDateTime()
                + "; Duration: "
                + getDuration()
                + "; Person: "
                + getPerson();
    }
}
