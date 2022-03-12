package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Name(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> Name.isValid(null));

        // invalid name
        assertFalse(Name.isValid("")); // empty string
        assertFalse(Name.isValid(" ")); // spaces only
        assertFalse(Name.isValid("^")); // only non-alphanumeric characters
        assertFalse(Name.isValid("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Name.isValid("peter jack")); // alphabets only
        assertTrue(Name.isValid("12345")); // numbers only
        assertTrue(Name.isValid("peter the 2nd")); // alphanumeric characters
        assertTrue(Name.isValid("Capital Tan")); // with capital letters
        assertTrue(Name.isValid("David Roger Jackson Ray Jr 2nd")); // long names
    }
}
