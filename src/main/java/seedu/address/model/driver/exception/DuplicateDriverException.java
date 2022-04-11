package seedu.address.model.driver.exception;
/**
 * Signals that the operation will result in duplicate Drivers (Drivers are considered duplicates if they have the same
 * identity).
 */
public class DuplicateDriverException extends RuntimeException {
    public DuplicateDriverException() {
        super("Operation would result in duplicate driver");
    }
}
