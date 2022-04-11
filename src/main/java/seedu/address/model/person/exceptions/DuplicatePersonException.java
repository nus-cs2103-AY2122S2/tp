package seedu.address.model.person.exceptions;


public class DuplicatePersonException extends RuntimeException {
    public DuplicatePersonException() {
        super("Operation would result in duplicate users");
    }
}

