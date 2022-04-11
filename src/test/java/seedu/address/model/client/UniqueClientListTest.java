package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BURGER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_TECH;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.ARTFRIEND;
import static seedu.address.testutil.TypicalClients.BURGER;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.client.exceptions.ClientNotFoundException;
import seedu.address.model.client.exceptions.DuplicateClientException;
import seedu.address.testutil.ClientBuilder;

public class UniqueClientListTest {

    private final UniqueClientList uniqueClientList = new UniqueClientList();

    @Test
    public void contains_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClientList.contains(null));
    }

    @Test
    public void contains_clientNotInList_returnsFalse() {
        assertFalse(uniqueClientList.contains(ARTFRIEND));
    }

    @Test
    public void contains_clientInList_returnsTrue() {
        uniqueClientList.add(ARTFRIEND);
        assertTrue(uniqueClientList.contains(ARTFRIEND));
    }

    @Test
    public void contains_clientWithSameAddressInList_returnsTrue() {
        uniqueClientList.add(BURGER);
        Client editedBurger = new ClientBuilder(ARTFRIEND).withAddress(VALID_ADDRESS_BURGER)
                .build();
        assertTrue(uniqueClientList.contains(editedBurger));
    }

    @Test
    public void add_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClientList.add(null));
    }

    @Test
    public void add_duplicateClient_throwsDuplicateClientException() {
        uniqueClientList.add(ARTFRIEND);
        assertThrows(DuplicateClientException.class, () -> uniqueClientList.add(ARTFRIEND));
    }

    @Test
    public void setClient_nullTargetClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClientList.setClient(null, ARTFRIEND));
    }

    @Test
    public void setClient_nullEditedClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClientList.setClient(ARTFRIEND, null));
    }

    @Test
    public void setClient_targetClientNotInList_throwsClientNotFoundException() {
        assertThrows(ClientNotFoundException.class, () -> uniqueClientList.setClient(ARTFRIEND, ARTFRIEND));
    }

    @Test
    public void setClient_editedClientIsSameClient_success() {
        uniqueClientList.add(ARTFRIEND);
        uniqueClientList.setClient(ARTFRIEND, ARTFRIEND);
        UniqueClientList expectedUniqueClientList = new UniqueClientList();
        expectedUniqueClientList.add(ARTFRIEND);
        assertEquals(expectedUniqueClientList, uniqueClientList);
    }

    @Test
    public void setClient_editedClientHasSameIdentity_success() {
        uniqueClientList.add(ARTFRIEND);
        Client editedAlice = new ClientBuilder(ARTFRIEND).withAddress(VALID_ADDRESS_BURGER).withTags(VALID_TAG_TECH)
                .build();
        uniqueClientList.setClient(ARTFRIEND, editedAlice);
        UniqueClientList expectedUniqueClientList = new UniqueClientList();
        expectedUniqueClientList.add(editedAlice);
        assertEquals(expectedUniqueClientList, uniqueClientList);
    }

    @Test
    public void setClient_editedClientHasDifferentIdentity_success() {
        uniqueClientList.add(ARTFRIEND);
        uniqueClientList.setClient(ARTFRIEND, BURGER);
        UniqueClientList expectedUniqueClientList = new UniqueClientList();
        expectedUniqueClientList.add(BURGER);
        assertEquals(expectedUniqueClientList, uniqueClientList);
    }

    @Test
    public void setClient_editedClientHasNonUniqueIdentity_throwsDuplicateClientException() {
        uniqueClientList.add(ARTFRIEND);
        uniqueClientList.add(BURGER);
        assertThrows(DuplicateClientException.class, () -> uniqueClientList.setClient(ARTFRIEND, BURGER));
    }

    @Test
    public void remove_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClientList.remove(null));
    }

    @Test
    public void remove_clientDoesNotExist_throwsClientNotFoundException() {
        assertThrows(ClientNotFoundException.class, () -> uniqueClientList.remove(ARTFRIEND));
    }

    @Test
    public void remove_existingClient_removesClient() {
        uniqueClientList.add(ARTFRIEND);
        uniqueClientList.remove(ARTFRIEND);
        UniqueClientList expectedUniqueClientList = new UniqueClientList();
        assertEquals(expectedUniqueClientList, uniqueClientList);
    }

    @Test
    public void setClients_nullUniqueClientList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClientList.setClients((UniqueClientList) null));
    }

    @Test
    public void setClients_uniqueClientList_replacesOwnListWithProvidedUniqueClientList() {
        uniqueClientList.add(ARTFRIEND);
        UniqueClientList expectedUniqueClientList = new UniqueClientList();
        expectedUniqueClientList.add(BURGER);
        uniqueClientList.setClients(expectedUniqueClientList);
        assertEquals(expectedUniqueClientList, uniqueClientList);
    }

    @Test
    public void setClients_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClientList.setClients((List<Client>) null));
    }

    @Test
    public void setClients_list_replacesOwnListWithProvidedList() {
        uniqueClientList.add(ARTFRIEND);
        List<Client> clientList = Collections.singletonList(BURGER);
        uniqueClientList.setClients(clientList);
        UniqueClientList expectedUniqueClientList = new UniqueClientList();
        expectedUniqueClientList.add(BURGER);
        assertEquals(expectedUniqueClientList, uniqueClientList);
    }

    @Test
    public void setClients_listWithDuplicateClients_throwsDuplicateClientException() {
        List<Client> listWithDuplicateClients = Arrays.asList(ARTFRIEND, ARTFRIEND);
        assertThrows(DuplicateClientException.class, () -> uniqueClientList.setClients(listWithDuplicateClients));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueClientList.asUnmodifiableObservableList().remove(0));
    }
}
