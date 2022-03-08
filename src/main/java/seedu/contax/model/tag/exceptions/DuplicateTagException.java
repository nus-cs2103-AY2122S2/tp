package seedu.contax.model.tag.exceptions;

public class DuplicateTagException extends RuntimeException {
    public DuplicateTagException() {
        super("Operation would result in duplicate tags.");
    }
}
