package seedu.address.model.tasks;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.Date;
import seedu.address.model.Time;

public class Task {

    private final Header header;
    private final Date date;
    private final Time time;
    private final Information information;
    /**
     * Every field must be present and not null.
     */
    public Task(Header header, Date date, Time time, Information information) {
        requireAllNonNull(header, date, time, information);
        this.header = header;
        this.date = date;
        this.time = time;
        this.information = information;
    }

    public Header getHeader() {
        return header;
    }

    public Information getInformation() {
        return information;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    /**
     * Returns true if both Tasks have the same Information.
     * This defines a weaker notion of equality between two Tasks.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && (otherTask.getHeader().equals(getHeader()))
                && (otherTask.getDate().equals(getDate())
                && otherTask.getTime().equals(getTime()));
    }

    /**
     * Returns true if both interviews have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        return (otherTask.getHeader().equals(getHeader()))
                && (otherTask.getDate().equals(getDate())
                && otherTask.getTime().equals(getTime()));
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getHeader())
                .append("; Date: ")
                .append(getDate())
                .append("; Time: ")
                .append(getTime())
                .append("; Information: ")
                .append(getInformation());

        return builder.toString();
    }
}