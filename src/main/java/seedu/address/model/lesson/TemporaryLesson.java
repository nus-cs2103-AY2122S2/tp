package seedu.address.model.lesson;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class TemporaryLesson extends Lesson {

    // Data fields
    private final TimeSlot timeSlot;

    /**
     * Every field must be present and not null.
     */
    protected TemporaryLesson(Name name, Subject subject, TimeSlot timeSlot) {
        super(name, subject);
        requireAllNonNull(timeSlot);
        this.timeSlot = timeSlot;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    /**
     * Returns true if both lessons clash.
     */
    public boolean isConflictingWithLesson(Lesson otherLesson) {
        if (otherLesson == this) {
            return true;
        }

        return timeSlot.isClashingWith(otherLesson.getTimeSlot());
    }

    /**
     * Returns true if both lessons have the same name, subject and timeslot.
     * @param otherLesson
     * @return
     */
    public boolean isSameLesson(Lesson otherLesson) {
        if (!otherLesson.getName().equals(this.getName())) {
            return false;
        }

        if (!otherLesson.getSubject().equals(this.getSubject())) {
            return false;
        }

        if (!otherLesson.getTimeSlot().equals(timeSlot)) {
            return false;
        }

        return true;
    }

    /**
     * Returns true if both lessons have the same identity and data fields.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TemporaryLesson)) {
            return false;
        }

        TemporaryLesson otherLesson = (TemporaryLesson) other;

        return otherLesson.getName().equals(getName())
                && otherLesson.getSubject().equals(getSubject())
                && otherLesson.getTimeSlot().equals(getTimeSlot());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(this.getName(), this.getSubject(), timeSlot);
    }

    @Override
    public String toString() {
        // TODO: come up with string representation for a lesson
        return this.getName().toString();
    }
}
