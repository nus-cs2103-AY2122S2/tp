package seedu.contax.model.appointment.exceptions;

/**
 * Signals that the operation is unable to find the specified appointment.
 */
public class AppointmentNotFoundException extends RuntimeException {
    public AppointmentNotFoundException() {
        super("Operation could not find the supplied appointment");
    }
}
