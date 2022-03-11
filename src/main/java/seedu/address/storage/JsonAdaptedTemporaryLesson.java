package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.lesson.*;
import seedu.address.model.student.Student;
import seedu.address.model.tag.Tag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Jackson-friendly version of {@link TemporaryLesson}.
 */
class JsonAdaptedTemporaryLesson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Lesson's %s field is missing!";

    private final String lessonName;
    private final String subject;
    private final String lessonAddress;
    private final JsonAdaptedDateTimeSlot dateTimeSlot;
    private final List<JsonAdaptedStudent> assignedStudents = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedLesson} with the given lesson details.
     */
    @JsonCreator
    public JsonAdaptedTemporaryLesson(@JsonProperty("lessonName") String lessonName,
                                      @JsonProperty("subject") String subject,
                                      @JsonProperty("address") String address,
                                      @JsonProperty("dateTimeSlot") JsonAdaptedDateTimeSlot dateTimeSlot,
                                      @JsonProperty("assignedStudents") List<JsonAdaptedStudent> assignedStudents) {
        this.lessonName = lessonName;
        this.subject = subject;
        this.lessonAddress = address;
        this.dateTimeSlot = dateTimeSlot;
        if (assignedStudents != null) {
            this.assignedStudents.addAll(assignedStudents);
        }
    }

    /**
     * Converts a given {@code TemporaryLesson} into this class for Jackson use.
     */
    public JsonAdaptedTemporaryLesson(TemporaryLesson source) {
        this.lessonName = source.getName().fullName;
        this.subject = source.getSubject().subjectName;
        this.lessonAddress = source.getLessonAddress().value;
        this.dateTimeSlot = new JsonAdaptedDateTimeSlot(source.getDateTimeSlot());
        this.assignedStudents.addAll(source.getAssignedStudents().stream()
                .map(JsonAdaptedStudent::new)
                .collect(Collectors.toList()));
    }


    /**
     * Converts this Jackson-friendly adapted temporary lesson object into the model's {@code TemporaryLesson} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted temporary lesson.
     */
    public TemporaryLesson toModelType() throws IllegalValueException {
        final List<Student> modelAssignedStudents = new ArrayList<>();
        for (JsonAdaptedStudent student : assignedStudents) {
            modelAssignedStudents.add(student.toModelType());
        }

        if (lessonName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, LessonName.class.getSimpleName()));
        }
        if (!LessonName.isValidName(lessonName)) {
            throw new IllegalValueException(LessonName.MESSAGE_CONSTRAINTS);
        }
        final LessonName modelLessonName = new LessonName(lessonName);

        if (subject == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Subject.class.getSimpleName()));
        }
        if (!Subject.isValidSubject(subject)) {
            throw new IllegalValueException(Subject.MESSAGE_CONSTRAINTS);
        }
        final Subject modelSubject = new Subject(subject);

        if (lessonAddress == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, LessonAddress.class.getSimpleName()));
        }
        if (!LessonAddress.isValidAddress(lessonAddress)) {
            throw new IllegalValueException(LessonAddress.MESSAGE_CONSTRAINTS);
        }
        final LessonAddress modelLessonAddress = new LessonAddress(lessonAddress);

        if (dateTimeSlot == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, DateTimeSlot.class.getSimpleName()));
        }
        final DateTimeSlot modelDateTimeSlot = dateTimeSlot.toModelType();

        return Lesson.makeTemporaryLesson(modelLessonName, modelSubject, modelLessonAddress, modelDateTimeSlot);
    }

}
