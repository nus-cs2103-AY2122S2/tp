package seedu.address.model.lesson;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a temporary lesson in the student book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class TemporaryLesson extends Lesson {
    private static final String TEMPORARY_LESSON_DESCRIPTOR = "============== [TEMPORARY LESSON] ==============";

    /**
     * Every field must be present and not null.
     */
    protected TemporaryLesson(LessonName name, Subject subject, LessonAddress address,
                              DateTimeSlot dateTimeSlot, EnrolledStudents enrolledStudents) {
        super(name, subject, address, dateTimeSlot, enrolledStudents);
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
    @Override
    public boolean isConflictingWithLesson(Lesson otherLesson) {
        if (otherLesson == this) {
            return true;
        }
        if (otherLesson instanceof RecurringLesson) {
            return otherLesson.isConflictingWithLesson(this);
        } else {
            return getDateTimeSlot().isConflictingWith(otherLesson.getDateTimeSlot());
        }
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
        StringBuilder lessonString = new StringBuilder();

        lessonString.append(TEMPORARY_LESSON_DESCRIPTOR)
                .append(System.getProperty("line.separator"))
                .append(super.toString());

        return lessonString.toString();
    }
}
