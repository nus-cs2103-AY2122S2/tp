package seedu.address.model.consultation.exceptions;

/**
 * Signals that the operation will result in duplicate Persons (Persons are considered duplicates if they have the same
 * identity).
 */
public class DuplicateConsultationException extends RuntimeException {
    public DuplicateConsultationException() {
        super("Operation would result in duplicate consultations");
    }
}
