package seedu.address.model.policy.exceptions;

/**
 * Signals that the operation will result in duplicate Policies (Policies are considered duplicates if they have the
 * same identity).
 */
public class PolicyNotEditedException extends RuntimeException {
    public PolicyNotEditedException() {
        super("The edited policy is the same as the original policy");
    }
}
