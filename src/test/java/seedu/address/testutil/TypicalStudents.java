package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GITHUB_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GITHUB_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENTID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENTID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.lab.Lab;
import seedu.address.model.lab.LabList;
import seedu.address.model.student.Student;
import seedu.address.model.util.LabTriplet;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalStudents {
    public static final Student ALICE = new StudentBuilder().withName("Alice Pauline")
            .withEmail("alice@example.com")
            .withTags("friends")
            .withGithub("aliceP")
            .withTelegram("alice_P")
            .withStudentId("A0123456B")
            .withLabs(new LabTriplet("1", "UNSUBMITTED"),
                    new LabTriplet("2", "GRADED", "11")).build();
    public static final Student BENSON = new StudentBuilder().withName("Benson Meier")
            .withEmail("johnd@example.com")
            .withTags("owesMoney", "friends")
            .withGithub("bensonM")
            .withTelegram("benson_M")
            .withStudentId("A0123457C")
            .withLabs(new LabTriplet("1", "SUBMITTED"),
                    new LabTriplet("2", "UNSUBMITTED")).build();
    public static final Student CARL = new StudentBuilder().withName("Carl Kurz")
            .withEmail("heinz@example.com")
            .withGithub("carlK")
            .withTelegram("carl_K")
            .withStudentId("A0123458D")
            .withLabs(new LabTriplet("1", "UNSUBMITTED"),
                    new LabTriplet("2", "UNSUBMITTED")).build();
    public static final Student DANIEL = new StudentBuilder().withName("Daniel Meier")
            .withEmail("cornelia@example.com")
            .withTags("friends")
            .withGithub("danielM")
            .withTelegram("daniel_M")
            .withStudentId("A0123459E")
            .withLabs(new LabTriplet("1", "UNSUBMITTED"),
                    new LabTriplet("2", "UNSUBMITTED")).build();
    public static final Student ELLE = new StudentBuilder().withName("Elle Meyer")
            .withEmail("werner@example.com")
            .withGithub("elleM")
            .withTelegram("elle_M")
            .withStudentId("A0123411A")
            .withLabs(new LabTriplet("1", "GRADED", "10"),
                    new LabTriplet("2", "GRADED", "12")).build();
    public static final Student FIONA = new StudentBuilder().withName("Fiona Kunz")
            .withEmail("lydia@example.com")
            .withGithub("fionaK")
            .withTelegram("fiona_K")
            .withStudentId("A0123412B")
            .withLabs(new LabTriplet("1", "SUBMITTED"),
                    new LabTriplet("2", "GRADED", "13")).build();
    public static final Student GEORGE = new StudentBuilder().withName("George Best")
            .withEmail("anna@example.com")
            .withGithub("georgeB")
            .withTelegram("george_B")
            .withStudentId("A0123413C")
            .withLabs(new LabTriplet("1", "GRADED", "9"),
                    new LabTriplet("2", "UNSUBMITTED")).build();

    // Manually added
    public static final Student HOON = new StudentBuilder().withName("Hoon Meier")
            .withEmail("stefan@example.com")
            .withGithub("hoonM")
            .withTelegram("hoon_M")
            .withStudentId("A0123414D")
            .withLabs(new LabTriplet("1", "UNSUBMITTED")).build();
    public static final Student IDA = new StudentBuilder().withName("Ida Mueller")
            .withEmail("hans@example.com")
            .withGithub("idaM")
            .withTelegram("ida_M")
            .withStudentId("A0123415E")
            .withLabs(new LabTriplet("1", "UNSUBMITTED")).build();

    // Manually added - Student's details found in {@code CommandTestUtil}
    public static final Student AMY = new StudentBuilder().withName(VALID_NAME_AMY)
            .withEmail(VALID_EMAIL_AMY)
            .withTags(VALID_TAG_FRIEND)
            .withGithub(VALID_GITHUB_AMY)
            .withTelegram(VALID_TELEGRAM_AMY)
            .withStudentId(VALID_STUDENTID_AMY)
            .build();
    public static final Student BOB = new StudentBuilder().withName(VALID_NAME_BOB)
            .withEmail(VALID_EMAIL_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .withGithub(VALID_GITHUB_BOB)
            .withTelegram(VALID_TELEGRAM_BOB)
            .withStudentId(VALID_STUDENTID_BOB)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalStudents() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical students.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Student student : getTypicalStudents()) {
            Student temp = new StudentBuilder(student).build(); // Ensures that we have a fresh copy for each test case.
            ab.addStudent(temp);
        }
        ab.getMasterLabList().add(new Lab("1"));
        return ab;
    }

    /**
     * Returns an {@code AddressBook} with all the typical students but without any Lab.
     */
    public static AddressBook getTypicalAddressBookWithoutLabs() {
        AddressBook ab = new AddressBook();
        for (Student student : getTypicalStudents()) {
            Student temp = new StudentBuilder(student).build(); // Ensures that we have a fresh copy for each test case.
            temp.setLabs(new LabList());
            ab.addStudent(temp);
        }
        return ab;
    }

    public static List<Student> getTypicalStudents() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
