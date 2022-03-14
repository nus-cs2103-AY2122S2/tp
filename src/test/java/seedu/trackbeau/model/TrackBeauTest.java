package seedu.trackbeau.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.trackbeau.testutil.Assert.assertThrows;
import static seedu.trackbeau.testutil.TypicalCustomers.ALICE;
import static seedu.trackbeau.testutil.TypicalCustomers.getTypicalTrackBeau;

import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.trackbeau.model.customer.Customer;

public class TrackBeauTest {

    private final TrackBeau trackBeau = new TrackBeau();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), trackBeau.getCustomerList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> trackBeau.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyTrackBeau_replacesData() {
        TrackBeau newData = getTypicalTrackBeau();
        trackBeau.resetData(newData);
        assertEquals(newData, trackBeau);
    }

    /*
    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Customer editedAlice = new CustomerBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withStaffs(STAFF_DESC_BOB)
                .build();
        List<Customer> newCustomers = Arrays.asList(ALICE, editedAlice);
        TrackBeauStub newData = new TrackBeauStub(newCustomers);

        assertThrows(DuplicateCustomerException.class, () -> trackBeau.resetData(newData));
    }
*/
    @Test
    public void hasCustomer_nullCustomer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> trackBeau.hasCustomer(null));
    }

    @Test
    public void hasCustomer_customerNotInTrackBeau_returnsFalse() {
        assertFalse(trackBeau.hasCustomer(ALICE));
    }

    @Test
    public void hasCustomer_customerInTrackBeau_returnsTrue() {
        trackBeau.addCustomer(ALICE);
        assertTrue(trackBeau.hasCustomer(ALICE));
    }

    /*
    @Test
    public void hasPerson_personWithSameIdentityFieldsInTrackBeau_returnsTrue() {
        trackBeau.addCustomer(ALICE);
        Customer editedAlice = new CustomerBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withStaffs(STAFF_DESC_BOB)
                .build();
        assertTrue(trackBeau.hasCustomer(editedAlice));
    }
    */

    @Test
    public void getCustomerList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> trackBeau.getCustomerList().remove(0));
    }

    /**
     * A stub ReadOnlyTrackBeau whose customer list can violate interface constraints.
     */
    private static class TrackBeauStub implements ReadOnlyTrackBeau {
        private final ObservableList<Customer> customers = FXCollections.observableArrayList();

        TrackBeauStub(Collection<Customer> customers) {
            this.customers.setAll(customers);
        }

        @Override
        public ObservableList<Customer> getCustomerList() {
            return customers;
        }
    }

}
