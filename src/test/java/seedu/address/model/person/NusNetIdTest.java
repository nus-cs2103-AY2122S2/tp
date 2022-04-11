package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NusNetIdTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new NusNetId(null));
    }

    @Test
    public void constructor_invalidNusNetId_throwsIllegalArgumentException() {
        String invalidNusNetId = "";
        assertThrows(IllegalArgumentException.class, () -> new NusNetId(invalidNusNetId));
    }

    @Test
    public void isValidId() {

        // null NusNetId
        assertThrows(NullPointerException.class, () -> NusNetId.isValidId(null));

        // invalid NusNetId
        assertFalse(NusNetId.isValidId("")); // empty string
        assertFalse(NusNetId.isValidId(" ")); // spaces only
        assertFalse(NusNetId.isValidId("a")); // single character
        assertFalse(NusNetId.isValidId("abcdefgh")); // all alphabets
        assertFalse(NusNetId.isValidId("12345678")); // all digits
        assertFalse(NusNetId.isValidId("e012345")); // insufficient characters
        assertFalse(NusNetId.isValidId("a0123456")); // wrong starting character

        // valid NusNetId
        assertTrue(NusNetId.isValidId("e0000000")); // valid format
        assertTrue(NusNetId.isValidId("e9999999")); // valid format

    }
}
