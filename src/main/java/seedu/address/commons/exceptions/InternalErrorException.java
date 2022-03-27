package seedu.address.commons.exceptions;

/**
 * Signals that some operation fail.
 */
public class InternalErrorException extends Exception {
    /**
     * @param message should contain relevant information on the failed constraint(s)
     */
    public InternalErrorException(String message) {
        super(message);
    }

    /**
     * @param message should contain relevant information on the failed constraint(s)
     * @param cause of the main exception
     */
    public InternalErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
