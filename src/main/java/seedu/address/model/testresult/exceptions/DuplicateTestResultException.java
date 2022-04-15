package seedu.address.model.testresult.exceptions;

/**
 * Signals that the operation will result in duplicate TestResults (TestResults are considered duplicates if they belong
 * to the same NRIC and have the same medical test and results).
 */
public class DuplicateTestResultException extends RuntimeException {
    public DuplicateTestResultException() {
        super("Operation would result in duplicate test results");
    }

}
