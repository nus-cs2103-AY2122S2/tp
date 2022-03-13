package seedu.address.model.lesson;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import seedu.address.model.student.Student;

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
    private final List<Student> assignedStudents;
    private final DateTimeSlot dateTimeSlot;

    /**
     * Every field must be present and not null.
     */
    protected Lesson(LessonName name, Subject subject, LessonAddress address, DateTimeSlot dateTimeSlot) {
        requireAllNonNull(name, subject, address);
        this.name = name;
        this.subject = subject;
        this.address = address;
        this.dateTimeSlot = dateTimeSlot;
        this.assignedStudents = new ArrayList<>();
    }

    /**
     * Every field must be present and not null.
     */
    protected Lesson(LessonName name, Subject subject, LessonAddress address,
                     DateTimeSlot dateTimeSlot, List<Student> assignedStudents) {
        requireAllNonNull(name, subject, address);
        this.name = name;
        this.subject = subject;
        this.address = address;
        this.dateTimeSlot = dateTimeSlot;
        this.assignedStudents = assignedStudents;
    }

    /**
     * Creates a new instance of a non-recurring lesson.
     * @param name lesson name
     * @param subject what subject would be taught during the lesson
     * @param startDateTime date and starting time of the lesson
     * @param hours how long the lesson would last
     *
     * TODO: remove this constructor
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
     * @param minutes how long the lesson would last
     *
     * TODO: remove this constructor
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

    /**
     * Creates a new instance of a non-recurring lesson.
     * @param name lesson name
     * @param subject what subject would be taught during the lesson
     * @param address where the lesson would be conducted
     * @param dateOfLesson date of the lesson
     * @param startTime starting time of the lesson
     * @param hours how long the lesson would last
     * @param minutes how long the lesson would last
     */
    public static TemporaryLesson makeTemporaryLesson(String name, String subject, String address,
                                                      LocalDate dateOfLesson, String startTime,
                                                      int hours, int minutes) {
        LessonName lessonName = new LessonName(name);
        Subject lessonSubject = new Subject(subject);
        DateTimeSlot lessonDateTimeSlot = new DateTimeSlot(dateOfLesson, startTime, hours, minutes);
        LessonAddress lessonAddress = new LessonAddress(address);

        return new TemporaryLesson(
                lessonName,
                lessonSubject,
                lessonAddress,
                lessonDateTimeSlot
        );
    }

    /**
     * Creates a new instance of a non-recurring lesson.
     * @param name lesson name
     * @param subject what subject would be taught during the lesson
     * @param address where the lesson would be conducted
     * @param dateOfLesson date of the lesson
     * @param startTime starting time of the lesson
     * @param hours how long the lesson would last
     */
    public static TemporaryLesson makeTemporaryLesson(String name, String subject, String address,
                                                      LocalDate dateOfLesson, String startTime,
                                                      int hours) {
        return Lesson.makeTemporaryLesson(name, subject, address, dateOfLesson, startTime, hours, 0);
    }

    /**
     * Creates a new instance of a non-recurring lesson.
     * @param name lesson name
     * @param subject what subject would be taught during the lesson
     * @param address where the lesson would be conducted
     * @param dateTimeSlot an object encapsulating a lesson's date, starting time and duration.
     */
    public static TemporaryLesson makeTemporaryLesson(LessonName name, Subject subject, LessonAddress address,
                                                      DateTimeSlot dateTimeSlot, List<Student> assignedStudents) {

        return new TemporaryLesson(
                name,
                subject,
                address,
                dateTimeSlot,
                assignedStudents
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

    public List<Student> getAssignedStudents() {
        return assignedStudents;
    }

    /**
     * Returns the date and time that the lesson starts and ends.
     */
    public DateTimeSlot getDateTimeSlot() {
        return dateTimeSlot;
    }

    /**
     * Returns true if both lessons have overlapping timeslots.
     */
    public abstract boolean isConflictingWithLesson(Lesson otherLesson);

    /**
     * Adds a given Student to the list of students assigned to this lesson.
     */
    public abstract void addStudent(Student student);
}
