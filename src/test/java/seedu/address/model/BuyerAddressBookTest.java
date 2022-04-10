package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalBuyers.ALICE;
import static seedu.address.testutil.TypicalBuyers.BENSON;
import static seedu.address.testutil.TypicalBuyers.CARL;
import static seedu.address.testutil.TypicalBuyers.DANIEL;
import static seedu.address.testutil.TypicalBuyers.ELLE;
import static seedu.address.testutil.TypicalBuyers.FIONA;
import static seedu.address.testutil.TypicalBuyers.GEORGE;
import static seedu.address.testutil.TypicalBuyers.getTypicalBuyerAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.buyer.Buyer;
import seedu.address.model.client.exceptions.ClientNotFoundException;
import seedu.address.model.client.exceptions.DuplicateClientException;
import seedu.address.testutil.BuyerBuilder;


public class BuyerAddressBookTest {

    private final BuyerAddressBook addressBook = new BuyerAddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getBuyerList());
    }

    @Test
    public void constructor_copiesNewBuyerAddressBook_success() {
        BuyerAddressBook newData = getTypicalBuyerAddressBook();
        BuyerAddressBook newAddressBook = new BuyerAddressBook(newData);
        assertEquals(newData, newAddressBook);
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        BuyerAddressBook newData = getTypicalBuyerAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicateBuyers_throwsDuplicateclientException() {
        // Two buyer with the same identity fields
        Buyer editedAlice = new BuyerBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Buyer> newBuyers = Arrays.asList(ALICE, editedAlice);
        BuyerAddressBookStub newData = new BuyerAddressBookStub(newBuyers);

        assertThrows(DuplicateClientException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasBuyer_nullBuyer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasBuyer(null));
    }

    @Test
    public void hasBuyer_buyerNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasBuyer(ALICE));
    }

    @Test
    public void hasBuyer_buyerInAddressBook_returnsTrue() {
        addressBook.addBuyer(ALICE);
        assertTrue(addressBook.hasBuyer(ALICE));
    }

    @Test
    public void hasBuyer_buyerWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addBuyer(ALICE);
        Buyer editedAlice = new BuyerBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasBuyer(editedAlice));
    }

    @Test
    public void getBuyerList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getBuyerList().remove(0));
    }

    @Test
    public void removeBuyer_buyerFoundInsideAddressBook_successRemoval() {
        Buyer someBuyer = new BuyerBuilder(GEORGE).build();
        addressBook.addBuyer(someBuyer);
        addressBook.removeBuyer(someBuyer);
        assertEquals(Collections.emptyList(), addressBook.getBuyerList());
    }

    @Test
    public void removeBuyer_buyerNotFoundInsideAddressBook_failedRemoval() {
        Buyer someBuyer = new BuyerBuilder(GEORGE).build();
        Buyer otherBuyer = new BuyerBuilder(ALICE).build();
        addressBook.addBuyer(someBuyer);
        assertThrows(ClientNotFoundException.class, () -> addressBook.removeBuyer(otherBuyer));
    }

    @Test
    public void setBuyer_buyerFoundInsideAddressBook_successSet() {
        Buyer someBuyer = new BuyerBuilder(GEORGE).build();
        Buyer otherBuyer = new BuyerBuilder(ALICE).build();
        addressBook.addBuyer(someBuyer);
        addressBook.setBuyer(someBuyer, otherBuyer);
        List<Buyer> testBuyers = Arrays.asList(otherBuyer);
        BuyerAddressBookStub stub = new BuyerAddressBookStub(testBuyers);
        assertEquals(stub.getBuyerList(), addressBook.getBuyerList());
    }

    @Test
    public void setBuyer_buyerNotFoundInsideAddressBook_failedSet() {
        Buyer someBuyer = new BuyerBuilder(GEORGE).build();
        Buyer otherBuyer = new BuyerBuilder(ALICE).build();
        assertThrows(ClientNotFoundException.class, () -> addressBook.setBuyer(someBuyer, otherBuyer));
    }

    @Test
    public void setBuyer_nullbuyer_failedSet() {
        Buyer someBuyer = new BuyerBuilder(GEORGE).build();
        assertThrows(NullPointerException.class, () -> addressBook.setBuyer(someBuyer, null));
    }

    @Test
    public void sortBuyer_byName_successfulSort() {
        BuyerAddressBook typicalBuyers = getTypicalBuyerAddressBook();
        typicalBuyers.sortBuyers("name", "desc");
        setBuyers(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE);
        assertNotEquals(typicalBuyers, addressBook);
        addressBook.sortBuyers("name", "desc");
        assertEquals(typicalBuyers, addressBook);
    }

    @Test
    public void toString_sameSize() {
        BuyerAddressBook testBuyers = getTypicalBuyerAddressBook();
        setBuyers(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE);
        assertEquals(testBuyers.toString(), addressBook.toString());
    }

    /**
     * A stub ReadOnlyAddressBook whose Buyers list can violate interface constraints.
     */
    private static class BuyerAddressBookStub implements ReadOnlyBuyerAddressBook {
        private final ObservableList<Buyer> buyers = FXCollections.observableArrayList();

        BuyerAddressBookStub(Collection<Buyer> buyers) {
            this.buyers.setAll(buyers);
        }

        @Override
        public ObservableList<Buyer> getBuyerList() {
            return buyers;
        }
    }

    public void setBuyers(Buyer ...buyers) {
        for (Buyer b: buyers) {
            addressBook.addBuyer(b);
        }
    }

}
