package seedu.address.testutil;

import static seedu.address.logic.commands.SellerCommandTestUtil.VALID_APPOINTMENT_AMY;
import static seedu.address.logic.commands.SellerCommandTestUtil.VALID_APPOINTMENT_BOB;
import static seedu.address.logic.commands.SellerCommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.SellerCommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.SellerCommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.SellerCommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.SellerCommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.SellerCommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.SellerAddressBook;
import seedu.address.model.property.HouseType;
import seedu.address.model.property.PriceRange;
import seedu.address.model.seller.Seller;

/**
 * A utility class containing a list of {@code client} objects to be used in tests.
 */
public class TypicalSellers {

    // Sellers who are yet to indicate any properties to sell
    public static final Seller ALICE = new SellerBuilder().withName("Alice Pauline")
            .withPhone("94351253").withAppointment("2022-05-01-12-00")
            .withTags("friends").withProperty(
                    new PropertyToSellBuilder().withHouse(
                            new HouseBuilder().withHouseType(HouseType.BUNGALOW).withLocation("bishan").build())
                                    .withAddress("Bishan Street 5")
                                            .withPriceRange(new PriceRange(10, 20)).build()).build();

    public static final Seller BENSON = new SellerBuilder().withName("Benson Meier")
            .withPhone("98765432").withAppointment("2022-05-04-09-00")
            .withTags("owesMoney", "friends").withProperty(
            new PropertyToSellBuilder().withHouse(
                new HouseBuilder().withHouseType(HouseType.HDB_FLAT).withLocation("bishan").build())
                .withAddress("Bishan Ave 3 Blk 123")
                .withPriceRange(new PriceRange(10, 20)).build()).build();

    public static final Seller CARL = new SellerBuilder().withName("Carl Kurz")
            .withPhone("95352563").withProperty(
            new PropertyToSellBuilder().withHouse(
                new HouseBuilder().withHouseType(HouseType.COLONIA).withLocation("clementi").build())
                .withAddress("Clementi Town block 7")
                .withPriceRange(new PriceRange(10, 20)).build()).build();

    public static final Seller DANIEL = new SellerBuilder().withName("Daniel Meier")
            .withPhone("87652533").withTags("friends").withProperty(
            new PropertyToSellBuilder().withHouse(
                new HouseBuilder().withHouseType(HouseType.BUNGALOW).withLocation("bishan").build())
                .withAddress("Bishan District 8")
                .withPriceRange(new PriceRange(30, 50)).build()).build();

    public static final Seller ELLE = new SellerBuilder().withName("Elle Meyer")
            .withPhone("9482224").withProperty(
            new PropertyToSellBuilder().withHouse(
                new HouseBuilder().withHouseType(HouseType.APARTMENT).withLocation("ang mo kio").build())
                .withAddress("Ang Mo Kio Ave 3")
                .withPriceRange(new PriceRange(10, 20)).build()).build();

    public static final Seller FIONA = new SellerBuilder().withName("Fiona Kunz")
            .withPhone("9482427").withProperty(
            new PropertyToSellBuilder().withHouse(
                new HouseBuilder().withHouseType(HouseType.BUNGALOW).withLocation("bishan").build())
                .withAddress("Bishan Street 11")
                .withPriceRange(new PriceRange(20, 25)).build()).build();

    public static final Seller GEORGE = new SellerBuilder().withName("George Best")
            .withPhone("9482442").build();

    // Manually added
    public static final Seller HOON = new SellerBuilder().withName("Hoon Meier")
            .withPhone("8482424").build();
    public static final Seller IDA = new SellerBuilder().withName("Ida Mueller").withPhone("8482131")
            .build();

    // Manually added - client's details found in {@code CommandTestUtil}
    public static final Seller AMY = new SellerBuilder()
            .withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withAppointment(VALID_APPOINTMENT_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Seller BOB = new SellerBuilder()
            .withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withAppointment(VALID_APPOINTMENT_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalSellers() {} // prevents instantiation

    /**
     * Returns an {@code SellerAddressBook} with all the typical sellers.
     */
    public static SellerAddressBook getTypicalSellerAddressBook() {
        SellerAddressBook ab = new SellerAddressBook();
        for (Seller seller : getTypicalSellers()) {
            ab.addSeller(seller);
        }
        return ab;
    }


    public static List<Seller> getTypicalSellers() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
