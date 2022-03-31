package seedu.address.model.lab.exceptions;

public class DuplicateLabException extends RuntimeException {

    public DuplicateLabException() {
        super("Operation would result in duplicate labs");
    }
}
