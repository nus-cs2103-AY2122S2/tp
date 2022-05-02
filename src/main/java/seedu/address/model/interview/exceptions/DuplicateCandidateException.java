package seedu.address.model.interview.exceptions;

public class DuplicateCandidateException extends RuntimeException {
    /**
     * Signals that the operation will result in Interviews with duplicate candidates.
     */
    public DuplicateCandidateException() {
        super("Operation would result in duplicate candidates");
    }
}
