package seedu.address.storage;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.Student;
import seedu.address.model.studentattendance.Attendance;
import seedu.address.model.studentattendance.StudentAttendance;

/**
 * Jackson-friendly version of {@link StudentAttendance}.
 */
class JsonAdaptedStudentAttendance {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "StudentAttendance's %s field is missing!";
    public static final String NONEXISTENT_STUDENT = "Student does not exist!";
    private final JsonAdaptedStudent student;
    private final boolean isPresent;

    /**
     * Constructs a {@code JsonAdaptedStudentAttendance} with the given student attendance details.
     */
    @JsonCreator
    public JsonAdaptedStudentAttendance(@JsonProperty("student") JsonAdaptedStudent student,
                                        @JsonProperty("isPresent") Boolean isPresent) {
        this.student = student;
        this.isPresent = isPresent;
    }

    /**
     * Converts a given {@code StudentAttendance} into this class for Jackson use.
     */
    public JsonAdaptedStudentAttendance(StudentAttendance source) {
        student = new JsonAdaptedStudent(source.getStudent());
        isPresent = source.getAttendance().getValue();
    }

    /**
     * Converts this Jackson-friendly adapted class group object into the model's {@code StudentAttendance} object.
     * Checks that the student the attendance is tied to already exists.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted student attendance.
     */
    public StudentAttendance toModelType(List<Student> studentList) throws IllegalValueException {
        if (student == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Student.class.getSimpleName()));
        }
        final Student modelStudent = student.toModelType();
        if (!studentList.contains(modelStudent)) {
            throw new IllegalValueException(NONEXISTENT_STUDENT);
        }

        if (!Attendance.isValidAttendance(Boolean.toString(isPresent))) {
            throw new IllegalValueException(Attendance.MESSAGE_CONSTRAINTS);
        }
        final Attendance modelAttendance = new Attendance(isPresent);

        return new StudentAttendance(modelStudent, modelAttendance);
    }
}
