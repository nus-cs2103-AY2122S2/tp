package seedu.address.model.customer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NameCustomerTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new NameCustomer(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new NameCustomer(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> NameCustomer.isValidName(null));

        // invalid name
        assertFalse(NameCustomer.isValidName("")); // empty string
        assertFalse(NameCustomer.isValidName(" ")); // spaces only
        assertFalse(NameCustomer.isValidName("^")); // only non-alphanumeric characters
        assertFalse(NameCustomer.isValidName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(NameCustomer.isValidName("peter jack")); // alphabets only
        assertTrue(NameCustomer.isValidName("12345")); // numbers only
        assertTrue(NameCustomer.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(NameCustomer.isValidName("Capital Tan")); // with capital letters
        assertTrue(NameCustomer.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
    }
}
