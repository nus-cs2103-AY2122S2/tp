package seedu.address.model.meeting.exceptions;

/**
 * Signals that the operation will result in overlapping Meetings (Meetings are considered overlapping if the start
 * time of the later meeting is before the end time of the earlier meeting).
 */
public class OverlappingMeetingsException extends RuntimeException {
    public OverlappingMeetingsException() {
        super("Operation would result in overlapping meetings");
    }
}
