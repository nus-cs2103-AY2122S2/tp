package seedu.address.model.customer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PhoneCustomerTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PhoneCustomer(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new PhoneCustomer(invalidPhone));
    }

    @Test
    public void isValidPhone() {
        // null phone number
        assertThrows(NullPointerException.class, () -> PhoneCustomer.isValidPhone(null));

        // invalid phone numbers
        assertFalse(PhoneCustomer.isValidPhone("")); // empty string
        assertFalse(PhoneCustomer.isValidPhone(" ")); // spaces only
        assertFalse(PhoneCustomer.isValidPhone("91")); // less than 3 numbers
        assertFalse(PhoneCustomer.isValidPhone("phone")); // non-numeric
        assertFalse(PhoneCustomer.isValidPhone("9011p041")); // alphabets within digits
        assertFalse(PhoneCustomer.isValidPhone("9312 1534")); // spaces within digits

        // valid phone numbers
        assertTrue(PhoneCustomer.isValidPhone("91123456")); // exactly 3 numbers
        assertTrue(PhoneCustomer.isValidPhone("93121534"));
        assertTrue(PhoneCustomer.isValidPhone("82324354")); // long phone numbers
    }
}
