package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AddressTest {


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
    public void isEqual() {
        Address addressOne = new Address("Kings Road");
        Address addressTwo = new Address("Queens Drive");
        Address addressThree = new Address(null);

        //same object
        assertTrue(addressOne.equals(addressOne));

        //different objects same values
        assertTrue(addressOne.equals(new Address("Kings Road")));

        //different objects different values
        assertFalse(addressOne.equals(addressTwo));

        //null value
        assertFalse(addressOne.equals(addressThree));
        assertTrue(addressThree.equals(new Address(null)));

        //different types
        assertFalse(addressOne.equals(1));
    }
}
