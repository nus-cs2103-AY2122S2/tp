package seedu.trackermon.model.show.exceptions;

/**
 * Represents a runtime exception causes by having duplicated shows.
 */
public class DuplicatedShowException extends RuntimeException {

    /**
     * Constructs a new {@code DuplicatedShowException} with the specified given error message.
     */
    public DuplicatedShowException() {
        super("Operation would result in duplicate shows");
    }
}
