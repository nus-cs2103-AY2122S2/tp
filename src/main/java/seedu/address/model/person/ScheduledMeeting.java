package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Client's upcoming meeting.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class ScheduledMeeting {

    private final MeetingDate date;
    private final MeetingTime time;

    /**
     * Constructs an upcoming meeting with the specified date and time.
     * @param date the date set for the meeting.
     * @param time the time set for the meeting.
     */
    public ScheduledMeeting(MeetingDate date, MeetingTime time) {
        requireAllNonNull(date, time);
        this.date = date;
        this.time = time;
    }

    public MeetingDate getDate() {
        return date;
    }

    public MeetingTime getTime() {
        return time;
    }

    /**
     * Returns true if this scheduled meeting has the exact same date and time as the other meeting.
     * @param otherMeeting The other meeting to check against.
     * @return true if this meeting has the same date and time as the other meeting.
     */
    public boolean isSameMeeting(ScheduledMeeting otherMeeting) {
        if (otherMeeting == this) {
            return true;
        }

        return otherMeeting != null
                && otherMeeting.equals(this);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ScheduledMeeting)) {
            return false;
        }

        ScheduledMeeting otherMeeting = (ScheduledMeeting) other;
        return otherMeeting.getDate().equals(getDate())
                && otherMeeting.getTime().equals(getTime());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(date, time);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDate())
                .append(" at: ")
                .append(getTime());

        return builder.toString();
    }

}
