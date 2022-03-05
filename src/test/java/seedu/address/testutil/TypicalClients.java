package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
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
import seedu.address.model.client.Client;

/**
 * A utility class containing a list of {@code client} objects to be used in tests.
 */
public class TypicalClients {

    public static final Client ALICE = new ClientBuilder().withName("Alice Pauline")
            .withDescription("A desc")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withRemark("Alice remark")
            .withTags("friends").build();
    public static final Client BENSON = new ClientBuilder().withName("Benson Meier")
            .withDescription("B desc")
            .withAddress("311, Clementi Ave 2, #02-25").withEmail("johnd@example.com")
            .withPhone("98765432").withRemark("Benson remark")
            .withTags("owesMoney", "friends").build();
    public static final Client CARL = new ClientBuilder().withName("Carl Kurz")
            .withDescription("C desc").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").withRemark("Carl remark").build();
    public static final Client DANIEL = new ClientBuilder().withName("Daniel Meier")
            .withDescription("D desc").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street")
            .withRemark("Daniel remark").withTags("friends").build();
    public static final Client ELLE = new ClientBuilder().withName("Elle Meyer")
            .withDescription("E desc").withPhone("9482224")
            .withEmail("werner@example.com")
            .withRemark("Elle remark").withAddress("michegan ave").build();
    public static final Client FIONA = new ClientBuilder().withName("Fiona Kunz")
            .withDescription("F desc").withPhone("9482427")
            .withEmail("lydia@example.com")
            .withRemark("Fiona remark").withAddress("little tokyo").build();
    public static final Client GEORGE = new ClientBuilder().withName("George Best")
            .withDescription("G desc").withPhone("9482442")
            .withEmail("anna@example.com")
            .withRemark("George remark").withAddress("4th street").build();

    // Manually added
    public static final Client HOON = new ClientBuilder().withName("Hoon Meier")
            .withDescription("H desc").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").withRemark("Hoon remark").build();
    public static final Client IDA = new ClientBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").withRemark("Ida remark").build();

    // Manually added - client's details found in {@code CommandTestUtil}
    public static final Client AMY = new ClientBuilder().withDescription(VALID_DESCRIPTION_AMY)
            .withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withRemark(VALID_REMARK_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Client BOB = new ClientBuilder().withDescription(VALID_DESCRIPTION_BOB)
            .withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withRemark(VALID_REMARK_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalClients() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical clients.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Client client : getTypicalclients()) {
            ab.addclient(client);
        }
        return ab;
    }

    public static List<Client> getTypicalclients() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
