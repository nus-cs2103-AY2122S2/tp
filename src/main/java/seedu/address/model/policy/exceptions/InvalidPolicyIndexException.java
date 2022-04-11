package seedu.address.model.policy.exceptions;

/**
 * Signals that the operation is not possible because the given policy index is not a valid one.
 */
public class InvalidPolicyIndexException extends RuntimeException {
    public InvalidPolicyIndexException() {
        super("There is no policy at this index");
    }
}
