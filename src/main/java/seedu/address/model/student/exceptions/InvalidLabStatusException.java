package seedu.address.model.student.exceptions;

public class InvalidLabStatusException extends RuntimeException {

    public InvalidLabStatusException() {
        super("The Lab Status of the lab is invalid or incorrect.");
    }
}
