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
     * Returns true if both lessons are the same instance.
     */
    @Override
    public boolean equals(Object other) {
        return other == this;
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
