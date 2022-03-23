package unibook.model.module.exceptions;

/**
 * Signals that an adding of a meeting time operation will result in duplicate meeting times.
 */
public class DuplicateMeetingTimeException extends RuntimeException {
    public DuplicateMeetingTimeException() {
        super("Meeting time already exists");
    }
}
