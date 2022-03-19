package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.student.Student;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalStudents {
    public static final Student ALICE = new StudentBuilder().withName("Alice Pauline")
            .withStudentId("E0123457").withEmail("alice@example.com")
            .withTelegram("alicehandle").build();
    public static final Student BENSON = new StudentBuilder().withName("Benson Meier")
            .withStudentId("E0123458").withEmail("benson@example.com")
            .withTelegram("bensonhandle").build();
    public static final Student CARL = new StudentBuilder().withName("Carl Kurz")
            .withStudentId("E0123459").withEmail("carl@example.com").build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalStudents() {} // prevents instantiation

    public static List<Student> getTypicalStudents() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL));
    }
}
