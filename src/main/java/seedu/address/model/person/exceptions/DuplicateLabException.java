package seedu.address.model.person.exceptions;

public class DuplicateLabException extends RuntimeException {

    public DuplicateLabException() {
        super("Operation would result in duplicate labs");
    }
}
