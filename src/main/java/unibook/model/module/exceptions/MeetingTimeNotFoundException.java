package unibook.model.module.exceptions;

/**
 * Signals that a given meeting date is not found in the list of meeting dates of a group.
 */
public class MeetingTimeNotFoundException extends RuntimeException {
    public MeetingTimeNotFoundException() {
        super("Meeting Time not found");
    }
}
