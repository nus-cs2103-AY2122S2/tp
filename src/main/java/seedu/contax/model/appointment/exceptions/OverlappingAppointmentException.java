package seedu.contax.model.appointment.exceptions;

/**
 * Signals that the operation will result in overlapping Appointments.
 * Appointments are overlapping if an appointment starts before the other appointment ends.
 */
public class OverlappingAppointmentException extends RuntimeException {
    public OverlappingAppointmentException() {
        super("Operation would result in overlapping appointments");
    }
}
