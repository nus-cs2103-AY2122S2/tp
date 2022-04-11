package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MOD_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MOD_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_NUMBER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_NUMBER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.ContactList;
import seedu.address.model.person.Mod;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniqueModuleList;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withMod("CS2100").withEmail("alice@example.com")
            .withStudentNumber("A0246813G").withGroup("T01")
            .withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withMod("CS2040S")
            .withEmail("johnd@example.com").withStudentNumber("A0123456L").withGroup("T01")
            .withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withStudentNumber("A1234567M")
            .withEmail("heinz@example.com").withMod("MA1101R").withGroup("T01").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withStudentNumber("A1357902K")
            .withEmail("cornelia@example.com").withMod("MA1521").withGroup("T01").withTags("friends").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withStudentNumber("A1234123A")
            .withEmail("werner@example.com").withMod("ACC1701X").withGroup("T01").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withStudentNumber("A5678567C")
            .withEmail("lydia@example.com").withMod("FIN2704X").withGroup("T01").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withStudentNumber("A7890789X")
            .withEmail("anna@example.com").withMod("CS3244").withGroup("T01").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withStudentNumber("A1001001L")
            .withEmail("stefan@example.com").withMod("CS2100").withGroup("T01").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withStudentNumber("A0000001S")
            .withEmail("hans@example.com").withMod("CS2100").withGroup("T01").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY =
            new PersonBuilder().withName(VALID_NAME_AMY).withStudentNumber(VALID_STUDENT_NUMBER_AMY)
            .withEmail(VALID_EMAIL_AMY).withMod(VALID_MOD_AMY)
            .withGroup(VALID_GROUP_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB)
            .withStudentNumber(VALID_STUDENT_NUMBER_BOB)
            .withEmail(VALID_EMAIL_BOB).withMod(VALID_MOD_BOB)
            .withGroup(VALID_GROUP_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code ContactList} with all the typical persons.
     */
    public static ContactList getTypicalContactList() {
        ContactList ab = new ContactList();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }
    public static Mod[] getTypicalModules() {
        return new Mod[] {
            new Mod("CS2108", "W12"),
            new Mod("CS2109S", "W14"),
            new Mod("CS2100", "W16"),
        };
    }

    public static UniqueModuleList getTypicalModuleList() {
        UniqueModuleList moduleList = new UniqueModuleList();
        for (Mod mod : getTypicalModules()) {
            moduleList.add(mod);
        }
        return moduleList;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
