package seedu.address.model.person.exceptions;

/**
 * Signals that the operation will result in Invalid task Index (Task index are considered as invalid if it is less
 * than 1 or larger than the number of tasks).
 */

public class InvalidTaskIndexException extends RuntimeException {
    public InvalidTaskIndexException() {
        super("Invalid Task Index Input!");
    }
}
