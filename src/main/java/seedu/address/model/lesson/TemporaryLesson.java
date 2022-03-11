package seedu.address.model.lesson;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a temporary lesson in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class TemporaryLesson extends Lesson {
    // Data fields
    private final DateTimeSlot dateTimeSlot;

    /**
     * Every field must be present and not null.
     */
    protected TemporaryLesson(LessonName name, Subject subject, LessonAddress address, DateTimeSlot dateTimeSlot) {
        super(name, subject, address);
        requireAllNonNull(dateTimeSlot);
        this.dateTimeSlot = dateTimeSlot;
    }

    public DateTimeSlot getTimeSlot() {
        return dateTimeSlot;
    }

    /**
     * Returns true if both lessons clash.
     */
    public boolean isConflictingWithLesson(Lesson otherLesson) {
        if (otherLesson == this) {
            return true;
        }

        return dateTimeSlot.isConflictingWith(otherLesson.getTimeSlot());
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
                && otherLesson.getLessonAddress().equals(getLessonAddress())
                && otherLesson.getTimeSlot().equals(getTimeSlot());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(this.getName(), this.getSubject(), dateTimeSlot);
    }

    @Override
    public String toString() {
        // TODO: come up with string representation for a lesson
        return this.getName().toString();
    }
}
