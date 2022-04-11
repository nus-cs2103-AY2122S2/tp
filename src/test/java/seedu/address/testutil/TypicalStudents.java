package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GITHUB_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GITHUB_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_GROUP_CS2101_G08;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_GROUP_CS2103T_W15_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.student.Student;

/**
 * A utility class containing a list of {@code Student} objects to be used in tests.
 */
public class TypicalStudents {

    public static final Student ALICE = new StudentBuilder().withName("Alice Pauline")
            .withGitHub(null).withEmail("alice@u.nus.edu")
            .withTelegram("@APauline")
            .withTutorialGroup("CS2103 W13-2").build();
    public static final Student BENSON = new StudentBuilder().withName("Benson Meier")
            .withGitHub("bensonguy")
            .withEmail("johnd@u.nus.edu").withTelegram(null)
            .withTutorialGroup("CS2106 T02", "CS2103 W13-2").build();
    public static final Student CARL = new StudentBuilder().withName("Carl Kurz Meier").withTelegram("@CarlJr")
            .withEmail("heinz@u.nus.edu").withGitHub("JrCarl").withTutorialGroup("CS2106 T02").build();
    public static final Student DANIEL = new StudentBuilder().withName("Daniel Carl Meier")
            .withTelegram("@DanieltheName").withEmail("cornelia@u.nus.edu")
            .withGitHub("Dan-iel").withTutorialGroup("CS2103 W13-2").build();
    public static final Student ELLE = new StudentBuilder().withName("Elle Meyer").withTelegram("@Elle20")
            .withEmail("werner@u.nus.edu").withGitHub("ELLE")
            .withTutorialGroup("CS2106 T02", "CS2103 W13-2", "CS2101 G08").build();
    public static final Student FIONA = new StudentBuilder().withName("Fiona Kunz").withTelegram("@Fiona14")
            .withEmail("lydia@u.nus.edu").withGitHub("Fiona-14")
            .withTutorialGroup("CS2106 T02", "CS2103T W15-3").build();
    public static final Student GEORGE = new StudentBuilder().withName("George Fiona Kun").withTelegram("@George11")
            .withEmail("anna@u.nus.edu").withGitHub("George-Not-Bush").withTutorialGroup("CS2106 T02").build();

    // Manually added
    public static final Student HOON = new StudentBuilder().withName("Hoon Meier").withTelegram("@Hooooon")
            .withEmail("stefan@u.nus.edu").withGitHub("hoon-meier-14").withTutorialGroup("CS2106 T02").build();
    public static final Student IDA = new StudentBuilder().withName("Ida Mueller").withTelegram("@idaida10")
            .withEmail("hans@u.nus.edu").withGitHub("mueller-ida-20").withTutorialGroup("CS2103 W13-2").build();

    // Manually added - Student's details found in {@code CommandTestUtil}
    public static final Student AMY = new StudentBuilder().withName(VALID_NAME_AMY).withTelegram(VALID_TELEGRAM_AMY)
            .withEmail(VALID_EMAIL_AMY).withGitHub(VALID_GITHUB_AMY)
            .withTutorialGroup(VALID_TUTORIAL_GROUP_CS2101_G08).build();
    public static final Student BOB = new StudentBuilder().withName(VALID_NAME_BOB).withTelegram(VALID_TELEGRAM_BOB)
            .withEmail(VALID_EMAIL_BOB).withGitHub(VALID_GITHUB_BOB)
            .withTutorialGroup(VALID_TUTORIAL_GROUP_CS2103T_W15_3, VALID_TUTORIAL_GROUP_CS2101_G08).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalStudents() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Student student : getTypicalStudents()) {
            ab.addStudent(student);
        }
        return ab;
    }

    public static List<Student> getTypicalStudents() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
