package seedu.address.model.lesson;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;

/**
 * Represents a Lesson in the Lesson book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public abstract class Lesson {

    // Identity fields
    private final Name name;
    private final Subject subject;

    // Data fields
    // no data fields in abstract class

    /**
     * Every field must be present and not null.
     */
    protected Lesson(Name name, Subject subject) {
        requireAllNonNull(name, subject);
        this.name = name;
        this.subject = subject;
    }

    /**
     * Creates a new instance of a non-recurring lesson.
     * @param name lesson name
     * @param subject what subject would be taught during the lesson
     * @param startDateTime date and starting time of the lesson
     * @param hours how long the lesson would last
     */
    public static TemporaryLesson makeTemporaryLesson(String name, String subject, LocalDateTime startDateTime, int hours) {
        return Lesson.makeTemporaryLesson(name, subject, startDateTime, hours, 0);
    }

    /**
     * Creates a new instance of a non-recurring lesson.
     * @param name lesson name
     * @param subject what subject would be taught during the lesson
     * @param startDateTime date and starting time of the lesson
     * @param hours how long the lesson would last
     */
    public static TemporaryLesson makeTemporaryLesson(String name, String subject, LocalDateTime startDateTime,
                                                      int hours, int minutes) {
        Name lessonName = new Name(name);
        Subject lessonSubject = new Subject(subject);
        DateTimeSlot lessonDateTimeSlot = new DateTimeSlot(startDateTime, hours, minutes);

        return new TemporaryLesson(
                lessonName,
                lessonSubject,
                lessonDateTimeSlot
        );
    }

    public Name getName() {
        return name;
    }

    public Subject getSubject() {
        return subject;
    }

    /**
     * Returns true if both lessons have overlapping timeslots.
     */
    public abstract boolean isConflictingWithLesson(Lesson otherLesson);

    /**
     * Returns the date and time that the lesson starts and ends.
     */
    public abstract DateTimeSlot getTimeSlot();
}
