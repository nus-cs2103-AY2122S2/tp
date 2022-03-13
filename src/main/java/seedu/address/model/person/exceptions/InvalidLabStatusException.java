package seedu.address.model.person.exceptions;

public class InvalidLabStatusException extends RuntimeException {

    public InvalidLabStatusException() {
        super("The Lab Status of the lab is invalid.");
    }
}
