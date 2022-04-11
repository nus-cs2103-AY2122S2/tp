package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.module.Module;
import seedu.address.model.tag.Tag;

/**
 * Represents a Meeting in the meeting list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Meeting implements Comparable<Meeting> {

    // Identity fields
    private final MeetingName name;
    private final MeetingUrl url;
    private final MeetingDateTime startDateTime;
    private final Module module;
    private final MeetingDuration duration;

    // Data fields
    private final IsRecurring isRecurring;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Constructs a {@code Meeting}. Every field must be present and non-null.
     *
     * @param name          The name of the meeting.
     * @param url           The URL link of the meeting.
     * @param startDateTime The start date and time of the meeting.
     * @param isRecurring   Whether the meeting is recurring.
     */
    public Meeting(MeetingName name, MeetingUrl url, MeetingDateTime startDateTime, MeetingDuration duration,
                   Module module, IsRecurring isRecurring, Set<Tag> tags) {
        requireAllNonNull(name, url, startDateTime, isRecurring, tags);
        this.name = name;
        this.url = url;
        this.startDateTime = startDateTime;
        this.duration = duration;
        this.module = module;
        this.isRecurring = isRecurring;
        this.tags.addAll(tags);
    }

    public MeetingName getName() {
        return name;
    }

    public MeetingUrl getUrl() {
        return url;
    }

    /**
     * Returns the start date and time of the meeting.
     *
     * @return The start date and time of the meeting if the meeting isn't recurring. Otherwise, the
     * start date and time of the next upcoming recurrence is returned.
     */
    public MeetingDateTime getStartDateTime() {
        if (!isRecurring.isRecurring) {
            return startDateTime;
        }
        return new MeetingDateTime(getNextRecurrence());
    }

    /**
     * Returns the end date and time of the meeting.
     *
     * @return The end date and time of the meeting if the meeting isn't recurring. Otherwise, the
     * end date and time of the next upcoming recurrence is returned.
     */
    public MeetingDateTime getEndDateTime() {
        LocalDateTime endDateTime;
        if (isRecurring.isRecurring) {
            endDateTime = duration.getEndDateTime(getNextRecurrence());
        } else {
            endDateTime = duration.getEndDateTime(startDateTime.datetime);
        }
        return new MeetingDateTime(endDateTime);
    }

    /**
     * Computes the next recurrence of the meeting relative to the current date and time.
     * If the current meeting is over, the next recurrence would be seven days from the
     * start date and time of the meeting. Otherwise, the meeting isn't over and its
     * current start date and time would be the next upcoming recurrence.
     *
     * @return The next recurrence of the meeting.
     */
    private LocalDateTime getNextRecurrence() {
        final LocalDateTime today = LocalDateTime.now();
        return getNextRecurrence(today);
    }

    /*
     * This is the protected version of the private method getNextOccurence that accepts a
     * LocalDateTime, logic abstracted out for testing purposes.
     */
    protected LocalDateTime getNextRecurrence(LocalDateTime today) {
        final LocalDateTime endDateTime = duration.getEndDateTime(startDateTime.datetime);

        final long weeksElapsed = ChronoUnit.WEEKS.between(endDateTime, today);
        if (weeksElapsed < 0) {
            return startDateTime.datetime;
        }

        final LocalDateTime nextRecurrentStartDateTime = startDateTime.datetime.plusWeeks(weeksElapsed);
        final LocalDateTime nextRecurrentEndDateTime = endDateTime.plusWeeks(weeksElapsed);
        if (today.isBefore(nextRecurrentEndDateTime) || today.isEqual(nextRecurrentEndDateTime)) {
            return nextRecurrentStartDateTime;
        }

        return nextRecurrentStartDateTime.plusWeeks(1);
    }

    public MeetingDuration getDuration() {
        return duration;
    }

    public Module getModule() {
        return module;
    }

    public IsRecurring getIsRecurring() {
        return isRecurring;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }


    /**
     * Returns true if both meetings have the same identity and data fields.
     * This defines a stronger notion of equality between two meetings.
     *
     * @param other The other meeting to compare against.
     * @return      True if both meetings are the same.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Meeting)) {
            return false;
        }

        final Meeting otherMeeting = (Meeting) other;
        return otherMeeting.name.equals(this.name)
                && otherMeeting.url.equals(this.url)
                && otherMeeting.getStartDateTime().equals(this.getStartDateTime())
                && otherMeeting.duration.equals(this.duration)
                && otherMeeting.module.equals(this.module)
                && otherMeeting.isRecurring.equals(this.isRecurring)
                && otherMeeting.tags.equals(this.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, url, startDateTime, duration, module, isRecurring, tags);
    }

    // Not updating to show the duration for now
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(module)
                .append(" ")
                .append(name)
                .append("; Meeting URL: ")
                .append(url)
                .append("; Date and time: ")
                .append(getStartDateTime())
                .append("; Is recurring: ")
                .append(isRecurring);

        final Set<Tag> tags = this.tags;
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

    @Override
    public int compareTo(Meeting other) {
        requireNonNull(other);

        MeetingDateTime startDateTime1 = getStartDateTime();
        MeetingDateTime startDateTime2 = other.getStartDateTime();
        int result = startDateTime1.compareTo(startDateTime2);

        if (result != 0) {
            return result;
        }

        MeetingDateTime endDateTime1 = getEndDateTime();
        MeetingDateTime endDateTime2 = other.getEndDateTime();

        return endDateTime1.compareTo(endDateTime2);
    }
}
