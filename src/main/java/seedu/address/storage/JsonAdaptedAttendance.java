package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.attendance.Attendance;
import seedu.address.model.attendance.Comment;
import seedu.address.model.person.Name;
import seedu.address.model.person.NusNetId;

public class JsonAdaptedAttendance {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Attendance's %s field is missing!";
    private final String studentName;
    private final String studentId;
    private final String comments;
    private final List<String> studentAttendance = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedAttendance} with the given attendance details.
     */
    @JsonCreator
    public JsonAdaptedAttendance(@JsonProperty("studentName") String studentName,
            @JsonProperty("studentId") String studentId, @JsonProperty("comments") String comments,
            @JsonProperty("studentAttendance") List<String> studentAttendance) {
        this.studentName = studentName;
        this.studentId = studentId;
        this.comments = comments;
        if (studentAttendance != null) {
            this.studentAttendance.addAll(studentAttendance);
        }
    }

    /**
     * Converts a given {@code Attendance} into this class for Jackson use.
     */
    public JsonAdaptedAttendance(Attendance source) {
        studentName = source.getStudentName().toString();
        studentId = source.getStudentId().toString();
        comments = source.getComment().toString();
        studentAttendance.addAll(source.getAttendanceList()
            .stream()
            .map(x -> String.valueOf(x))
            .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Attendance} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted attendance.
     */
    public Attendance toModelType() throws IllegalValueException {
        if (studentName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Name.class.getSimpleName()));
        }
        if (!Name.isValidName(studentName)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        if (studentId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    NusNetId.class.getSimpleName()));
        }
        if (!NusNetId.isValidId(studentId)) {
            throw new IllegalValueException(NusNetId.MESSAGE_CONSTRAINTS);
        }

        if (studentAttendance.isEmpty()) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    "list"));
        }
        if (!listValidityCheck(studentAttendance)) {
            throw new IllegalValueException("Attendance list is not valid!");
        }

        if (comments == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Comment.class.getSimpleName()));
        }

        final Name modelStudentName = new Name(studentName);
        final NusNetId modelStudentId = new NusNetId(studentId);
        final ArrayList<Integer> attendanceList = new ArrayList<>();
        attendanceList.addAll(studentAttendance.stream()
            .map(x -> Integer.parseInt(x))
            .collect(Collectors.toList()));
        final Comment modelComment = new Comment(comments);

        return new Attendance(attendanceList, modelStudentName, modelStudentId, modelComment);
    }

    private boolean listValidityCheck(List<String> list) {
        boolean isValid = true;
        if (list.isEmpty()) {
            return false;
        }
        for (String s : list) {
            if (!s.chars().allMatch(Character::isDigit)
                || (!s.equals("1") && !s.equals("0"))) {
                isValid = false;
                break;
            }
        }
        return isValid;
    }
}
