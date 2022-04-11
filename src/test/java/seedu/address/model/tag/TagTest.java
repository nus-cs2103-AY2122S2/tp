package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagTest {

    @Test
    public void constructor_nullTagName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null, Priority.PRIORITY_1));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName, null));
    }

    @Test
    public void isValidTagName() {
        // invalid tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));
        assertFalse(Tag.isValidTagName("^")); // only non-alphanumeric characters
        assertFalse(Tag.isValidTagName("find out later*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Tag.isValidTagName("peter jack")); // alphabets only
        assertTrue(Tag.isValidTagName("12345")); // numbers only
        assertTrue(Tag.isValidTagName("peter2")); // alphanumeric characters
        assertTrue(Tag.isValidTagName("friends and family 2")); // alphanumeric characters and spaces
    }

}
