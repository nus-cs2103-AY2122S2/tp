package seedu.address.model.entry;

import seedu.address.model.tag.Tag;


import java.util.Objects;
import java.util.Set;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class Event extends Entry {

    //Identity fields
    private final String company;

    // Data fields
    private final Date date;
    private final Time time;
    private final Platform platform;

    public Event(Name name, String company, Date date, Time time, Platform platform, Set<Tag> tags) {
        super(name, tags);
        requireAllNonNull(company, date, time, platform);
        this.company = company;
        this.date = date;
        this.time = time;
        this.platform = platform;
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

    public Platform getPlatform() {
        return this.platform;
    }

    @Override
    public boolean isSameEntry(Entry otherEntry) {
        if(otherEntry == this) {
            return true;
        }

        if(!(otherEntry instanceof Event)) {
            return false;
        }

        Event otherEvent = (Event) otherEntry;
        return otherEvent.getName().equals(getName())
                && otherEvent.getCompany().equals(getCompany());
    }

    @Override
    public boolean equals(Object other) {
        if(other == this) {
            return true;
        }

        if(!(other instanceof Event)) {
            return false;
        }

        Event otherEvent = (Event) other;
        return otherEvent.getName().equals(getName())
                && otherEvent.getCompany().equals(getCompany())
                && otherEvent.getDate().equals(getDate())
                && otherEvent.getTime().equals(getTime())
                && otherEvent.getPlatform().equals(getPlatform())
                && otherEvent.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // Use all Event's attributes to get a unique hashCode
        return Objects.hash(getName(), company, date, time, platform, getTags());
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
                .append("; Platform: ")
                .append(getPlatform());

        Set<Tag> tags = getTags();
        if(!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        return builder.toString();
    }
}
