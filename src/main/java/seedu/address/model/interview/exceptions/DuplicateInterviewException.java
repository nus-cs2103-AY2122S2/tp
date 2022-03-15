package seedu.address.model.interview.exceptions;

public class DuplicateInterviewException extends RuntimeException {
    public DuplicateInterviewException() {
        super("Operation would result in duplicate interviews");
    }
}
