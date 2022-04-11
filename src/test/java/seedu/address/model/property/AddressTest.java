package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class AddressTest {

    @Test
    void testEquals() {
        Address a1 = new Address("block 123");
        Address a2 = new Address("block 123");
        Address a3 = new Address("BLOCK 123");

        assertTrue(a1.equals(a2));

        assertFalse(a1.equals(a3));
    }

    @Test
    void isValidAddress() {
        assertTrue(Address.isValidAddress("gibber"));
        assertTrue(Address.isValidAddress("2321321"));
        assertTrue(Address.isValidAddress("       "));

        assertFalse(Address.isValidAddress(""));
    }
}
