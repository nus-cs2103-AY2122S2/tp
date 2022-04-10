package seedu.address.model.insurance.exceptions;

/**
 * Signals that the operation will result in duplicate Insurances
 * (Insurances are considered duplicates if they have the same
 * identity).
 */
public class DuplicateInsuranceException extends RuntimeException {
    public DuplicateInsuranceException() {
        super("Operation would result in duplicate Insurance");
    }
}
