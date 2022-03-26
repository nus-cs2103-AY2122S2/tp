package seedu.trackbeau.model.uniquelist.exceptions;

/**
 * Signals that the operation will result in duplicate UniqueItems
 * (UniqueItems are considered duplicates if they have the same identity).
 */
public class DuplicateItemException extends RuntimeException {
    public DuplicateItemException() {
        super("Operation would result in duplicate items");
    }
}