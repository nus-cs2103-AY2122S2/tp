package seedu.address.storage;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.assessment.Grade;
import seedu.address.model.student.Student;

//@@author jxt00
/**
 * Jackson-friendly version of {@code Map.Entry<Student, Grade>}.
 */
class JsonAdaptedAttempt {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Attempt's %s field is missing!";
    public static final String NONEXISTENT_STUDENT = "Student does not exist!";

    private final JsonAdaptedStudent student;
    private final String grade;

    /**
     * Constructs a {@code JsonAdaptedAttempt} with the given attempt details.
     */
    @JsonCreator
    public JsonAdaptedAttempt(@JsonProperty("student") JsonAdaptedStudent student,
                              @JsonProperty("grade") String grade) {
        this.student = student;
        this.grade = grade;
    }

    /**
     * Converts a given {@code Map.Entry<Student, Grade>} into this class for Jackson use.
     */
    public JsonAdaptedAttempt(Map.Entry<Student, Grade> source) {
        student = new JsonAdaptedStudent(source.getKey());
        grade = String.valueOf(source.getValue());
    }

    /**
     * Converts this Jackson-friendly adapted attempt object into a {@code Entry<Student, Grade>} object.
     * Checks that the student the grade is tied to already exists.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted attempt.
     */
    public Map.Entry<Student, Grade> toModelType(List<Student> studentList) throws IllegalValueException {
        if (student == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Student.class.getSimpleName()));
        }
        final Student modelStudent = student.toModelType();
        if (!studentList.contains(modelStudent)) {
            throw new IllegalValueException(NONEXISTENT_STUDENT);
        }

        if (grade == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Grade.class.getSimpleName()));
        }
        if (!Grade.isValidGrade(grade)) {
            throw new IllegalValueException(Grade.MESSAGE_CONSTRAINTS);
        }
        final Grade modelGrade = new Grade(grade);

        return new AbstractMap.SimpleEntry<>(modelStudent, modelGrade);
    }
}
