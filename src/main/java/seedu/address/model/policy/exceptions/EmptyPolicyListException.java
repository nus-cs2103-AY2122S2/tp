package seedu.address.model.policy.exceptions;

/**
 * Signals that the operation is not possible because the given policy index is not a valid one.
 */
public class EmptyPolicyListException extends RuntimeException {
    public EmptyPolicyListException() {
        super("This client has no policies");
    }
}
