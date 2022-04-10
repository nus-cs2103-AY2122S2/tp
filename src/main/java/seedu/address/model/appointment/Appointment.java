package seedu.address.model.appointment;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents an Appointment in Mr. Agent.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Appointment {
    // Data fields
    private final Description description;
    private final DateTime dateTime;
    /**
     * Every field must be present and not null.
     */
    public Appointment(Description description, DateTime dateTime) {
        this.description = description;
        this.dateTime = dateTime;
    }

    public Description getDescription() {
        return this.description;
    }
    public DateTime getDateTime() {
        return this.dateTime;
    }
    public LocalDateTime getLocalDateTime() {
        return this.dateTime.getLocalDateTime();
    }

    /**
     * Returns true if both appointments have the same date time.
     * This defines a weaker notion of equality between two appointments.
     */
    public boolean isSameAppointment(Appointment otherAppointment) {
        if (otherAppointment == this) {
            return true;
        }

        return otherAppointment != null && otherAppointment.getDateTime().equals(this.getDateTime());
    }


    /**
     * Returns true if both appointments have the same identity and data fields.
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

        Appointment otherAppointment = (Appointment) other;
        return otherAppointment.getDescription().equals(this.getDescription())
                && otherAppointment.getDateTime().equals(this.getDateTime());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(this.getDescription(), this.getDateTime());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Appointment: ")
                .append(this.getDescription())
                .append(" at ")
                .append(this.getDateTime().toString());

        return builder.toString();
    }
}
