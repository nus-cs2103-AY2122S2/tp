package seedu.address.model.interview.exceptions;

public class DuplicateInterviewException extends RuntimeException {
    /**
     * Signals that the operation will result in duplicate Interviews (Interviews are considered duplicates if they
     * have the same candidate and interview time slot).
     */
    public DuplicateInterviewException() {
        super("Operation would result in duplicate interviews");
    }
}
