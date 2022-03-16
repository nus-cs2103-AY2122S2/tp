package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SizeTest {

    @Test
    public void fromString_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Size.fromString(null));
    }

    @Test
    public void fromString_invalidSize_throwsIllegalArgumentException() {
        String invalidSize = "";
        assertThrows(IllegalArgumentException.class, () -> Size.fromString(invalidSize));
    }

    @Test
    public void isValidSize() {
        // null size
        assertThrows(NullPointerException.class, () -> Size.isValidSize(null));

        // invalid sizes
        assertFalse(Size.isValidSize("")); // empty string
        assertFalse(Size.isValidSize(" ")); // spaces only
        assertFalse(Size.isValidSize(" 1-room")); // leading spaces
        assertFalse(Size.isValidSize("1-room ")); // trailing spaces
        assertFalse(Size.isValidSize("1room")); // missing hyphen
        assertFalse(Size.isValidSize("6-room")); // not an allowed size

        // valid sizes (all lower case)
        assertTrue(Size.isValidSize("1-room"));
        assertTrue(Size.isValidSize("2-room"));
        assertTrue(Size.isValidSize("3-room"));
        assertTrue(Size.isValidSize("4-room"));
        assertTrue(Size.isValidSize("5-room"));

        // valid sizes (all upper case)
        assertTrue(Size.isValidSize("1-ROOM"));
        assertTrue(Size.isValidSize("2-ROOM"));
        assertTrue(Size.isValidSize("3-ROOM"));
        assertTrue(Size.isValidSize("4-ROOM"));
        assertTrue(Size.isValidSize("5-ROOM"));

        // valid sizes (first letter capitalized)
        assertTrue(Size.isValidSize("1-Room"));
        assertTrue(Size.isValidSize("2-Room"));
        assertTrue(Size.isValidSize("3-Room"));
        assertTrue(Size.isValidSize("4-Room"));
        assertTrue(Size.isValidSize("5-Room"));

        // valid sizes (mixed case)
        assertTrue(Size.isValidSize("1-RoOm"));
        assertTrue(Size.isValidSize("2-RoOm"));
        assertTrue(Size.isValidSize("3-RoOm"));
        assertTrue(Size.isValidSize("4-RoOm"));
        assertTrue(Size.isValidSize("5-RoOm"));
    }
}
