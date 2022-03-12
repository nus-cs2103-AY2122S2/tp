package seedu.address.model.interview.exceptions;

public class DuplicateInterivewException extends RuntimeException {
    public DuplicateInterivewException() {
        super("Operation would result in duplicate interviews");
    }
}
