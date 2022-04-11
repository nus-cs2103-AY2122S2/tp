package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName));
    }

    @Test
    public void isValidTagName() {
        assertTrue(Tag.isValidTagName("alpha"));
        assertTrue(Tag.isValidTagName("alpha123"));
        assertTrue(Tag.isValidTagName("abcedfghijklmnopqrstuvwxyzABCD")); // 30 characters
        assertTrue(Tag.isValidTagName("abcedfghi123mnopqrstuvwxyzABCD")); // 30 alphanumeric characters
        assertTrue(Tag.isValidTagName("abcedfghi123mnoqrstuvwxyzABCD")); // 30 alphanumeric characters w space

    }

    @Test
    public void isInvalidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));
        assertFalse(Tag.isValidTagName("")); // empty tag
        assertFalse(Tag.isValidTagName(" ")); // tag contains only whitespace
        assertFalse(Tag.isValidTagName("a b")); // whitespace between chars
        assertFalse(Tag.isValidTagName("1 2")); // whitespace between nums
        assertFalse(Tag.isValidTagName("1 b")); // whitespace between num and char
        assertFalse(Tag.isValidTagName("abcedfghijklmnopqrstuvwxyzABCDE")); // 31 characters
        assertFalse(Tag.isValidTagName("abcedfghijklmnop12stuvwxyzABCDE")); // 31 alphanumeric characters
        assertFalse(Tag.isValidTagName("abcedfghijklmnop12stuvwxyzABCDEFGHJIAS"
                + "DHASIJDHAS21371289SN")); // 58 alphanumeric characters
    }

}
