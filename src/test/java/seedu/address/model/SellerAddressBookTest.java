package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.SellerCommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalSellers.ALICE;
import static seedu.address.testutil.TypicalSellers.BENSON;
import static seedu.address.testutil.TypicalSellers.CARL;
import static seedu.address.testutil.TypicalSellers.DANIEL;
import static seedu.address.testutil.TypicalSellers.ELLE;
import static seedu.address.testutil.TypicalSellers.FIONA;
import static seedu.address.testutil.TypicalSellers.GEORGE;
import static seedu.address.testutil.TypicalSellers.getTypicalSellerAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.client.exceptions.ClientNotFoundException;
import seedu.address.model.client.exceptions.DuplicateClientException;
import seedu.address.model.seller.Seller;
import seedu.address.testutil.SellerBuilder;

public class SellerAddressBookTest {

    private final SellerAddressBook addressBook = new SellerAddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getSellerList());
    }

    @Test
    public void constructor_copiesNewSellerAddressBook_success() {
        SellerAddressBook newData = getTypicalSellerAddressBook();
        SellerAddressBook newAddressBook = new SellerAddressBook(newData);
        assertEquals(newData, newAddressBook);
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        SellerAddressBook newData = getTypicalSellerAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicateSellers_throwsDuplicateclientException() {
        // Two seller with the same identity fields
        Seller editedAlice = new SellerBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Seller> newSellers = Arrays.asList(ALICE, editedAlice);
        SellerAddressBookTest.SellerAddressBookStub newData = new SellerAddressBookTest
                .SellerAddressBookStub(newSellers);

        assertThrows(DuplicateClientException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasSeller_nullSeller_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasSeller(null));
    }

    @Test
    public void hasSeller_sellerNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasSeller(ALICE));
    }

    @Test
    public void hasSeller_sellerInAddressBook_returnsTrue() {
        addressBook.addSeller(ALICE);
        assertTrue(addressBook.hasSeller(ALICE));
    }

    @Test
    public void hasSeller_sellerWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addSeller(ALICE);
        Seller editedAlice = new SellerBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasSeller(editedAlice));
    }

    @Test
    public void getSellerList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getSellerList().remove(0));
    }

    @Test
    public void removeSeller_sellerFoundInsideAddressBook_successRemoval() {
        Seller someSeller = new SellerBuilder(GEORGE).build();
        addressBook.addSeller(someSeller);
        addressBook.removeSeller(someSeller);
        assertEquals(Collections.emptyList(), addressBook.getSellerList());
    }

    @Test
    public void removeSeller_sellerNotFoundInsideAddressBook_failedRemoval() {
        Seller someSeller = new SellerBuilder(GEORGE).build();
        Seller otherSeller = new SellerBuilder(ALICE).build();
        addressBook.addSeller(someSeller);
        assertThrows(ClientNotFoundException.class, () -> addressBook.removeSeller(otherSeller));
    }

    @Test
    public void setSeller_sellerFoundInsideAddressBook_successSet() {
        Seller someSeller = new SellerBuilder(GEORGE).build();
        Seller otherSeller = new SellerBuilder(ALICE).build();
        addressBook.addSeller(someSeller);
        addressBook.setSeller(someSeller, otherSeller);
        List<Seller> testSellers = Arrays.asList(otherSeller);
        SellerAddressBookTest.SellerAddressBookStub stub = new SellerAddressBookTest.SellerAddressBookStub(testSellers);
        assertEquals(stub.getSellerList(), addressBook.getSellerList());
    }

    @Test
    public void setSeller_sellerNotFoundInsideAddressBook_failedSet() {
        Seller someSeller = new SellerBuilder(GEORGE).build();
        Seller otherSeller = new SellerBuilder(ALICE).build();
        assertThrows(ClientNotFoundException.class, () -> addressBook.setSeller(someSeller, otherSeller));
    }

    @Test
    public void setSeller_nullseller_failedSet() {
        Seller someSeller = new SellerBuilder(GEORGE).build();
        assertThrows(NullPointerException.class, () -> addressBook.setSeller(someSeller, null));
    }

    @Test
    public void sortSeller_byName_successfulSort() {
        SellerAddressBook typicalSellers = getTypicalSellerAddressBook();
        typicalSellers.sortSellers("name", "desc");
        setSellers(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE);
        assertNotEquals(typicalSellers, addressBook);
        addressBook.sortSellers("name", "desc");
        assertEquals(typicalSellers, addressBook);
    }

    @Test
    public void toString_sameSize() {
        SellerAddressBook testSellers = getTypicalSellerAddressBook();
        setSellers(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE);
        assertEquals(testSellers.toString(), addressBook.toString());
    }

    /**
     * A stub ReadOnlyAddressBook whose Sellers list can violate interface constraints.
     */
    private static class SellerAddressBookStub implements ReadOnlySellerAddressBook {
        private final ObservableList<Seller> sellers = FXCollections.observableArrayList();

        SellerAddressBookStub(Collection<Seller> sellers) {
            this.sellers.setAll(sellers);
        }

        @Override
        public ObservableList<Seller> getSellerList() {
            return sellers;
        }
    }

    public void setSellers(Seller ...sellers) {
        for (Seller b: sellers) {
            addressBook.addSeller(b);
        }
    }
}
