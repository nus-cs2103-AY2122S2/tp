package seedu.address.model.entry;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents an Event in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Event extends Entry {

    //Identity fields
    private final String company;

    // Data fields
    private final Date date;
    private final Time time;
    private final Location location;

    /**
     * Every field must be present and not null.
     */
    public Event(Name name, String company, Date date, Time time, Location location, Set<Tag> tags) {
        super(name, tags);
        requireAllNonNull(company, date, time, location);
        this.company = company;
        this.date = date;
        this.time = time;
        this.location = location;
    }

    public String getCompany() {
        return this.company;
    }

    public Date getDate() {
        return this.date;
    }

    public Time getTime() {
        return this.time;
    }

    public Location getLocation() {
        return this.location;
    }

    /**
     * Returns true if both entries are an event and have the same name, company, date, and time.
     * This defines a weaker notion of equality between two events.
     */
    @Override
    public boolean isSameEntry(Entry otherEntry) {
        if (otherEntry == this) {
            return true;
        }

        if (!(otherEntry instanceof Event)) {
            return false;
        }

        Event otherEvent = (Event) otherEntry;
        return otherEvent.getName().equals(getName())
                && otherEvent.getCompany().equals(getCompany())
                && otherEvent.getDate().equals(getDate())
                && otherEvent.getTime().equals(getTime());
    }

    /**
     * Returns true if both events have the same identity and data fields.
     * This defines a stronger notion of equality between two events.
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
                && otherEvent.getCompany().equals(getCompany())
                && otherEvent.getDate().equals(getDate())
                && otherEvent.getTime().equals(getTime())
                && otherEvent.getLocation().equals((getLocation()))
                && otherEvent.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // Use all Event's attributes to get a unique hashCode
        return Objects.hash(getName(), company, date, time, location, getTags());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Company: ")
                .append(getCompany())
                .append("; Date: ")
                .append(getDate())
                .append("; Time: ")
                .append(getTime())
                .append("; Location: ")
                .append(getLocation());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        return builder.toString();
    }
}
