package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AgeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Age(null));
    }

    @Test
    public void constructor_invalidAge_throwsIllegalArgumentException() {
        String invalidAge = "";
        assertThrows(IllegalArgumentException.class, () -> new Age(invalidAge));
    }

    @Test
    public void isValidAge() {
        // null age
        assertThrows(NullPointerException.class, () -> Age.isValidAge(null));

        // invalid ages
        assertFalse(Age.isValidAge("")); // empty string
        assertFalse(Age.isValidAge(" ")); // spaces only
        assertFalse(Age.isValidAge("$%#")); // symbols
        assertFalse(Age.isValidAge("a24gsd")); // alpha numeric
        assertFalse(Age.isValidAge("-124")); // negative age
        assertFalse(Age.isValidAge("101")); // age greater than 100
        assertFalse(Age.isValidAge("0")); // age lesser than 1
        assertFalse(Age.isValidAge("21.2")); // decimal age

        // valid ages
        assertTrue(Age.isValidAge("12")); // between 1 and 100
        assertTrue(Age.isValidAge("100")); // inclusive 100
        assertTrue(Age.isValidAge("1")); // inclusive 1
    }
}
