package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BURGER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BURGER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BURGER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BURGER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_TECH;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.ARTFRIEND;
import static seedu.address.testutil.TypicalClients.BURGER;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ClientBuilder;

public class ClientTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Client client = new ClientBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> client.getTags().remove(0));
    }

    @Test
    public void isSameClient() {
        // same object -> returns true
        assertTrue(ARTFRIEND.isSameClient(ARTFRIEND));

        // null -> returns false
        assertFalse(ARTFRIEND.isSameClient(null));

        // same address, all other attributes different -> returns true
        Client editedArtfriend = new ClientBuilder(ARTFRIEND)
                .withName(VALID_NAME_BURGER)
                .withPhone(VALID_PHONE_BURGER)
                .withEmail(VALID_EMAIL_BURGER)
                .withTags(VALID_TAG_TECH).build();
        assertTrue(ARTFRIEND.isSameClient(editedArtfriend));

        // different address, all other attributes same -> returns false
        editedArtfriend = new ClientBuilder(ARTFRIEND).withAddress(VALID_ADDRESS_BURGER).build();
        assertFalse(ARTFRIEND.isSameClient(editedArtfriend));

        // address has trailing spaces, all other attributes same -> returns false
        String addressWithTrailingSpaces = VALID_ADDRESS_BURGER + " ";
        Client editedBurger = new ClientBuilder(BURGER)
                .withAddress(addressWithTrailingSpaces).build();
        assertFalse(BURGER.isSameClient(editedBurger));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Client artfriendCopy = new ClientBuilder(ARTFRIEND).build();
        assertTrue(ARTFRIEND.equals(artfriendCopy));

        // same object -> returns true
        assertTrue(ARTFRIEND.equals(ARTFRIEND));

        // null -> returns false
        assertFalse(ARTFRIEND.equals(null));

        // different type -> returns false
        assertFalse(ARTFRIEND.equals(5));

        // different client -> returns false
        assertFalse(ARTFRIEND.equals(BURGER));

        // different name -> returns false
        Client editedArtfriend = new ClientBuilder(ARTFRIEND).withName(VALID_NAME_BURGER).build();
        assertFalse(ARTFRIEND.equals(editedArtfriend));

        // different phone -> returns false
        editedArtfriend = new ClientBuilder(ARTFRIEND).withPhone(VALID_PHONE_BURGER).build();
        assertFalse(ARTFRIEND.equals(editedArtfriend));

        // different email -> returns false
        editedArtfriend = new ClientBuilder(ARTFRIEND).withEmail(VALID_EMAIL_BURGER).build();
        assertFalse(ARTFRIEND.equals(editedArtfriend));

        // different address -> returns false
        editedArtfriend = new ClientBuilder(ARTFRIEND).withAddress(VALID_ADDRESS_BURGER).build();
        assertFalse(ARTFRIEND.equals(editedArtfriend));

        // different tags -> returns false
        editedArtfriend = new ClientBuilder(ARTFRIEND).withTags(VALID_TAG_TECH).build();
        assertFalse(ARTFRIEND.equals(editedArtfriend));
    }
}
