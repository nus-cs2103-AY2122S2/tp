package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
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
    public void exactEquals() {

        Address validAddressAmy = new Address(VALID_ADDRESS_AMY);

        // same object -> returns true
        assertTrue(validAddressAmy.exactEquals(validAddressAmy));

        // same values -> returns true
        Address validAddressAmyCopy = new Address(VALID_ADDRESS_AMY);
        assertTrue(validAddressAmy.exactEquals(validAddressAmyCopy));

        // different capitalization -> returns false
        Address validAddressAmyAllCaps = new Address(VALID_ADDRESS_AMY.toUpperCase());
        assertFalse(validAddressAmy.exactEquals(validAddressAmyAllCaps));

        // null -> returns false
        assertFalse(validAddressAmy.exactEquals(null));

        // different address -> returns false
        Address validAddressBob = new Address(VALID_ADDRESS_BOB);
        assertFalse(validAddressAmy.exactEquals(validAddressBob));
    }

    @Test
    public void equals() {

        Address validAddressAmy = new Address(VALID_ADDRESS_AMY);
        Address validAddressBob = new Address(VALID_ADDRESS_BOB);

        // same object -> returns true
        assertTrue(validAddressAmy.equals(validAddressAmy));

        // same values -> returns true
        Address validAddressAmyCopy = new Address(VALID_ADDRESS_AMY);
        assertTrue(validAddressAmy.equals(validAddressAmyCopy));

        // different capitalization -> returns true
        Address validAddressAmyAllCaps = new Address(VALID_ADDRESS_AMY.toUpperCase());
        assertTrue(validAddressAmy.equals(validAddressAmyAllCaps));

        // different types -> returns false
        assertFalse(validAddressAmy.equals(1));

        // null -> returns false
        assertFalse(validAddressAmy.equals(null));

        // different address -> returns false
        assertFalse(validAddressAmy.equals(validAddressBob));
    }

}
