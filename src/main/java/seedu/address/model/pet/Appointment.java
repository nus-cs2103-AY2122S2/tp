package seedu.address.model.pet;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Pet's upcoming appointment in the address book.
 * Guarantees: immutable; is always valid.
 */
public class Appointment {
    public final String value;
    /**
     * Regex to match appointment format.
     * eg: "Mar-04-2022 09:30 AM at NUS VET".
     */
    public static final String VALIDATION_REGEX =
            "\\w{3}-\\d{2}-\\d{4} \\d{2}:\\d{2} \\w{2} \\w+(\\s\\w+){1,}";

    /**
     * Constructs an {@code Appointment}.
     *
     * @param appointment A valid appointment details.
     */
    public Appointment(String appointment) {
        requireNonNull(appointment);
        this.value = appointment;
    }

    /**
     * Returns true if a given string is a valid appointment
     * @param test The input string.
     * @return True if input matches VALIDATION_REGEX, False otherwise.
     */
    public static boolean isValidAppointment(String test) {
        return test.matches(VALIDATION_REGEX);
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