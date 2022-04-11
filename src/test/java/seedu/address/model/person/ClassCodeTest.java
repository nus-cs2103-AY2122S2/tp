package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ClassCodeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ClassCode(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidClassCode = "";
        assertThrows(IllegalArgumentException.class, () -> new ClassCode(invalidClassCode));
    }

    @Test
    public void isValidClassCode() {
        // null ClassCode
        assertThrows(NullPointerException.class, () -> ClassCode.isValidClassCode(null));

        // invalid ClassCodes
        assertFalse(ClassCode.isValidClassCode("")); // empty string
        assertFalse(ClassCode.isValidClassCode(" ")); // spaces only
        assertFalse(ClassCode.isValidClassCode("abcde")); // not valid string

        // valid ClassCodes
        assertTrue(ClassCode.isValidClassCode("4A"));
        assertTrue(ClassCode.isValidClassCode("4B"));
    }
}
