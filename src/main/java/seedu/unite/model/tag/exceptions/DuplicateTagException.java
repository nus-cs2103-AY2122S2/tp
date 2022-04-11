package seedu.unite.model.tag.exceptions;

/**
 * Signals that the operation will result in duplicate Tag (Tags are considered duplicates if they have the same
 * identity).
 */
public class DuplicateTagException extends RuntimeException {
    public DuplicateTagException() {
        super("Operation would result in duplicate tags");
    }
}
