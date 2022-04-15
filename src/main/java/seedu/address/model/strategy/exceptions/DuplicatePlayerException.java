package seedu.address.model.strategy.exceptions;

/**
 * Indicates that the operation will result in duplicate Players
 */
public class DuplicatePlayerException extends RuntimeException {
    public DuplicatePlayerException() {
        super("Operation would result in duplicate persons");
    }
}
