package seedu.address.model.module.exceptions;

/**
 * Signals that the operation will result in duplicate modules (modules are considered duplicates if they have the same
 * module code and name).
 */
public class DuplicateModuleException extends RuntimeException {
    public DuplicateModuleException() {
        super("Operation would result in duplicate modules");
    }
}
