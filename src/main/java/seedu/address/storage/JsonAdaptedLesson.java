package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.lesson.DateTimeSlot;
import seedu.address.model.lesson.EnrolledStudents;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.LessonAddress;
import seedu.address.model.lesson.LessonName;
import seedu.address.model.lesson.RecurringLesson;
import seedu.address.model.lesson.Subject;
import seedu.address.model.lesson.TemporaryLesson;

/**
 * Jackson-friendly version of {@link TemporaryLesson}.
 */
class JsonAdaptedLesson {

    public static final String MISSING_FIELD_MESSAGE = "Lesson has some missing/invalid fields!";

    private final String lessonName;
    private final String subject;
    private final String lessonAddress;
    private final JsonAdaptedDateTimeSlot dateTimeSlot;
    private final Boolean isRecurring;
    private final List<JsonAdaptedStudent> assignedStudents = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedLesson} with the given lesson details.
     */
    @JsonCreator
    public JsonAdaptedLesson(@JsonProperty("lessonName") String lessonName,
                             @JsonProperty("subject") String subject,
                             @JsonProperty("address") String address,
                             @JsonProperty("dateTimeSlot") JsonAdaptedDateTimeSlot dateTimeSlot,
                             @JsonProperty("isRecurring") Boolean isRecurring,
                             @JsonProperty("assignedStudents") List<JsonAdaptedStudent> assignedStudents) {
        this.lessonName = lessonName;
        this.subject = subject;
        this.lessonAddress = address;
        this.dateTimeSlot = dateTimeSlot;
        this.isRecurring = isRecurring;
        if (assignedStudents != null) {
            this.assignedStudents.addAll(assignedStudents);
        }
    }

    /**
     * Converts a given {@code TemporaryLesson} into this class for Jackson use.
     */
    public JsonAdaptedLesson(Lesson source) {
        this.lessonName = source.getName().fullName;
        this.subject = source.getSubject().subjectName;
        this.lessonAddress = source.getLessonAddress().value;
        this.dateTimeSlot = new JsonAdaptedDateTimeSlot(source.getDateTimeSlot());
        this.isRecurring = source instanceof RecurringLesson;
        this.assignedStudents.addAll(source.getEnrolledStudents().getStudentsList().stream()
                .map(JsonAdaptedStudent::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted temporary lesson object into the model's {@code TemporaryLesson} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted temporary lesson.
     */
    public Lesson toModelType() throws IllegalValueException {
        checkFieldsArePresent(lessonName, subject, lessonAddress, dateTimeSlot, isRecurring, assignedStudents);
        checkFieldsAreValid(lessonName, subject, lessonAddress);

        LessonName modelLessonName = new LessonName(lessonName);
        Subject modelSubject = new Subject(subject);
        LessonAddress modelLessonAddress = new LessonAddress(lessonAddress);
        DateTimeSlot modelDateTimeSlot = dateTimeSlot.toModelType();
        EnrolledStudents modelAssignedStudents = getEnrolledStudents(assignedStudents);

        if (this.isRecurring) {
            return Lesson.makeRecurringLesson(
                    modelLessonName, modelSubject, modelLessonAddress,
                    modelDateTimeSlot, modelAssignedStudents);
        } else {
            return Lesson.makeTemporaryLesson(
                    modelLessonName, modelSubject, modelLessonAddress,
                    modelDateTimeSlot, modelAssignedStudents);
        }
    }

    private static void checkFieldsArePresent(Object... toCheck) throws IllegalValueException {
        for (Object o : toCheck) {
            if (o == null) {
                throw new IllegalValueException(MISSING_FIELD_MESSAGE);
            }
        }
    }

    private static void checkFieldsAreValid(String lessonName, String subject, String lessonAddress)
            throws IllegalValueException {
        checkNameIsValid(lessonName);
        checkSubjectIsValid(subject);
        checkAddressIsValid(lessonAddress);
    }

    private static void checkNameIsValid(String lessonName) throws IllegalValueException {
        if (!LessonName.isValidName(lessonName)) {
            throw new IllegalValueException(LessonName.MESSAGE_CONSTRAINTS);
        }
    }

    private static void checkSubjectIsValid(String subject) throws IllegalValueException {
        if (!Subject.isValidSubject(subject)) {
            throw new IllegalValueException(Subject.MESSAGE_CONSTRAINTS);
        }
    }

    private static void checkAddressIsValid(String lessonAddress) throws IllegalValueException {
        if (!LessonAddress.isValidAddress(lessonAddress)) {
            throw new IllegalValueException(LessonAddress.MESSAGE_CONSTRAINTS);
        }
    }

    private static EnrolledStudents getEnrolledStudents(List<JsonAdaptedStudent> assignedStudents)
            throws IllegalValueException {
        EnrolledStudents enrolledStudents = new EnrolledStudents();

        for (JsonAdaptedStudent student : assignedStudents) {
            enrolledStudents.addStudent(student.toModelType());
        }

        return enrolledStudents;
    }
}
