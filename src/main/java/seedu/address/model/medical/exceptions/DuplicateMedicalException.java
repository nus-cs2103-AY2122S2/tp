package seedu.address.model.medical.exceptions;

/**
 * Signals that the operation will result in duplicate Medication
 * (Medical are considered duplicates if they have the same identity).
 */
public class DuplicateMedicalException extends RuntimeException {
    public DuplicateMedicalException() {
        super("Operation would result in duplicate medical information");
    }
}
