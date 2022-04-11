package seedu.address.model.driver;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NameDriverTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new NameDriver(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new NameDriver(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> NameDriver.isValidName(null));

        // invalid name
        assertFalse(NameDriver.isValidName("")); // empty string
        assertFalse(NameDriver.isValidName(" ")); // spaces only
        assertFalse(NameDriver.isValidName("^")); // only non-alphanumeric characters
        assertFalse(NameDriver.isValidName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(NameDriver.isValidName("peter jack")); // alphabets only
        assertTrue(NameDriver.isValidName("12345")); // numbers only
        assertTrue(NameDriver.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(NameDriver.isValidName("Capital Tan")); // with capital letters
        assertTrue(NameDriver.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
    }
}
