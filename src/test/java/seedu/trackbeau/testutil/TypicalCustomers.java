package seedu.trackbeau.testutil;

import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_ALLERGY_AMY;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_ALLERGY_BOB;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_BIRTHDATE_AMY;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_BIRTHDATE_BOB;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_HAIR_TYPE_AMY;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_HAIR_TYPE_BOB;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_REG_DATE_AMY;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_REG_DATE_BOB;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_SERVICE_AMY;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_SERVICE_BOB;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_SKIN_TYPE_AMY;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_SKIN_TYPE_BOB;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_STAFF_AMY;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_STAFF_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.trackbeau.model.TrackBeau;
import seedu.trackbeau.model.customer.Customer;

/**
 * A utility class containing a list of {@code Customer} objects to be used in tests.
 */
public class TypicalCustomers {

    public static final Customer ALICE = new CustomerBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withSkinType("oily").withHairType("dry")
            .withStaffs("Jason").withAllergies("Nickel").withServices("Acne treatment")
            .withBirthdate("01-01-2000").withRegistrationDate("23-03-2022").build();
    public static final Customer BENSON = new CustomerBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withSkinType("oily").withHairType("dry")
            .withStaffs("Jason").withServices("Chemical Peel")
            .withBirthdate("01-01-2000").withRegistrationDate("23-03-2022").build();
    public static final Customer CARL = new CustomerBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street")
            .withSkinType("oily").withHairType("dry").withBirthdate("01-05-2000")
            .withRegistrationDate("23-03-2022").build();
    public static final Customer DANIEL = new CustomerBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street")
            .withSkinType("oily").withHairType("dry").withAllergies("Cocoa Butter")
            .withBirthdate("01-01-1990")
            .withRegistrationDate("23-03-2022").build();
    public static final Customer ELLE = new CustomerBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave")
            .withSkinType("oily").withHairType("dry")
            .withRegistrationDate("23-03-2022").withBirthdate("01-01-2000").build();
    public static final Customer FIONA = new CustomerBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo")
            .withSkinType("oily").withHairType("dry")
            .withRegistrationDate("23-03-2022").withBirthdate("01-01-2000").build();
    public static final Customer GEORGE = new CustomerBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street")
            .withSkinType("oily").withHairType("dry").withBirthdate("01-01-2000")
            .withRegistrationDate("23-03-2022").build();

    // Manually added
    public static final Customer HOON = new CustomerBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").build();
    public static final Customer IDA = new CustomerBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Customer's details found in {@code CommandTestUtil}
    public static final Customer AMY = new CustomerBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
            .withSkinType(VALID_SKIN_TYPE_AMY)
            .withHairType(VALID_HAIR_TYPE_AMY)
            .withBirthdate(VALID_BIRTHDATE_AMY)
            .withRegistrationDate(VALID_REG_DATE_AMY)
            .withStaffs(VALID_STAFF_AMY)
            .withServices(VALID_SERVICE_AMY)
            .withAllergies(VALID_ALLERGY_AMY)
            .build();

    public static final Customer BOB = new CustomerBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB)
            .withAddress(VALID_ADDRESS_BOB)
            .withSkinType(VALID_SKIN_TYPE_BOB)
            .withHairType(VALID_HAIR_TYPE_BOB)
            .withBirthdate(VALID_BIRTHDATE_BOB)
            .withRegistrationDate(VALID_REG_DATE_BOB)
            .withStaffs(VALID_STAFF_BOB)
            .withServices(VALID_SERVICE_BOB)
            .withAllergies(VALID_ALLERGY_BOB)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalCustomers() {} // prevents instantiation

    /**
     * Returns an {@code TrackBeau} with all the typical customers.
     */
    public static TrackBeau getTypicalTrackBeau() {
        TrackBeau tb = new TrackBeau();
        for (Customer customer : getTypicalCustomers()) {
            tb.addCustomer(customer);
        }
        return tb;
    }

    public static List<Customer> getTypicalCustomers() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
