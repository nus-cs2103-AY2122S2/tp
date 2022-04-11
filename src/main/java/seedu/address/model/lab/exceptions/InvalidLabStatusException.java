package seedu.address.model.lab.exceptions;

public class InvalidLabStatusException extends IllegalLabStateException {

    public InvalidLabStatusException() {
        super("The Lab Status of the lab is invalid or incorrect.");
    }

}
