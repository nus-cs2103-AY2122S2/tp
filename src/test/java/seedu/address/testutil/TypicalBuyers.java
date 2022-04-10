package seedu.address.testutil;

import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_APPOINTMENT_AMY;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_APPOINTMENT_BOB;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_APPOINTMENT_CHAD;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_NAME_CHAD;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_PHONE_CHAD;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_PROPERTY_BUY_CHAD;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.BuyerAddressBook;
import seedu.address.model.buyer.Buyer;
import seedu.address.model.property.HouseType;
import seedu.address.model.property.PriceRange;

/**
 * A utility class containing a list of {@code client} objects to be used in tests.
 */
public class TypicalBuyers {

    // Buyers who are yet to indicate any properties to buy
    public static final Buyer ALICE = new BuyerBuilder().withName("Alice Pauline")
            .withPhone("94351253").withAppointment("2022-05-01-12-00")
            .withTags("friends").withProperty(
            new PropertyToBuyBuilder().withHouse(
                new HouseBuilder().withHouseType(HouseType.BUNGALOW).withLocation("clementi").build())
            .withPriceRange(new PriceRange(0, 20)).build()).build();
    public static final Buyer BENSON = new BuyerBuilder().withName("Benson Meier")
            .withPhone("98765432").withAppointment("2022-05-04-09-00")
            .withTags("owesMoney", "friends").withProperty(
            new PropertyToBuyBuilder().withHouse(
                new HouseBuilder().withHouseType(HouseType.CONDOMINIUM).withLocation("utown").build())
            .withPriceRange(new PriceRange(0, 20)).build()).build();;
    public static final Buyer CARL = new BuyerBuilder().withName("Carl Kurz")
            .withPhone("95352563").withProperty(
            new PropertyToBuyBuilder().withHouse(
                new HouseBuilder().withHouseType(HouseType.UNSPECIFIED).withLocation("bishan").build())
                    .withPriceRange(new PriceRange(0, 20)).build()).build();
    public static final Buyer DANIEL = new BuyerBuilder().withName("Daniel Meier")
            .withPhone("87652533").withTags("friends").build();
    public static final Buyer ELLE = new BuyerBuilder().withName("Elle Meyer")
            .withPhone("9482224").build();
    public static final Buyer FIONA = new BuyerBuilder().withName("Fiona Kunz")
            .withPhone("9482427").build();
    public static final Buyer GEORGE = new BuyerBuilder().withName("George Best")
            .withPhone("9482442").build();

    // Manually added
    public static final Buyer HOON = new BuyerBuilder().withName("Hoon Meier")
            .withPhone("8482424").build();
    public static final Buyer IDA = new BuyerBuilder().withName("Ida Mueller").withPhone("8482131").build();

    // Manually added - client's details found in {@code CommandTestUtil}
    public static final Buyer AMY = new BuyerBuilder()
            .withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withAppointment(VALID_APPOINTMENT_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Buyer BOB = new BuyerBuilder()
            .withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withAppointment(VALID_APPOINTMENT_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

    public static final Buyer CHAD = new BuyerBuilder()
            .withName(VALID_NAME_CHAD).withPhone(VALID_PHONE_CHAD)
            .withAppointment(VALID_APPOINTMENT_CHAD).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .withProperty(VALID_PROPERTY_BUY_CHAD).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalBuyers() {} // prevents instantiation

    /**
     * Returns an {@code BuyerAddressBook} with all the typical buyers.
     */
    public static BuyerAddressBook getTypicalBuyerAddressBook() {
        BuyerAddressBook ab = new BuyerAddressBook();
        for (Buyer buyer : getTypicalBuyers()) {
            ab.addBuyer(buyer);
        }
        return ab;
    }

    public static List<Buyer> getTypicalBuyers() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
