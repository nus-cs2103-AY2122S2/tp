package seedu.address.model.task.exceptions;

/**
 * Indicates that the operation will result in duplicate Tasks (Tasks are consider duplicates if they
 * have the same identity).
 */
public class DuplicateTaskException extends RuntimeException {
    public DuplicateTaskException() {
        super("Operation would result in duplicate persons");
    }
}

