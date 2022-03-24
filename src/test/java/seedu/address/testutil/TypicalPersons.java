package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PREFERENCE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_USERTYPE_BUYER;

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
            .withPhone("94351253").withFavourite(true)
            .withUserType("buyer").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withUserType("buyer").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withFavourite(true).withEmail("heinz@example.com").withAddress("wall street")
            .withUserType("buyer").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withFavourite(true).withEmail("cornelia@example.com")
            .withAddress("10th street").withUserType("buyer").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").withUserType("buyer").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").withUserType("buyer").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withFavourite(true).withEmail("anna@example.com").withAddress("4th street")//.withPreference()
            //.withProperties("East,4th street,1-room,$100000").withUserType("seller").build();
            .withUserType("buyer").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withUserType(VALID_USERTYPE_BUYER)
            .withPreference(VALID_PREFERENCE_BOB).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withUserType(VALID_USERTYPE_BUYER)
            .withPreference(VALID_PREFERENCE_BOB).build();

    // Sellers with properties
    public static final Person PROTY = new PersonBuilder().withName("Proty Per").withPhone("35748962")
            .withEmail("hastwo@property.com").withAddress("orchard").withUserType("seller")
            .withProperties("East,4th street,1-room,$100000").withProperties("West,5th street,2-room,$300000")
            .build();
    public static final Person PERTY = new PersonBuilder().withName("Perty Pro").withPhone("45693286")
            .withEmail("hassome@property.com").withAddress("somerset").withUserType("seller")
            .withProperties("North,6th street,3-room,$500000").withProperties("South,7th street,4-room,$700000")
            .build();

    // Buyers with preference
    public static final Person PREF = new PersonBuilder().withName("Pref Fen").withPhone("37586624")
            .withEmail("hasa@preference.com").withAddress("newton").withUserType("buyer")
            .withPreference("East,2-room,$50000,$500000").build();
    public static final Person FEREN = new PersonBuilder().withName("Feren Nence").withPhone("25594936")
            .withEmail("hasanother@preference.com").withAddress("novena").withUserType("buyer")
            .withPreference("West,3-room,$100000,$1000000").build();

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

    /**
     * Returns an {@code AddressBook} with various types of persons.
     */
    public static AddressBook getVariousAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getVariousPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getVariousPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE,
                PROTY, PERTY, PREF, FEREN));
    }
}
