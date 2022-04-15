package seedu.address.authentication;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents an authentication error encountered.
 */
public class AuthenticationException extends IllegalValueException {

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
