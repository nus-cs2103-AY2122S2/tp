package seedu.address.model.lab.exceptions;

/**
 * Exception thrown when creation of a Lab in illegal state is attempted
 * (eg. Lab that are GRADED but has no marks, Lab that are not GRADED but has marks)
 */

public class IllegalLabStateException extends RuntimeException {

    private static final String EXCEPTION_MESSAGE = "Illegal Lab state found";

    public IllegalLabStateException() {
        super(EXCEPTION_MESSAGE);
    }

    public IllegalLabStateException(String message) {
        super(EXCEPTION_MESSAGE + "\n" + message);
    }

}
