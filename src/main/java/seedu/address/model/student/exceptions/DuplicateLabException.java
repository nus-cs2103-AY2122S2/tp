package seedu.address.model.student.exceptions;

public class DuplicateLabException extends RuntimeException {

    public DuplicateLabException() {
        super("Operation would result in duplicate labs");
    }
}
