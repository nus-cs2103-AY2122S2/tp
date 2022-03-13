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
import seedu.address.model.person.Person;
import seedu.address.model.person.lab.Lab;
import seedu.address.model.person.lab.LabList;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withEmail("alice@example.com")
            .withTags("friends")
            .withGithub("aliceP")
            .withTelegram("alice_P")
            .withStudentId("A0123456B")
            .withLabs("1").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withEmail("johnd@example.com")
            .withTags("owesMoney", "friends")
            .withGithub("bensonM")
            .withTelegram("benson_M")
            .withStudentId("A0123457C")
            .withLabs("1").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz")
            .withEmail("heinz@example.com")
            .withGithub("carlK")
            .withTelegram("carl_K")
            .withStudentId("A0123458D")
            .withLabs("1").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier")
            .withEmail("cornelia@example.com")
            .withTags("friends")
            .withGithub("danielM")
            .withTelegram("daniel_M")
            .withStudentId("A0123459E")
            .withLabs("1").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer")
            .withEmail("werner@example.com")
            .withGithub("elleM")
            .withTelegram("elle_M")
            .withStudentId("A0123411A")
            .withLabs("1").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz")
            .withEmail("lydia@example.com")
            .withGithub("fionaK")
            .withTelegram("fiona_K")
            .withStudentId("A0123412B")
            .withLabs("1").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best")
            .withEmail("anna@example.com")
            .withGithub("georgeB")
            .withTelegram("george_B")
            .withStudentId("A0123413C")
            .withLabs("1").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier")
            .withEmail("stefan@example.com")
            .withGithub("hoonM")
            .withTelegram("hoon_M")
            .withStudentId("A0123414D")
            .withLabs("1").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller")
            .withEmail("hans@example.com")
            .withGithub("idaM")
            .withTelegram("ida_M")
            .withStudentId("A0123415E")
            .withLabs("1").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY)
            .withEmail(VALID_EMAIL_AMY)
            .withTags(VALID_TAG_FRIEND)
            .withGithub(VALID_GITHUB_AMY)
            .withTelegram(VALID_TELEGRAM_AMY)
            .withStudentId(VALID_STUDENTID_AMY)
            .build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB)
            .withEmail(VALID_EMAIL_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .withGithub(VALID_GITHUB_BOB)
            .withTelegram(VALID_TELEGRAM_BOB)
            .withStudentId(VALID_STUDENTID_BOB)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            Person temp = new PersonBuilder(person).build(); // Ensures that we have a fresh copy for each test case.
            ab.addPerson(temp);
        }
        ab.getMasterLabList().add(new Lab("1"));
        return ab;
    }

    /**
     * Returns an {@code AddressBook} with all the typical persons but without any Lab.
     */
    public static AddressBook getTypicalAddressBookWithoutLabs() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            Person temp = new PersonBuilder(person).build(); // Ensures that we have a fresh copy for each test case.
            temp.setLabs(new LabList());
            ab.addPerson(temp);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
