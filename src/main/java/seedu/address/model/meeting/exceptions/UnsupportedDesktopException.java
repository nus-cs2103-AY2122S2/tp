package seedu.address.model.meeting.exceptions;

/**
 * Signals that the operation is unable to access user device desktop functions.
 */
public class UnsupportedDesktopException extends RuntimeException {
    public UnsupportedDesktopException() {
        super("Unable to interact with device desktop capabilities");
    }
}
