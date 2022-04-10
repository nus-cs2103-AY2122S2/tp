package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AddressBookHistoryTest {
    private AddressBookHistory addressBookHistory;

    @BeforeEach
    public void setUp() {
        AddressBook addressBook = new AddressBook();
        addressBookHistory = new AddressBookHistory(addressBook);
    }

    @Test
    public void constructor() {
        ArrayList<AddressBook> addressBooks = new ArrayList<>();
        AddressBook addressBook = new AddressBook();
        addressBooks.add(new AddressBook(addressBook));
        assertEquals(addressBooks, addressBookHistory.getAddressBooks());
    }

    @Test
    public void isEmpty_addressBookHistorySavesAddressBookOnInitialisation_returnsFalse() {
        assertFalse(addressBookHistory.isEmpty());
    }

    @Test
    public void isEmpty_hasAddressBooksInAddressBookHistory_returnsFalse() {
        ArrayList<AddressBook> addressBooks = new ArrayList<>();
        AddressBook addressBook = new AddressBook();
        addressBooks.add(new AddressBook(addressBook));
        assertFalse(addressBookHistory.isEmpty());
    }

    @Test
    public void getPreviousAddressBook_addressBookHistoryHasOnlyOneAddressBook_returnsAddressBook() {
        AddressBook addressBook = new AddressBook();
        assertEquals(addressBook, addressBookHistory.getPreviousAddressBook());
    }

    @Test
    public void getPreviousAddressBook_addAndRemoveFromAddressBookHistory_returnsSameAddressBook() {
        AddressBook addressBook = new AddressBook();
        addressBookHistory.addAddressBook(new AddressBook(addressBook));
        assertEquals(addressBook, addressBookHistory.getPreviousAddressBook());
    }

    @Test
    public void clearHistory_clearFromNewAddressBookHistory_returnsSameAddressBookHistory() {
        AddressBook addressBook = new AddressBook();
        AddressBookHistory expectedAddressBookHistory = new AddressBookHistory(addressBook);

        addressBookHistory.clearHistory();
        assertEquals(expectedAddressBookHistory, addressBookHistory);
    }

    @Test
    public void toString_newAddressBookHistory_success() {
        String expectedString = "1. \n";
        assertEquals(expectedString, addressBookHistory.toString());
    }

    @Test
    public void equals() {
        // same object -> true
        assertTrue(addressBookHistory.equals(addressBookHistory));

        // null -> false
        assertFalse(addressBookHistory.equals(null));

        // different types -> false
        assertFalse(addressBookHistory.equals(5));

        // same AddressBooks in history -> true
        AddressBook addressBook = new AddressBook();
        AddressBookHistory copyOfAddressBookHistory = new AddressBookHistory(addressBook);
        assertTrue(addressBookHistory.equals(copyOfAddressBookHistory));
    }
}
