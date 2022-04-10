package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListCommand;

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
        assertEquals(addressBooks, addressBookHistory.getPreviousAddressBook());
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



//    @Test
//    public void equals() {
//        String testCommand = ListCommand.COMMAND_WORD;
//
//        // no commands in history -> true
//        CommandHistory copyOfCommandHistory = new CommandHistory();
//        assertTrue(commandHistory.equals(copyOfCommandHistory));
//
//        // same object -> true
//        assertTrue(commandHistory.equals(commandHistory));
//
//        // null -> false
//        assertFalse(commandHistory.equals(null));
//
//        // different types -> false
//        assertFalse(commandHistory.equals(5));
//
//        // same commands in history -> true
//        copyOfCommandHistory.addToHistory(testCommand);
//        commandHistory.addToHistory(testCommand);
//        assertTrue(commandHistory.equals(copyOfCommandHistory));
//    }

}
