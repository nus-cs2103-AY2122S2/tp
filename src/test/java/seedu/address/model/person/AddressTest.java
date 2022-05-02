package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AddressTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Address(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new Address(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> Address.isValid(null));

        // invalid addresses
        assertFalse(Address.isValid("")); // empty string
        assertFalse(Address.isValid(" ")); // spaces only

        // valid addresses
        assertTrue(Address.isValid("Blk 456, Den Road, #01-355"));
        assertTrue(Address.isValid("-")); // one character
        assertTrue(Address.isValid("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long address
    }
}
