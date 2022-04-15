package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
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

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends")
            .build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends")
            .withStrengths("Good Defense", "Great Stamina")
            .withWeaknesses("Bad Offense", "Poor Endurance")
            .withMisc("birthday tomorrow", "likes Python").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withTags("friends")
            .withMisc("likes bing chilling").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    //Manually added - Person's details for sort testing
    private static final String noteStub1 = "something1";
    private static final String noteStub2 = "something2";
    private static final String noteStub3 = "something3";

    public static final Person PERSON_A = new PersonBuilder().withName("PersonA")
            .withStrengths(noteStub1, noteStub2, noteStub3).build();
    public static final Person PERSON_B = new PersonBuilder().withName("PersonB").withStrengths(noteStub1, noteStub2)
            .withWeaknesses(noteStub1).build();
    public static final Person PERSON_C = new PersonBuilder().withName("PersonC").withStrengths(noteStub1, noteStub2)
            .withWeaknesses(noteStub1, noteStub2).build();
    public static final Person PERSON_D = new PersonBuilder().withName("PersonD").withStrengths(noteStub1)
            .withWeaknesses(noteStub1).build();
    public static final Person PERSON_E = new PersonBuilder().withName("PersonE")
            .withWeaknesses(noteStub1, noteStub2, noteStub3).build();
    public static final Person PERSON_F = new PersonBuilder().withName("PersonF").withStrengths(noteStub1)
            .withWeaknesses(noteStub1, noteStub2).build();
    public static final Person PERSON_G = new PersonBuilder().withName("PersonG").withStrengths(noteStub1, noteStub2)
            .withWeaknesses(noteStub1, noteStub2).build();
    public static final Person PERSON_H = new PersonBuilder().withName("PersonH").withStrengths(noteStub1)
            .withWeaknesses(noteStub1).build();

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

    /**
     * Returns an {@code AddressBook} with sorted list of persons for strength-sort test cases.
     */
    public static AddressBook getGenericAddressBookWithSortedStrengths() {
        AddressBook ab = new AddressBook();
        for (Person person : getGenericPersonsWithSortedStrengths()) {
            ab.addPerson(person);
        }
        return ab;
    }

    /**
     * Returns an {@code AddressBook} with sorted list of persons for weakness-sort test cases.
     */
    public static AddressBook getGenericAddressBookWithSortedWeaknesses() {
        AddressBook ab = new AddressBook();
        for (Person person : getGenericPersonsWithSortedWeaknesses()) {
            ab.addPerson(person);
        }
        return ab;
    }

    /**
     * Returns an {@code AddressBook} with unsorted list of persons for strength-sort test cases.
     */
    public static AddressBook getGenericAddressBookWithUnsortedStrengths() {
        AddressBook ab = new AddressBook();
        for (Person person : getGenericPersonsWithUnsortedStrengths()) {
            ab.addPerson(person);
        }
        return ab;
    }

    /**
     * Returns an {@code AddressBook} with unsorted list of persons for weakness-sort test cases.
     */
    public static AddressBook getGenericAddressBookWithUnsortedWeaknesses() {
        AddressBook ab = new AddressBook();
        for (Person person : getGenericPersonsWithUnsortedWeaknesses()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    /**
     * Returns unsorted persons for sort-strength test cases.
     * @return list of persons
     */
    public static List<Person> getGenericPersonsWithSortedStrengths() {
        return new ArrayList<>(Arrays.asList(PERSON_A, PERSON_B, PERSON_C, PERSON_D));
    }

    /**
     * Returns unsorted persons for sort-weakness test cases.
     * @return list of persons
     */
    public static List<Person> getGenericPersonsWithSortedWeaknesses() {
        return new ArrayList<>(Arrays.asList(PERSON_E, PERSON_F, PERSON_G, PERSON_H));
    }

    /**
     * Returns unsorted persons for sort-strength test cases.
     * @return list of persons
     */
    public static List<Person> getGenericPersonsWithUnsortedStrengths() {
        return new ArrayList<>(Arrays.asList(PERSON_D, PERSON_A, PERSON_C, PERSON_B));
    }

    /**
     * Returns unsorted persons for sort-weakness test cases.
     * @return list of persons
     */
    public static List<Person> getGenericPersonsWithUnsortedWeaknesses() {
        return new ArrayList<>(Arrays.asList(PERSON_H, PERSON_E, PERSON_G, PERSON_F));
    }
}
