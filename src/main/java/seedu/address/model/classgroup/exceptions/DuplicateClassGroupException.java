package seedu.address.model.classgroup.exceptions;

/**
 * Signals that the operation will result in duplicate ClassGroups (ClassGroups are considered duplicates
 * if they have the same class group ID, type and module code).
 */
public class DuplicateClassGroupException extends RuntimeException {
    public DuplicateClassGroupException() {
        super("Operation would result in duplicate class groups");
    }
}
