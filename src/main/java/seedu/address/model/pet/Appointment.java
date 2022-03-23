package seedu.address.model.pet;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Pet's upcoming appointment in the address book.
 * Guarantees: immutable; is always valid.
 */
public class Appointment implements Comparable<Appointment> {

    /** Regex to match value attribute. eg: "Mar-04-2022 09:30 AM at NUS VET".*/
    public static final String VALIDATION_REGEX =
            "\\w{3}-\\d{2}-\\d{4} \\d{1}:\\d{2} \\w{2} \\w+(\\s\\w+){1,}";

    /** Date and time of appointment in "dd-MM-yyyy HH:mm" format.*/
    public final LocalDateTime dateTime;
    /** Location of appointment.*/
    public final String location;
    /**
     * Appointment details comprising information from dateTime and location.
     * Format of value: dateTime + " at " + location. To be reflected in GUI.
     */
    public final String value;

    /**
     * Constructs an {@code Appointment} from the user input details.
     * @param dateTime in LocalDateTime format.
     * @param location of appointment.
     */
    public Appointment(LocalDateTime dateTime, String location) {
        requireAllNonNull(dateTime, location);
        this.dateTime = dateTime;
        this.location = location;
        this.value = formatDateTime(dateTime) + " at " + location;
    }

    /**
     * Constructs an {@code Appointment} from the retrieved stored details.
     * @param value String representation of appointment details retrieved from storage.
     */
    public Appointment(String value) {
        requireNonNull(value);
        if (value.equals("")) {
            this.value = value;
            this.location = null;
            this.dateTime = null;
        } else {
            String[] appointmentDetails = value.split(" at ");
            String retrievedDateTime = appointmentDetails[0];
            String retrievedLocation = appointmentDetails[1];
            DateTimeFormatter formatOfRetrievedDateTime = DateTimeFormatter.ofPattern("MMM-dd-yyyy h:mm a");
            this.value = value;
            this.location = retrievedLocation;
            System.out.println(retrievedDateTime);
            this.dateTime = LocalDateTime.parse(retrievedDateTime, formatOfRetrievedDateTime);
        }
    }

    /**
     * Constructs an {@code Appointment} for empty appointment.
     * To be used when appointment field is empty or when appointment is cleared.
     */
    public Appointment() {
        this.value = "";
        this.dateTime = null;
        this.location = null;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    public String getLocation() {
        return this.location;
    }

    /**
     * Formats the LocalDateTime to String representation in format of "MMM-dd-yyyy h:mm a"
     * to be reflected in GUI and for storage.
     * @param dateTime in LocalDateTime.
     * @return String representation of dateTime in "MMM-dd-yyyy h:mm a".
     */
    private String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-dd-yyyy h:mm a");
        String formattedDateTime = dateTime.format(formatter);
        return formattedDateTime;
    }

    /**
     * Returns true if a given string is a valid appointment.
     * @param test The input string.
     * @return True if input matches VALIDATION_REGEX, False otherwise.
     */
    public static boolean isValidAppointment(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Compares appointment objects based on their dateTime attribute.
     *
     * @param other appointment to be compared with.
     * @return Value signifying in the difference between the comparing attribute.
     */
    @Override
    public int compareTo(Appointment other) {
        if (this.dateTime == null && other.getDateTime() == null) {
            return 0;
        } else if (this.dateTime != null && other.getDateTime() != null) {
            return this.dateTime.compareTo(other.getDateTime());
        } else if ((this.dateTime == null && other.getDateTime() != null)) {
            return 1;
        } else {
            return -1;
        }
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
