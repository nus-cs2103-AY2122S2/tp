package seedu.address.model.interview;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.Date;
import seedu.address.model.Time;
import seedu.address.model.person.Person;

/**
 * Represents a Interview in the interview list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Interview {

    // Identity fields
    private final Person person;

    // Data fields
    private final Date date;
    private final Time time;

    /**
     * Every field must be present and not null.
     */
    public Interview(Person person, Date date, Time time) {
        requireAllNonNull(person, date, time);
        this.person = person;
        this.date = date;
        this.time = time;
    }

    public Person getPerson() {
        return person;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    /**
     * Returns true if both interviews have the same person or same date and time.
     * This defines a weaker notion of equality between two interviews.
     */
    public boolean isSameInterview(Interview otherInterview) {
        return otherInterview != null
                && (otherInterview.getDate().equals(getDate())
                && otherInterview.getTime().equals(getTime()));
    }

    /**
     * Returns true if both interviews have the same identity and data fields.
     * This defines a stronger notion of equality between two interviews.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Interview)) {
            return false;
        }

        Interview otherInterview = (Interview) other;
        return otherInterview.getPerson().isSamePerson(getPerson())
                && otherInterview.getDate().equals(getDate())
                && otherInterview.getTime().equals(getTime());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(person, date, time);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getPerson())
                .append("; Date: ")
                .append(getDate())
                .append("; Time: ")
                .append(getTime());
        return builder.toString();
    }

}

