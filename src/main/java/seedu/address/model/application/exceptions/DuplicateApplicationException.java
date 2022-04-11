package seedu.address.model.application.exceptions;

/**
 * Signals that the operation will result in duplicate Application (Applications are considered duplicates if they have
 * the same name, which is an identity field of an Application in InternApply).
 */
public class DuplicateApplicationException extends RuntimeException {
    public DuplicateApplicationException() {
        super("Operation would result in duplicate applications");
    }
}
