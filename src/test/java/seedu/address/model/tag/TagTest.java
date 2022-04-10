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
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));

        // tags with more than 25 characters
        assertFalse(Tag.isValidTagName("1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ"));

        // tags with 25 characters
        assertTrue(Tag.isValidTagName("1234567890123456789012345"));

        // tag with special characters
        assertFalse(Tag.isValidTagName("Abc*"));

        // valid tags
        assertTrue(Tag.isValidTagName("ABCDEF")); // letters only
        assertTrue(Tag.isValidTagName("124632")); // numbers only
        assertTrue(Tag.isValidTagName("ABCE124a")); // alphanumeric
    }

}
