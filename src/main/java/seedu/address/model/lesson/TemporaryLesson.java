package seedu.address.model.lesson;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.Objects;

/**
 * Represents a temporary lesson in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class TemporaryLesson extends Lesson {

    /**
     * Every field must be present and not null.
     */
    protected TemporaryLesson(LessonName name, Subject subject, LessonAddress address,
                              DateTimeSlot dateTimeSlot, List<Student> assignedStudents) {
        super(name, subject, address, dateTimeSlot, assignedStudents);
        requireAllNonNull(dateTimeSlot);
    }

    /**
     * Every field must be present and not null.
     */
    protected TemporaryLesson(LessonName name, Subject subject, LessonAddress address,
                              DateTimeSlot dateTimeSlot) {
        super(name, subject, address, dateTimeSlot);
        requireAllNonNull(dateTimeSlot);
    }

    /**
     * Returns true if both lessons clash.
     */
    public boolean isConflictingWithLesson(Lesson otherLesson) {
        if (otherLesson == this) {
            return true;
        }

        return getDateTimeSlot().isConflictingWith(otherLesson.getDateTimeSlot());
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
                && otherLesson.getDateTimeSlot().equals(getDateTimeSlot());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(this.getName(), this.getSubject(), this.getDateTimeSlot());
    }

    @Override
    public String toString() {
        // TODO: come up with string representation for a lesson
        return this.getName().toString();
    }
}
