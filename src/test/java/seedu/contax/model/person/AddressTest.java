package seedu.contax.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.contax.testutil.Assert.assertThrows;

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
        assertThrows(NullPointerException.class, () -> Address.isValidAddress(null));

        // invalid addresses
        assertFalse(Address.isValidAddress("")); // empty string
        assertFalse(Address.isValidAddress(" ")); // spaces only

        // valid addresses
        assertTrue(Address.isValidAddress("Blk 456, Den Road, #01-355"));
        assertTrue(Address.isValidAddress("-")); // one character
        assertTrue(Address.isValidAddress("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long address
    }

    @Test
    public void equals() {
        Address address1 = new Address("13 Computing Drive Singapore 117417");
        Address address2 = new Address("Blk 456, Den Road, #01-355");

        assertTrue(address1.equals(address1));
        assertTrue(address1.equals(new Address("13 Computing Drive Singapore 117417")));

        assertFalse(address1.equals(address2));
        assertFalse(address1.equals(null));
        assertFalse(address1.equals(0));
    }
}
