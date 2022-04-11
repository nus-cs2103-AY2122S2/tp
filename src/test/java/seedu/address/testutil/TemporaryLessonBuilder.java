package seedu.address.testutil;

import java.time.LocalDateTime;

import seedu.address.model.lesson.DateTimeSlot;
import seedu.address.model.lesson.EnrolledStudents;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.LessonAddress;
import seedu.address.model.lesson.LessonName;
import seedu.address.model.lesson.Subject;

/**
 * A utility class to help with building Lesson objects.
 */
public class TemporaryLessonBuilder {
    public static final String DEFAULT_NAME = "Make up lesson for George";
    public static final String DEFAULT_SUBJECT = "Biology";
    public static final String DEFAULT_ADDRESS = "Blk 47 Tampines Street 20, #17-35";
    public static final LocalDateTime DEFAULT_START_DATETIME =
            LocalDateTime.of(2022, 12, 1, 18, 0);;
    public static final Integer DEFAULT_DURATION_HOURS = 2;
    public static final Integer DEFAULT_DURATION_MINUTES = 30;

    private LessonName name;
    private Subject subject;
    private LessonAddress address;
    private DateTimeSlot dateTimeSlot;
    private EnrolledStudents enrolledStudents;

    /**
     * Creates a {@code TemporaryLessonBuilder} with the default details.
     */
    public TemporaryLessonBuilder() {
        name = new LessonName(DEFAULT_NAME);
        subject = new Subject(DEFAULT_SUBJECT);
        address = new LessonAddress(DEFAULT_ADDRESS);
        dateTimeSlot = new DateTimeSlot(DEFAULT_START_DATETIME, DEFAULT_DURATION_HOURS, DEFAULT_DURATION_MINUTES);
        enrolledStudents = new EnrolledStudents();
    }

    /**
     * Initializes the TemporaryLessonBuilder with the data of {@code lessonToCopy}.
     */
    public TemporaryLessonBuilder(Lesson lessonToCopy) {
        name = lessonToCopy.getName();
        subject = lessonToCopy.getSubject();
        address = lessonToCopy.getLessonAddress();
        dateTimeSlot = lessonToCopy.getDateTimeSlot();
        enrolledStudents = lessonToCopy.getEnrolledStudents();
    }

    /**
     * Sets the {@code Name} of the {@code Lesson} that we are building.
     */
    public TemporaryLessonBuilder withName(String name) {
        this.name = isNull(name)
                ? null
                : new LessonName(name);

        return this;
    }

    /**
     * Sets the {@code Subject} of the {@code Lesson} that we are building.
     */
    public TemporaryLessonBuilder withSubject(String subject) {
        this.subject = isNull(subject)
                ? null
                : new Subject(subject);

        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Lesson} that we are building.
     */
    public TemporaryLessonBuilder withAddress(String address) {
        this.address = isNull(address)
                ? null
                : new LessonAddress(address);

        return this;
    }

    /**
     * Sets the {@code DateTimeSlot} of the {@code Lesson} that we are building.
     */
    public TemporaryLessonBuilder withDateTimeSlot(DateTimeSlot dateTimeSlot) {
        this.dateTimeSlot = dateTimeSlot;
        return this;
    }

    /**
     * Sets the {@code DateTimeSlot} of the {@code Lesson} that we are building.
     */
    public TemporaryLessonBuilder withDateTimeSlot(LocalDateTime lessonDateTime,
                                                   Integer durationHours, Integer durationMinutes) {
        this.dateTimeSlot = new DateTimeSlot(lessonDateTime, durationHours, durationMinutes);
        return this;
    }

    /**
     * Sets the {@code DateTimeSlot} of the {@code Lesson} that we are building.
     */
    public TemporaryLessonBuilder withDateTimeSlot(Integer year, Integer month, Integer dayOfMonth,
                                                   Integer hour, Integer minute,
                                                   Integer durationHours, Integer durationMinutes) {
        this.dateTimeSlot = new DateTimeSlot(
                LocalDateTime.of(year, month, dayOfMonth, hour, minute),
                durationHours, durationMinutes
        );
        return this;
    }

    /**
     * Sets the duration of the {@code DateTimeSlot} of the {@code Lesson} that we are building
     */
    public TemporaryLessonBuilder withDuration(Integer hours, Integer minutes) {
        this.dateTimeSlot = new DateTimeSlot(this.dateTimeSlot.getDateOfLesson(), hours, minutes);
        return this;
    }

    /**
     * Sets the {@code EnrolledStudents} of the {@code Lesson} that we are building.
     */
    public TemporaryLessonBuilder withEnrolledStudents(EnrolledStudents enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
        return this;
    }

    public Lesson build() {
        return Lesson.makeTemporaryLesson(name, subject, address, dateTimeSlot);
    }

    private static boolean isNull(Object toTest) {
        return toTest == null;
    }
}
