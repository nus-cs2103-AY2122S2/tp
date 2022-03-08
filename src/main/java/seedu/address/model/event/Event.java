package seedu.address.model.event;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Objects;

import seedu.address.model.person.Name;

public class Event {

    // Identity fields
    private final Name name;


    // Data fields
    private final LocalDateTime localDateTime;
    private final String description;

    /**
     * Constructor for event.
     * @param name name of event
     * @param description description of event
     * @param localDateTime date and time of event
     */
    public Event(Name name, String description, LocalDateTime localDateTime) {
        requireAllNonNull(name, localDateTime);
        this.name = name;
        this.localDateTime = localDateTime;
        this.description = description;
    }

    public Name getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Event)) {
            return false;
        }

        Event otherEvent = (Event) other;
        return otherEvent.getName().equals(getName())
                && otherEvent.getLocalDateTime().equals(getLocalDateTime());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, localDateTime);
    }
}
