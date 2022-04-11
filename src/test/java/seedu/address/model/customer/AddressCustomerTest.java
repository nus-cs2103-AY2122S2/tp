package seedu.address.model.customer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AddressCustomerTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddressCustomer(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new AddressCustomer(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> AddressCustomer.isValidAddress(null));

        // invalid addresses
        assertFalse(AddressCustomer.isValidAddress("")); // empty string
        assertFalse(AddressCustomer.isValidAddress(" ")); // spaces only

        // valid addresses
        assertTrue(AddressCustomer.isValidAddress("Blk 456, Den Road, #01-355"));
        assertTrue(AddressCustomer.isValidAddress("-")); // one character
        assertTrue(AddressCustomer.isValidAddress("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long
        // address
    }
}
