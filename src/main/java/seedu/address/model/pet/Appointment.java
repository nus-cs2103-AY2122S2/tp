package seedu.address.model.pet;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Pet's upcoming appointment in the address book.
 * Guarantees: immutable; is always valid
 */
public class Appointment {
    public final String value;

    /**
     * Constructs a {@code Appointment}.
     *
     * @param appointment A pet's appointment.
     */
    public Appointment(String appointment) {
        requireNonNull(appointment);
        value = appointment;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Appointment
                && value.equals(((Appointment) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}