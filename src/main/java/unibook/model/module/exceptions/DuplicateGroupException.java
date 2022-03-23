package unibook.model.module.exceptions;

/**
 * Signals that a given operation would create duplicate groups in a module.
 */
public class DuplicateGroupException extends RuntimeException {
    public DuplicateGroupException() {
        super("Operation would result in a duplicate group");
    }
}
