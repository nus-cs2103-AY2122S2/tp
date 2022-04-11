package seedu.address.model.schedule.exceptions;

/**
 * Represents an error when the same schedule is created more than once
 */
public class DuplicateScheduleException extends RuntimeException {
    public DuplicateScheduleException() {
        super("Operation would result in duplicate schedules");
    }
}
