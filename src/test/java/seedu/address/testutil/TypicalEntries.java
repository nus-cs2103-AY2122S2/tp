package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_A;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_BIG_BANK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_JANICE_STREET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_A;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_A;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_INTERVIEW_BIG_BANK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_INTERVIEW_JANICE_STREET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_A;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_A;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_BEHAVIOURAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_TECHNICAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_A;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_B;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.entry.Company;
import seedu.address.model.entry.Event;
import seedu.address.model.entry.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalEntries {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline").withCompanyName("DBSSS")
            .withEmail("alice@example.com").withPhone("94351253").withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier").withCompanyName("Big Bank")
            .withPhone("98765432").withEmail("johnd@example.com").withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withCompanyName("DBSSS")
            .withPhone("95352563").withEmail("heinz@example.com").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withCompanyName("Janice Street")
            .withPhone("87652533").withEmail("cornelia@example.com").withTags("friends").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withCompanyName("DBSSS")
            .withPhone("9482224").withEmail("werner@example.com").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withCompanyName("Janice Street")
            .withPhone("9482427").withEmail("lydia@example.com").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withCompanyName("Big Bank")
            .withPhone("9482442").withEmail("anna@example.com").build();
    public static final Person JANE = new PersonBuilder().withName("Jane Lee").withCompanyName("Big Bank")
            .withPhone("6444121").withEmail("jane@example.com").withArchived(true).build();

    public static final Company DBSSS = new CompanyBuilder().withName("DBSSS").withPhone("91123671")
            .withEmail("dbsss@example.com").withAddress("14 Jurong Street").build();
    public static final Company BIG_BANK = new CompanyBuilder().withName("Big Bank").withPhone(VALID_PHONE_A)
            .withEmail(VALID_EMAIL_A).withAddress(VALID_ADDRESS_A).build();
    public static final Company JANICE_STREET = new CompanyBuilder().withName("Janice Street").withPhone(VALID_PHONE_B)
            .withEmail(VALID_EMAIL_B).withAddress(VALID_ADDRESS_B).build();

    public static final Event INTERVIEW_A = new EventBuilder().withName("DBSSS Interview").withCompanyName("DBSSS")
            .withDate("2022-05-01").withTime("10:00").withLocation("Zoom").withTags("Technical").build();
    public static final Event INTERVIEW_B = new EventBuilder().withName("BB Interview").withCompanyName("Big Bank")
            .withDate("2022-06-05").withTime("12:30").withLocation("16 Race Course Road").build();
    public static final Event ONLINE_ASSESSMENT = new EventBuilder().withName("Online Assessment")
            .withCompanyName("Janice Street").withDate("2022-04-30").withTime("18:00").withLocation("Hackerrank")
            .withTags("Difficult").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withCompanyName("Janice Street")
            .withPhone("8482424").withEmail("stefan@example.com").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withCompanyName("Big Bank")
            .withPhone("8482131").withEmail("hans@example.com").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY)
            .withCompanyName(VALID_COMPANY_BIG_BANK)
            .withPhone(VALID_PHONE_A).withEmail(VALID_EMAIL_A).withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB)
            .withCompanyName(VALID_COMPANY_JANICE_STREET)
            .withPhone(VALID_PHONE_B).withEmail(VALID_EMAIL_B).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final Event INTERVIEW_BIG_BANK = new EventBuilder()
            .withName(VALID_EVENT_INTERVIEW_BIG_BANK)
            .withCompanyName(VALID_COMPANY_BIG_BANK)
            .withDate(VALID_DATE_A).withTime(VALID_TIME_A)
            .withLocation(VALID_LOCATION_A).withTags(VALID_TAG_TECHNICAL).build();

    public static final Event INTERVIEW_JANICE_STREET = new EventBuilder()
            .withName(VALID_EVENT_INTERVIEW_JANICE_STREET)
            .withCompanyName(VALID_COMPANY_JANICE_STREET)
            .withDate(VALID_DATE_B).withTime(VALID_TIME_B).withLocation(VALID_LOCATION_B)
            .withTags(VALID_TAG_BEHAVIOURAL).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalEntries() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical entries.
     * A copy of each of the static entries above is made so that different calls
     * to this method will not be referring to the same entry objects.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Company company : getTypicalCompanies()) {
            ab.addCompany(new CompanyBuilder(company).build());
        }
        for (Person person : getTypicalPersons()) {
            ab.addPerson(new PersonBuilder(person).build());
        }
        for (Event event : getTypicalEvents()) {
            ab.addEvent(new EventBuilder(event).build());
        }
        return ab;
    }

    public static List<Company> getTypicalCompanies() {
        return new ArrayList<>(Arrays.asList(DBSSS, BIG_BANK, JANICE_STREET));
    }

    public static List<Event> getTypicalEvents() {
        return new ArrayList<>(Arrays.asList(INTERVIEW_A, INTERVIEW_B, ONLINE_ASSESSMENT));
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE, JANE));
    }
}
