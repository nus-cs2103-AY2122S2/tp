package seedu.address.model.tutorial.exceptions;

public class DuplicateTutorialException extends RuntimeException {
    public DuplicateTutorialException() {
        super("Operation would result in duplicate tutorials");
    }
}
