package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BURGER;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.ARTFRIEND;
import static seedu.address.testutil.TypicalClients.BURGER;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.client.Client;
import seedu.address.model.client.exceptions.DuplicateClientException;
import seedu.address.model.procedure.Procedure;
import seedu.address.testutil.ClientBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getClientList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicateClients_throwsDuplicateClientException() {
        // Two clients with the same address fields
        Client editedBurger = new ClientBuilder(ARTFRIEND)
                .withAddress(VALID_ADDRESS_BURGER)
                .build();
        List<Client> newClients = Arrays.asList(BURGER, editedBurger);
        AddressBookStub newData = new AddressBookStub(newClients);

        assertThrows(DuplicateClientException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasClient_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasClient(null));
    }

    @Test
    public void hasClient_clientWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addClient(BURGER);
        // editedBurger only has the same address as burger
        Client editedBurger = new ClientBuilder(ARTFRIEND).withAddress(VALID_ADDRESS_BURGER)
                .build();
        assertTrue(addressBook.hasClient(editedBurger));
    }

    @Test
    public void getClientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getClientList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose clients list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Client> clients = FXCollections.observableArrayList();
        private final ObservableList<Procedure> procedures = FXCollections.observableArrayList();

        AddressBookStub(Collection<Client> clients) {
            this.clients.setAll(clients);
        }

        @Override
        public ObservableList<Client> getClientList() {
            return clients;
        }

        @Override
        public ObservableList<Procedure> getProcedureList() {
            return procedures;
        }
    }

}
