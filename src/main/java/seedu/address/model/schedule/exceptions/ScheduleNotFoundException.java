package seedu.address.model.schedule.exceptions;

/**
 * Represents an error when the target schedule could not be found
 */
public class ScheduleNotFoundException extends RuntimeException {
    public ScheduleNotFoundException() {
        super("Could not find the schedule");
    }
}
