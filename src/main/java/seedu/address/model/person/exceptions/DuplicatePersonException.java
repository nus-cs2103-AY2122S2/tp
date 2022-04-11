package seedu.address.model.person.exceptions;

/**
 * Signals that the operation will result in duplicate applicants (Applicants are considered
 * duplicates if they have the same identity).
 */
public class DuplicatePersonException extends RuntimeException {
    public DuplicatePersonException() {
        super("Operation would result in duplicate applicants");
    }
}
