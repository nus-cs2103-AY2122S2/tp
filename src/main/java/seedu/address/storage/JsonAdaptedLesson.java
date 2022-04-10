package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.WeekId;
import seedu.address.model.student.Student;
import seedu.address.model.studentattendance.StudentAttendance;

/**
 * Jackson-friendly version of {@link Lesson}.
 */
class JsonAdaptedLesson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Lesson's %s field is missing!";
    public static final String STUDENT_LIST_DOES_NOT_MATCH = "Lesson's student attendance does not match with"
            + " class group student list!";
    public static final String MESSAGE_DUPLICATE_STUDENTS = "Lesson's student list contains duplicate student(s).";
    private final String weekId;
    private final List<JsonAdaptedStudentAttendance> studentAttendanceList = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedLesson} with the given lesson details.
     */
    @JsonCreator
    public JsonAdaptedLesson(@JsonProperty("weekId") String weekId,
                             @JsonProperty("studentAttendanceList")
                                     List<JsonAdaptedStudentAttendance> studentAttendanceList) {
        this.weekId = weekId;
        if (!studentAttendanceList.isEmpty()) {
            this.studentAttendanceList.addAll(studentAttendanceList);
        }
    }

    /**
     * Converts a given {@code Lesson} into this class for Jackson use.
     */
    public JsonAdaptedLesson(Lesson source) {
        weekId = String.valueOf(source.getWeekId().value);
        studentAttendanceList.addAll(source.getStudentAttendanceList().stream()
                .map(JsonAdaptedStudentAttendance::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted lesson object into the model's {@code Lesson} object.
     * Checks that the student the attendance is tied to already exists.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted lesson.
     */
    public Lesson toModelType(List<Student> studentList, List<Student> classGroupStudentList)
            throws IllegalValueException {

        if (classGroupStudentList.size() != studentAttendanceList.size()) {
            throw new IllegalValueException(STUDENT_LIST_DOES_NOT_MATCH);
        }

        if (weekId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, WeekId.class.getSimpleName()));
        }
        if (!WeekId.isValidWeekId(weekId)) {
            throw new IllegalValueException(WeekId.MESSAGE_CONSTRAINTS);
        }
        final WeekId modelWeekId = new WeekId(weekId);

        final List<StudentAttendance> modelStudentAttendances = new ArrayList<>();
        for (JsonAdaptedStudentAttendance sa : studentAttendanceList) {
            StudentAttendance modelSA = sa.toModelType(studentList, classGroupStudentList);
            if (modelStudentAttendances.stream().anyMatch(modelSA::isSameStudentAttendance)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STUDENTS);
            }
            modelStudentAttendances.add(modelSA);
        }

        return new Lesson(modelWeekId, modelStudentAttendances);
    }

}
