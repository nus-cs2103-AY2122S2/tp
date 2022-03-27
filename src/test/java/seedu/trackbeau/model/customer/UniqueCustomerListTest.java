package seedu.trackbeau.model.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_STAFF_BOB;
import static seedu.trackbeau.testutil.Assert.assertThrows;
import static seedu.trackbeau.testutil.TypicalCustomers.ALICE;
import static seedu.trackbeau.testutil.TypicalCustomers.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.trackbeau.model.uniquelist.UniqueList;
import seedu.trackbeau.model.uniquelist.exceptions.DuplicateItemException;
import seedu.trackbeau.model.uniquelist.exceptions.ItemNotFoundException;
import seedu.trackbeau.testutil.CustomerBuilder;

public class UniqueCustomerListTest {

    private final UniqueList<Customer> uniqueCustomerList = new UniqueList<>();

    @Test
    public void contains_nullCustomer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCustomerList.contains(null));
    }

    @Test
    public void contains_customerNotInList_returnsFalse() {
        assertFalse(uniqueCustomerList.contains(ALICE));
    }

    @Test
    public void contains_customerInList_returnsTrue() {
        uniqueCustomerList.add(ALICE);
        assertTrue(uniqueCustomerList.contains(ALICE));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniqueCustomerList.add(ALICE);
        Customer editedAlice = new CustomerBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withStaffs(VALID_STAFF_BOB)
                .build();
        assertTrue(uniqueCustomerList.contains(editedAlice));
    }

    @Test
    public void add_nullCustomer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCustomerList.add(null));
    }

    @Test
    public void add_duplicateCustomer_throwsDuplicateCustomerException() {
        uniqueCustomerList.add(ALICE);
        assertThrows(DuplicateItemException.class, () -> uniqueCustomerList.add(ALICE));
    }

    @Test
    public void setCustomer_nullTargetCustomer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCustomerList.setItem(null, ALICE));
    }

    @Test
    public void setCustomer_nullEditedCustomer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCustomerList.setItem(ALICE, null));
    }

    @Test
    public void setCustomer_targetCustomerNotInList_throwsCustomerNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> uniqueCustomerList.setItem(ALICE, ALICE));
    }

    @Test
    public void setCustomer_editedCustomerIsSameCustomer_success() {
        uniqueCustomerList.add(ALICE);
        uniqueCustomerList.setItem(ALICE, ALICE);
        UniqueList<Customer> expectedUniqueCustomerList = new UniqueList<>();
        expectedUniqueCustomerList.add(ALICE);
        assertEquals(expectedUniqueCustomerList, uniqueCustomerList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        uniqueCustomerList.add(ALICE);
        Customer editedAlice = new CustomerBuilder(ALICE).withAddress(VALID_ADDRESS_BOB)
                .withStaffs(VALID_STAFF_BOB)
                .build();
        uniqueCustomerList.setItem(ALICE, editedAlice);
        UniqueList<Customer> expectedUniqueCustomerList = new UniqueList<>();
        expectedUniqueCustomerList.add(editedAlice);
        assertEquals(expectedUniqueCustomerList, uniqueCustomerList);
    }

    @Test
    public void setCustomer_editedCustomerHasDifferentIdentity_success() {
        uniqueCustomerList.add(ALICE);
        uniqueCustomerList.setItem(ALICE, BOB);
        UniqueList<Customer> expectedUniqueCustomerList = new UniqueList<>();
        expectedUniqueCustomerList.add(BOB);
        assertEquals(expectedUniqueCustomerList, uniqueCustomerList);
    }

    @Test
    public void setCustomer_editedCustomerHasNonUniqueIdentity_throwsDuplicateCustomerException() {
        uniqueCustomerList.add(ALICE);
        uniqueCustomerList.add(BOB);
        assertThrows(DuplicateItemException.class, () -> uniqueCustomerList.setItem(ALICE, BOB));
    }

    @Test
    public void remove_nullCustomer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCustomerList.remove(null));
    }

    @Test
    public void remove_customerDoesNotExist_throwsCustomerNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> uniqueCustomerList.remove(ALICE));
    }

    @Test
    public void remove_existingCustomer_removesCustomer() {
        uniqueCustomerList.add(ALICE);
        uniqueCustomerList.remove(ALICE);
        UniqueList<Customer> expectedUniqueCustomerList = new UniqueList<>();
        assertEquals(expectedUniqueCustomerList, uniqueCustomerList);
    }

    @Test
    public void setCustomers_nullUniqueCustomerList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCustomerList.setItems((UniqueList<Customer>) null));
    }

    @Test
    public void setCustomers_uniqueCustomerList_replacesOwnListWithProvidedUniqueCustomerList() {
        uniqueCustomerList.add(ALICE);
        UniqueList<Customer> expectedUniqueCustomerList = new UniqueList<>();
        expectedUniqueCustomerList.add(BOB);
        uniqueCustomerList.setItems(expectedUniqueCustomerList);
        assertEquals(expectedUniqueCustomerList, uniqueCustomerList);
    }

    @Test
    public void setCustomers_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCustomerList.setItems((List<Customer>) null));
    }

    @Test
    public void setCustomers_list_replacesOwnListWithProvidedList() {
        uniqueCustomerList.add(ALICE);
        List<Customer> customerList = Collections.singletonList(BOB);
        uniqueCustomerList.setItems(customerList);
        UniqueList<Customer> expectedUniqueCustomerList = new UniqueList<>();
        expectedUniqueCustomerList.add(BOB);
        assertEquals(expectedUniqueCustomerList, uniqueCustomerList);
    }

    @Test
    public void setCustomers_listWithDuplicateCustomers_throwsDuplicateCustomerException() {
        List<Customer> listWithDuplicateCustomers = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateItemException.class, () ->
                uniqueCustomerList.setItems(listWithDuplicateCustomers));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueCustomerList.asUnmodifiableObservableList().remove(0));
    }
}
