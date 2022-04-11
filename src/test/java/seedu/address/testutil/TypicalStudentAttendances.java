package seedu.address.testutil;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.studentattendance.StudentAttendance;

//@@author jxt00
/**
 * A utility class containing a list of {@code StudentAttendance} objects to be used in tests.
 */
public class TypicalStudentAttendances {
    public static final int TOTAL_STUDENT_ATTENDANCE = 6;
    public static final StudentAttendance ALICE_ATTENDANCE = getStudentAttendance(0);
    public static final StudentAttendance BENSON_ATTENDANCE = getStudentAttendance(1);
    public static final StudentAttendance CARL_ATTENDANCE = getStudentAttendance(2);
    public static final StudentAttendance DANIEL_ATTENDANCE = getStudentAttendance(3);
    public static final StudentAttendance ELLE_ATTENDANCE = getStudentAttendance(4);
    public static final StudentAttendance FIONA_ATTENDANCE = getStudentAttendance(5);

    private TypicalStudentAttendances() {} // prevents instantiation

    public static List<StudentAttendance> getTypicalStudentAttendances() {
        List<StudentAttendance> studentAttendanceList = new ArrayList<>();
        for (int i = 0; i < TOTAL_STUDENT_ATTENDANCE; i++) {
            studentAttendanceList.add(getStudentAttendance(i));
        }
        return studentAttendanceList;
    }

    public static StudentAttendance getStudentAttendance(int i) {
        switch (i) {
        case 0:
            return new StudentAttendanceBuilder()
                    .withStudent(TypicalStudents.ALICE)
                    .withAttendance("true").build();
        case 1:
            return new StudentAttendanceBuilder()
                    .withStudent(TypicalStudents.BENSON)
                    .withAttendance("true").build();
        case 2:
            return new StudentAttendanceBuilder()
                    .withStudent(TypicalStudents.CARL)
                    .withAttendance("true").build();
        case 3:
            return new StudentAttendanceBuilder()
                    .withStudent(TypicalStudents.DANIEL)
                    .withAttendance("false").build();
        case 4:
            return new StudentAttendanceBuilder()
                    .withStudent(TypicalStudents.ELLE)
                    .withAttendance("false").build();
        case 5:
            return new StudentAttendanceBuilder()
                    .withStudent(TypicalStudents.FIONA)
                    .withAttendance("false").build();
        default:
            return new StudentAttendanceBuilder().build();
        }
    }
}
