package seedu.address.model.interview.exceptions;

public class DuplicateInterviewException extends RuntimeException {
    /**
     * Signals that the operation will result in duplicate Persons (Persons are considered duplicates if they have the same
     * identity).
     */
    public DuplicateInterviewException() {
        super("Operation would result in duplicate persons");
    }
}
