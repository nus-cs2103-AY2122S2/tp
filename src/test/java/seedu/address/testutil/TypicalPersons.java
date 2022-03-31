package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BLOCK_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BLOCK_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COVID_STATUS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COVID_STATUS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FACULTY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FACULTY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MATRICULATION_NUMBER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MATRICULATION_NUMBER_BOB;
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
            .withBlock("B")
            .withFaculty("SOC")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withMatriculationNumber("A0000000E")
            .withCovidStatus("NEGATIVE")
            .withTags("friends")
            .build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withBlock("C")
            .withFaculty("BIZ")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withMatriculationNumber("A1234567X")
            .withCovidStatus("HRN")
            .withTags("owesMoney", "friends")
            .build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz")
            .withBlock("E")
            .withFaculty("YLLSOM")
            .withPhone("95352563")
            .withEmail("heinz@example.com")
            .withAddress("wall street")
            .withMatriculationNumber("A9988773V")
            .withCovidStatus("HRN")
            .build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier")
            .withBlock("A")
            .withFaculty("FOS")
            .withPhone("87652533")
            .withEmail("cornelia@example.com")
            .withAddress("10th street")
            .withMatriculationNumber("A3212345B")
            .withCovidStatus("POSITIVE")
            .withTags("friends")
            .build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer")
            .withBlock("E")
            .withFaculty("DUKE")
            .withPhone("9482224")
            .withEmail("werner@example.com")
            .withAddress("michegan ave")
            .withMatriculationNumber("A4345678N")
            .withCovidStatus("HRN")
            .build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz")
            .withBlock("B")
            .withFaculty("CDE")
            .withPhone("9482427")
            .withEmail("lydia@example.com")
            .withAddress("little tokyo")
            .withMatriculationNumber("A1114446J")
            .withCovidStatus("NEGATIVE")
            .build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best")
            .withBlock("E")
            .withFaculty("YSTCOM")
            .withPhone("9482442")
            .withEmail("anna@example.com")
            .withAddress("4th street")
            .withMatriculationNumber("A0043245H")
            .withCovidStatus("POSITIVE")
            .build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier")
            .withBlock("E")
            .withFaculty("SOC")
            .withPhone("8482424")
            .withEmail("stefan@example.com")
            .withAddress("little india")
            .withMatriculationNumber("A0843245H")
            .withCovidStatus("POSITIVE")
            .build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").withMatriculationNumber("A5243627L").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY)
            .withBlock(VALID_BLOCK_AMY)
            .withFaculty(VALID_FACULTY_AMY)
            .withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY)
            .withAddress(VALID_ADDRESS_AMY)
            .withMatriculationNumber(VALID_MATRICULATION_NUMBER_AMY)
            .withCovidStatus(VALID_COVID_STATUS_AMY)
            .withTags(VALID_TAG_FRIEND)
            .build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB)
            .withBlock(VALID_BLOCK_BOB)
            .withFaculty(VALID_FACULTY_BOB)
            .withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB)
            .withAddress(VALID_ADDRESS_BOB)
            .withMatriculationNumber(VALID_MATRICULATION_NUMBER_BOB)
            .withCovidStatus(VALID_COVID_STATUS_BOB)
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
