package seedu.address.model.driver;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


public class PhoneDriverTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PhoneDriver(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new PhoneDriver(invalidPhone));
    }

    @Test
    public void isValidPhone() {
        // null phone number
        assertThrows(NullPointerException.class, () -> PhoneDriver.isValidPhone(null));

        // invalid phone numbers
        assertFalse(PhoneDriver.isValidPhone("")); // empty string
        assertFalse(PhoneDriver.isValidPhone(" ")); // spaces only
        assertFalse(PhoneDriver.isValidPhone("91")); // less than 3 numbers
        assertFalse(PhoneDriver.isValidPhone("phone")); // non-numeric
        assertFalse(PhoneDriver.isValidPhone("9011p041")); // alphabets within digits
        assertFalse(PhoneDriver.isValidPhone("9312 1534")); // spaces within digits

        // valid phone numbers
        assertTrue(PhoneDriver.isValidPhone("91123456")); // exactly 3 numbers
        assertTrue(PhoneDriver.isValidPhone("93121534"));
        assertTrue(PhoneDriver.isValidPhone("82324354")); // long phone numbers
    }
}

