package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Client's upcoming meeting.
 * Guarantees: details are present field values are validated, immutable, else the isMeetingScheduled will be false.
 */
public class ScheduledMeeting {

    private final MeetingDate date;
    private final MeetingTime time;
    private final boolean isMeetingScheduled;

    /**
     * Constructs an upcoming meeting with the specified date and time.
     * @param date the date set for the meeting.
     * @param time the time set for the meeting.
     */
    public ScheduledMeeting(MeetingDate date, MeetingTime time) {
        requireAllNonNull(date, time);
        this.date = date;
        this.time = time;
        this.isMeetingScheduled = true;
    }

    /**
     * Constructs a temporary empty upcoming meeting for each person if there
     * is no upcoming meeting.
     */
    public ScheduledMeeting() {
        this.date = null;
        this.time = null;
        this.isMeetingScheduled = false;
    }

    public MeetingDate getDate() {
        return date;
    }

    public MeetingTime getTime() {
        return time;
    }

    public boolean hasMeetingScheduled() {
        return isMeetingScheduled;
    }

    /**
     * Returns true if this scheduled meeting has the exact same date and time as the other meeting.
     * @param otherMeeting The other meeting to check against.
     * @return true if this meeting has the same date and time as the other meeting.
     */
    public boolean hasSameMeeting(ScheduledMeeting otherMeeting) {
        if (otherMeeting == this) {
            return true;
        }

        if (otherMeeting != null
                && otherMeeting.hasMeetingScheduled()
                && this.hasMeetingScheduled()) {
            return otherMeeting.getDate().equals(getDate())
                    && otherMeeting.getTime().equals(getTime());
        } else {
            return false;
        }
    }

    /**
     * Method to compare SheduledMeeting with other ScheduledMeeting.
     * Returns 0 if meeting data and time are equal, -1 if this ScheduledMeeting is before and 1 if it is after.
     *
     * @param otherMeeting Another ScheduledMeeting to compare to
     * @return Integer indicating if PrevDateMet is equal, before or after otherMeeting
     */
    public int compare(ScheduledMeeting otherMeeting) {
        if (this.equals(otherMeeting)) {
            return 0;
        } else {
            int dateCompare = this.getDate().compare(otherMeeting.getDate());
            if (dateCompare != 0) {
                return dateCompare;
            } else {
                return this.getTime().compare(otherMeeting.getTime());
            }
        }

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
        // Compare both date and time only if both has scheduled meetings
        if (otherMeeting.hasMeetingScheduled() && this.hasMeetingScheduled()) {
            return otherMeeting.getDate().equals(getDate())
                    && otherMeeting.getTime().equals(getTime());
        } else if (!otherMeeting.hasMeetingScheduled() && !this.hasMeetingScheduled()) {
            return true; // Return true if both have no meeting scheduled
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, time, isMeetingScheduled);
    }

    @Override
    public String toString() {
        if (date == null || time == null) {
            return "No meeting scheduled";
        }
        final StringBuilder builder = new StringBuilder();
        builder.append(getDate())
                .append(" ")
                .append(getTime().toString());

        return builder.toString();
    }

}
