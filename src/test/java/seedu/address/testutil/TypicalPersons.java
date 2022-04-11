package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TITLE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TITLE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRONOUN_HIM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRONOUN_SHE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRONOUN_THEY;

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
            .withAddresses("123, Jurong West Ave 6, #08-111 l/home").withEmails("alice@example.com l/business")
            .withNumbers("94351253").withCompany("Tesla").withJobTitle("CEO")
            .withPronouns("she")
            .withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddresses("311, Clementi Ave 2, #02-25")
            .withEmails("johnd@example.com").withNumbers("98765432")
            .withCompany("Apple").withJobTitle("Accountant")
            .withPronouns("him")
            .withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withNumbers("95352563 l/personal")
            .withEmails("heinz@example.com l/personal", "heinzWork@example.com l/work")
            .withAddresses("wall street").withCompany("Samsung").withJobTitle("Data Analyst").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withNumbers("87652533")
            .withCompany("exampleCompany").withJobTitle("exampleJob").withTags("friends").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withNumbers("9482224")
            .withCompany("exampleCompany").withJobTitle("exampleJob")
            .withEmails("werner@example.com").withAddresses("michegan ave").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz")
            .withNumbers("9482427").withCompany("exampleCompany").withJobTitle("exampleJob")
            .withEmails("lydia@example.com").withAddresses("little tokyo").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withNumbers("9482442")
            .withCompany("exampleCompany").withJobTitle("exampleJob")
            .withEmails("anna@example.com").withAddresses("4th street").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier")
            .withNumbers("8482424").withCompany("exampleCompany").withJobTitle("exampleJob")
            .withEmails("stefan@example.com").withAddresses("little india").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller")
            .withNumbers("8482131").withCompany("exampleCompany").withJobTitle("exampleJob")
            .withEmails("hans@example.com").withAddresses("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withNumbers(VALID_PHONE_AMY)
            .withCompany(VALID_COMPANY_AMY).withJobTitle(VALID_JOB_TITLE_AMY)
            .withEmails(VALID_EMAIL_AMY).withAddresses(VALID_ADDRESS_AMY)
            .withPronouns(VALID_PRONOUN_SHE, VALID_PRONOUN_THEY)
            .build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withNumbers(VALID_PHONE_BOB)
            .withCompany(VALID_COMPANY_BOB).withJobTitle(VALID_JOB_TITLE_BOB)
            .withEmails(VALID_EMAIL_BOB).withAddresses(VALID_ADDRESS_BOB)
            .withPronouns(VALID_PRONOUN_THEY, VALID_PRONOUN_HIM)
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
