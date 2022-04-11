package seedu.address.testutil;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.student.Student;

/**
 * A utility class containing a list of {@code Student} objects to be used in tests.
 */
public class TypicalStudents {
    public static final int TOTAL_STUDENT = 6;
    public static final Student ALICE = getStudent(0);
    public static final Student BENSON = getStudent(1);
    public static final Student CARL = getStudent(2);
    public static final Student DANIEL = getStudent(3);
    public static final Student ELLE = getStudent(4);
    public static final Student FIONA = getStudent(5);

    //Manual Add
    public static final Student HOON = getStudent(6);
    public static final Student IDA = getStudent(7);

    private TypicalStudents() {} // prevents instantiation

    public static List<Student> getTypicalStudents() {
        List<Student> studentList = new ArrayList<>();
        for (int i = 0; i < TOTAL_STUDENT; i++) {
            studentList.add(getStudent(i));
        }
        return studentList;
    }

    public static Student getStudent(int i) {
        switch (i) {
        case 0:
            return new StudentBuilder().withName("Alice Pauline")
                    .withStudentId("E0123457").withEmail("alice@example.com")
                    .withTelegram("alicehandle").build();
        case 1:
            return new StudentBuilder().withName("Benson Meier")
                    .withStudentId("E0123458").withEmail("benson@example.com")
                    .withTelegram("bensonhandle").build();
        case 2:
            return new StudentBuilder().withName("Carl Kurz")
                    .withStudentId("E0123459").withEmail("carl@example.com").build();
        case 3:
            return new StudentBuilder().withName("Daniel Meier")
                    .withStudentId("E0234568").withEmail("cornelia@example.com").build();
        case 4:
            return new StudentBuilder().withName("Elle Meyer")
                    .withStudentId("E0345689").withEmail("werner@example.com")
                    .withTelegram("elleeeeeee").build();
        case 5:
            return new StudentBuilder().withName("Fiona Kunz")
                    .withStudentId("E0458690").withEmail("lydia@example.com")
                    .withTelegram("FIONA_LA").build();
        case 6:
            return new StudentBuilder().withName("Hoon Meier")
                    .withStudentId("E0458691").withEmail("hoon@example.com")
                    .withTelegram("HoonHoon").build();
        case 7:
            return new StudentBuilder().withName("Ida Mueller")
                    .withStudentId("E0458692").withEmail("ida@example.com").build();
        default:
            return new StudentBuilder().build();
        }
    }

}
