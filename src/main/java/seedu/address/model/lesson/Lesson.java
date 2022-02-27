package seedu.address.model.lesson;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Lesson {

    // Identity fields
    private final Name name;
    private final Subject subject;

    // Data fields
    private final TimeSlot timeSlot;

    /**
     * Every field must be present and not null.
     */
    public Lesson(Name name, Subject subject, TimeSlot timeSlot) {
        requireAllNonNull(name, subject, timeSlot);
        this.name = name;
        this.subject = subject;
        this.timeSlot = timeSlot;
    }

    public Name getName() {
        return name;
    }

    public Subject getSubject() {
        return subject;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    /**
     * Returns true if both lessons clash.
     */
    public boolean isClashingWithLesson(Lesson otherLesson) {
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
        if (!otherLesson.getName().equals(name)) {
            return false;
        }

        if (!otherLesson.getSubject().equals(subject)) {
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

        if (!(other instanceof Lesson)) {
            return false;
        }

        Lesson otherLesson = (Lesson) other;

        return otherLesson.getName().equals(getName())
                && otherLesson.getSubject().equals(getSubject())
                && otherLesson.getTimeSlot().equals(getTimeSlot());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, subject, timeSlot);
    }

    @Override
    public String toString() {
        // TODO: come up with string representation for a lesson
        return this.name.toString();
    }

}
