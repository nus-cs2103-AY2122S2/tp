package seedu.address.storage;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.assessment.Grade;
import seedu.address.model.student.Student;
import seedu.address.model.tamodule.TaModule;

//@@author jxt00
/**
 * Jackson-friendly version of {@code Map.Entry<Student, Grade>}.
 */
class JsonAdaptedAttempt {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Attempt's %s field is missing!";
    public static final String UNENROLLED_STUDENT = "Student is not enrolled in the module!";

    private final String studentId;
    private final String grade;

    /**
     * Constructs a {@code JsonAdaptedAttempt} with the given attempt details.
     */
    @JsonCreator
    public JsonAdaptedAttempt(@JsonProperty("studentId") String studentId,
                              @JsonProperty("grade") String grade) {
        this.studentId = studentId;
        this.grade = grade;
    }

    /**
     * Converts a given {@code Map.Entry<Student, Grade>} into this class for Jackson use.
     */
    public JsonAdaptedAttempt(Map.Entry<Student, Grade> source) {
        studentId = source.getKey().getStudentId().value;
        grade = String.valueOf(source.getValue());
    }

    /**
     * Converts this Jackson-friendly adapted attempt object into a {@code Entry<Student, Grade>} object.
     * Checks that the studentId the grade is tied to already exists.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted attempt.
     */
    public Map.Entry<Student, Grade> toModelType(TaModule module, List<Student> studentList)
            throws IllegalValueException {

        final Student modelStudent = StorageUtil.getStudentByStudentId(studentList, studentId,
                MISSING_FIELD_MESSAGE_FORMAT);

        if (!module.hasStudent(modelStudent)) {
            throw new IllegalValueException(UNENROLLED_STUDENT);
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
