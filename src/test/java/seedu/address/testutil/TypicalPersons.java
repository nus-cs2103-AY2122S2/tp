package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_COURSE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COURSE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder()
            .withStudentID("E0123456")
            .withName("Alice Pauline")
            .withPhone("94351253")
            .withEmail("E0123456@u.nus.edu")
            .withCourse("Business Analytics")
            .withTags("friends")
            .build();
    public static final Person BENSON = new PersonBuilder()
            .withStudentID("E0234567")
            .withName("Benson Meier")
            .withPhone("98765432")
            .withEmail("E0234567@u.nus.edu")
            .withCourse("Computer Engineering")
            .withTags("owesMoney", "friends")
            .build();
    public static final Person CARL = new PersonBuilder()
            .withStudentID("E0345678")
            .withName("Carl Kurz")
            .withPhone("95352563")
            .withEmail("E0345678@u.nus.edu")
            .withCourse("Computer Science")
            .build();
    public static final Person DANIEL = new PersonBuilder()
            .withStudentID("E0456789")
            .withName("Daniel Meier")
            .withPhone("87652533")
            .withEmail("E0456789@u.nus.edu")
            .withCourse("Information Security")
            .withTags("friends")
            .build();
    public static final Person ELLE = new PersonBuilder()
            .withStudentID("E0567890")
            .withName("Elle Meyer")
            .withPhone("9482224")
            .withEmail("E0567890@u.nus.edu")
            .withCourse("Information Systems")
            .build();
    public static final Person FIONA = new PersonBuilder()
            .withStudentID("E0678901")
            .withName("Fiona Kunz")
            .withPhone("9482427")
            .withEmail("E0678901@u.nus.edu")
            .withCourse("Business Analytics")
            .build();
    public static final Person GEORGE = new PersonBuilder()
            .withStudentID("E0789012")
            .withName("George Best")
            .withPhone("9482442")
            .withEmail("E0789012@u.nus.edu")
            .withCourse("Computer Engineering")
            .build();

    // Manually added
    public static final Person HOON = new PersonBuilder()
            .withStudentID("E0890123")
            .withName("Hoon Meier")
            .withPhone("8482424")
            .withEmail("E0890123@u.nus.edu")
            .withCourse("Computer Science")
            .build();
    public static final Person IDA = new PersonBuilder()
            .withStudentID("E0901234")
            .withName("Ida Mueller")
            .withPhone("8482131")
            .withEmail("E0901234@u.nus.edu")
            .withCourse("Information Security")
            .build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder()
            .withStudentID(VALID_STUDENT_ID_AMY)
            .withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY)
            .withCourse(VALID_COURSE_AMY)
            .withTags(VALID_TAG_FRIEND)
            .build();
    public static final Person BOB = new PersonBuilder()
            .withStudentID(VALID_STUDENT_ID_BOB)
            .withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB)
            .withCourse(VALID_COURSE_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
