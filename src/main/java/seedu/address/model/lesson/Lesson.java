package seedu.address.model.lesson;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import seedu.address.model.person.Person;

/**
 * Represents a Lesson in the Lesson book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public abstract class Lesson {

    // Identity fields
    private final LessonName name;
    private final Subject subject;
    private final LessonAddress address;

    // Data fields
    private final List<Person> assignedStudents;

    /**
     * Every field must be present and not null.
     */
    protected Lesson(LessonName name, Subject subject, LessonAddress address) {
        requireAllNonNull(name, subject);
        this.name = name;
        this.subject = subject;
        this.address = address;
        this.assignedStudents = new ArrayList<>();
    }

    /**
     * Creates a new instance of a non-recurring lesson.
     * @param name lesson name
     * @param subject what subject would be taught during the lesson
     * @param startDateTime date and starting time of the lesson
     * @param hours how long the lesson would last
     */
    public static TemporaryLesson makeTemporaryLesson(String name, String subject, String address,
                                                      LocalDateTime startDateTime, int hours) {
        return Lesson.makeTemporaryLesson(name, subject, address, startDateTime, hours, 0);
    }

    /**
     * Creates a new instance of a non-recurring lesson.
     * @param name lesson name
     * @param subject what subject would be taught during the lesson
     * @param startDateTime date and starting time of the lesson
     * @param hours how long the lesson would last
     */
    public static TemporaryLesson makeTemporaryLesson(String name, String subject, String address,
                                                      LocalDateTime startDateTime, int hours, int minutes) {
        LessonName lessonName = new LessonName(name);
        Subject lessonSubject = new Subject(subject);
        DateTimeSlot lessonDateTimeSlot = new DateTimeSlot(startDateTime, hours, minutes);
        LessonAddress lessonAddress = new LessonAddress(address);

        return new TemporaryLesson(
                lessonName,
                lessonSubject,
                lessonAddress,
                lessonDateTimeSlot
        );
    }

    public LessonName getName() {
        return name;
    }

    public Subject getSubject() {
        return subject;
    }

    public LessonAddress getLessonAddress() {
        return address;
    }

    public List<Person> getAssignedStudents() {
        return assignedStudents;
    }

    /**
     * Returns true if both lessons have overlapping timeslots.
     */
    public abstract boolean isConflictingWithLesson(Lesson otherLesson);

    /**
     * Returns the date and time that the lesson starts and ends.
     */
    public abstract DateTimeSlot getTimeSlot();

    /**
     * Adds a given Person to the list of students assigned to this lesson.
     */
    public abstract void addStudent(Person person);
}
