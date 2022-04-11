package seedu.address.model.attendance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.AttendancesTestUtil.VALID_COMMENT_E9999999;
import static seedu.address.logic.commands.StudentTestUtil.INVALID_NAME_ADAM;
import static seedu.address.logic.commands.StudentTestUtil.INVALID_STUDENT_ID_ADAM;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.ALEX;
import static seedu.address.testutil.TypicalStudents.EVA;
import static seedu.address.testutil.TypicalStudents.EVE;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Name;
import seedu.address.model.person.NusNetId;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniqueStudentsInTutorialList;
import seedu.address.model.tutorial.TutorialName;


public class AttendanceListTest {

    private final AttendanceList attendanceList = new AttendanceList(new ArrayList<>(), 3);

    private List<Person> studentList = new ArrayList<Person>() {
        {
            add(EVE);
            add(EVA);
        }
    };
    private TutorialName tutName = EVA.getTutorialName();
    private ObservableList<Person> observableStudentList = FXCollections.observableList(studentList);


    @Test
    public void generateAttendance_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> attendanceList.generateAttendance(null));
    }

    @Test
    public void generateAttendance_oneStudentList_generateSuccessful() {
        List<Person> studentList = new ArrayList<>();
        studentList.add(ALEX);
        ObservableList<Person> observableStudentList = FXCollections.observableList(studentList);
        attendanceList.generateAttendance(new UniqueStudentsInTutorialList(observableStudentList,
                new TutorialName("TG01")));
        assertEquals(attendanceList.getAttendances().size(), 1);
    }

    @Test
    public void getAttendancesByStudentName_nullName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> attendanceList.getAttendancesByStudentName(null));
    }

    @Test
    public void getAttendancesByStudentName_validName_returnsAttendanceList() {
        attendanceList.generateAttendance(new UniqueStudentsInTutorialList(observableStudentList,
                tutName));
        assertTrue(attendanceList.getAttendancesByStudentName(EVE.getName()).size() == 3);
    }

    @Test
    public void getAttendancesByStudentName_invalidId_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> attendanceList
                .getAttendancesByStudentName(new Name(INVALID_NAME_ADAM)));
    }

    @Test
    public void markAttendanceForStudent_nullId_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> attendanceList.markAttendanceForStudent(null, 2));
    }

    @Test
    public void markAttendanceForStudent_validId_successfulMark() {
        attendanceList.generateAttendance(new UniqueStudentsInTutorialList(observableStudentList,
                tutName));
        attendanceList.markAttendanceForStudent(EVE.getStudentId(), 2);
        Attendance eveAttendance = attendanceList.getAttendances().get(0); //EVE's attendance
        assertTrue(eveAttendance.getAttendanceList().get(1) == 1);
    }

    @Test
    public void markAttendanceForStudent_invalidId_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> attendanceList
                .markAttendanceForStudent(new NusNetId(INVALID_STUDENT_ID_ADAM), 2));
    }

    @Test
    public void markAllAttendance_twoStudentList_successfulMark() {
        attendanceList.generateAttendance(new UniqueStudentsInTutorialList(observableStudentList,
                tutName));
        attendanceList.markAllAttendance(2);
        Attendance eveAttendance = attendanceList.getAttendances().get(0);
        Attendance evaAttendance = attendanceList.getAttendances().get(1);
        assertTrue(eveAttendance.getAttendanceList().get(1) == 1
                && evaAttendance.getAttendanceList().get(1) == 1);
    }

    @Test
    public void unmarkAllAttendance_twoStudentList_successfulMark() {
        attendanceList.generateAttendance(new UniqueStudentsInTutorialList(observableStudentList,
                tutName));
        attendanceList.markAllAttendance(2);
        attendanceList.unmarkAllAttendance(2);
        Attendance eveAttendance = attendanceList.getAttendances().get(0);
        Attendance evaAttendance = attendanceList.getAttendances().get(1);
        assertTrue(eveAttendance.getAttendanceList().get(1) == 0
                && evaAttendance.getAttendanceList().get(1) == 0);
    }

    @Test
    public void unmarkAttendanceForStudent_invalidId_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> attendanceList
                .markAttendanceForStudent(new NusNetId(INVALID_STUDENT_ID_ADAM), 2));
    }

    @Test
    public void unmarkAttendanceForStudent_nullId_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> attendanceList
                .markAttendanceForStudent(null, 2));
    }

    @Test
    public void unmarkAttendanceForStudent_validId_successfulMark() {
        attendanceList.generateAttendance(new UniqueStudentsInTutorialList(observableStudentList,
                tutName));
        attendanceList.markAttendanceForStudent(EVE.getStudentId(), 2);
        attendanceList.unmarkAttendanceForStudent(EVE.getStudentId(), 2);
        Attendance eveAttendance = attendanceList.getAttendances().get(0); //EVE's attendance
        assertTrue(eveAttendance.getAttendanceList().get(1) == 0);
    }

    @Test
    public void addComment_nullComment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                attendanceList.addComment(EVE.getName(), null));
    }

    @Test
    public void addComment_validComment_successfulAddComment() {
        attendanceList.generateAttendance(new UniqueStudentsInTutorialList(observableStudentList,
                tutName));
        Comment comment = new Comment(VALID_COMMENT_E9999999);
        attendanceList.addComment(EVA.getName(), comment);
        Attendance evaAttendance = attendanceList.getAttendances().get(1);
        assertEquals(evaAttendance.getComment(), comment);
    }

    @Test
    public void removeComment_invalidName_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> attendanceList.removeComment(new Name(INVALID_NAME_ADAM)));
    }

    @Test
    public void removeComment_validName_successfulRemoveComment() {
        attendanceList.generateAttendance(new UniqueStudentsInTutorialList(observableStudentList,
                tutName));
        Comment comment = new Comment(VALID_COMMENT_E9999999);
        attendanceList.addComment(EVA.getName(), comment);
        attendanceList.removeComment(EVA.getName());
        Attendance evaAttendance = attendanceList.getAttendances().get(1);
        assertEquals(evaAttendance.getComment(), new Comment(""));
    }

    @Test
    public void viewComment_validName_successfulViewComment() {
        attendanceList.generateAttendance(new UniqueStudentsInTutorialList(observableStudentList,
                tutName));
        Comment comment = new Comment(VALID_COMMENT_E9999999);
        attendanceList.addComment(EVA.getName(), comment);
        assertEquals(attendanceList.viewComment(EVA.getName()), comment);
    }

}
