package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.buyer.Buyer;

/**
 * A utility class containing a list of {@code client} objects to be used in tests.
 */
public class TypicalBuyers {

    // Buyers who are yet to indicate any properties to buy
    public static final Buyer YUQI = new BuyerBuilder().withName("Alice Pauline")
            .withDescription("A desc")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withRemark("Alice remark").withAppointment("2022-05-01-12-00")
            .withTags("friends").build();
    public static final Buyer SHIHONG = new BuyerBuilder().withName("Benson Meier")
            .withDescription("B desc")
            .withAddress("311, Clementi Ave 2, #02-25").withEmail("johnd@example.com")
            .withPhone("98765432").withRemark("Benson remark").withAppointment("2022-05-04-09-00")
            .withTags("owesMoney", "friends").build();
    public static final Buyer JUNHENG = new BuyerBuilder().withName("Carl Kurz")
            .withDescription("C desc").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").withRemark("Carl remark").build();
    public static final Buyer JUNHONG = new BuyerBuilder().withName("Daniel Meier")
            .withDescription("D desc").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street")
            .withRemark("Daniel remark").withTags("friends").build();
    public static final Buyer JANALD = new BuyerBuilder().withName("Elle Meyer")
            .withDescription("E desc").withPhone("9482224")
            .withEmail("werner@example.com")
            .withRemark("Elle remark").withAddress("michegan ave").build();
    public static final Buyer DARA = new BuyerBuilder().withName("Fiona Kunz")
            .withDescription("F desc").withPhone("9482427")
            .withEmail("lydia@example.com")
            .withRemark("Fiona remark").withAddress("little tokyo").build();
    public static final Buyer RICHARD = new BuyerBuilder().withName("George Best")
            .withDescription("G desc").withPhone("9482442")
            .withEmail("anna@example.com")
            .withRemark("George remark").withAddress("4th street").build();

    // Manually added
    public static final Buyer HOON = new BuyerBuilder().withName("Hoon Meier")
            .withDescription("H desc").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").withRemark("Hoon remark").build();
    public static final Buyer IDA = new BuyerBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").withRemark("Ida remark").build();

    // Manually added - client's details found in {@code CommandTestUtil}
    public static final Buyer AMY = new BuyerBuilder().withDescription(VALID_DESCRIPTION_AMY)
            .withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withRemark(VALID_REMARK_AMY)
            .withAppointment(VALID_APPOINTMENT_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Buyer BOB = new BuyerBuilder().withDescription(VALID_DESCRIPTION_BOB)
            .withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withRemark(VALID_REMARK_BOB)
            .withAppointment(VALID_APPOINTMENT_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalBuyers() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical clients.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Buyer buyer : getTypicalBuyers()) {
            ab.addclient(buyer);
        }
        return ab;
    }

    public static List<Buyer> getTypicalBuyers() {
        return new ArrayList<>(Arrays.asList(JUNHENG, JUNHONG, SHIHONG, JANALD, DARA, RICHARD, YUQI));
    }
    
}
