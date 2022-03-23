package seedu.address.model.event;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.Objects;

import seedu.address.model.person.Name;

/**
 * Represents an Event in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Event {

    // Identity fields
    private final EventName name;
    private final Information info;
    private final List<Name> participants;
    private final DateTime dateTime;

    /**
     * Every field must be present and not null.
     */
    public Event(EventName name, Information info, List<Name> participants, DateTime dateTime) {
        requireAllNonNull(name, info, participants, dateTime);
        this.name = name;
        this.info = info;
        this.participants = participants;
        this.dateTime = dateTime;
    }

    public EventName getEventName() {
        return name;
    }

    public Information getEventInfo() {
        return info;
    }

    public List<Name> getParticipants() {
        return participants;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    /**
     * Returns true if both events have the same identity fields.
     */
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Event)) {
            return false;
        }

        Event otherEvent = (Event) other;
        return otherEvent.getEventName().equals(getEventName())
                && otherEvent.getEventInfo().equals(getEventInfo())
                && otherEvent.getParticipants().equals(getParticipants())
                && otherEvent.getDateTime().equals(getDateTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, info, participants, dateTime);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Event Name: ")
                .append(getEventName())
                .append("\nDetails: ")
                .append(getEventInfo())
                .append("\nDate & Time: ")
                .append(getDateTime())
                .append("\nParticipants: ")
                .append(getParticipants());
        return builder.toString();
    }
}
