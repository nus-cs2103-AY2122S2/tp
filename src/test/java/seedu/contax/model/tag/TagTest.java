package seedu.contax.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.contax.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag((String) null));
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
    }

    @Test
    public void isSameTag() {
        Tag tag1 = new Tag("test");
        Tag tag2 = new Tag("different");

        assertTrue(tag1.isSameTag(tag1));
        assertTrue(tag1.isSameTag(new Tag("test")));

        assertFalse(tag1.isSameTag(tag2));
        assertFalse(tag1.isSameTag(null));
    }

    @Test
    public void equals() {
        Tag tag1 = new Tag("test");
        Tag tag2 = new Tag("different");

        assertTrue(tag1.equals(tag1));
        assertTrue(tag1.equals(new Tag("test")));

        assertFalse(tag1.equals(tag2));
        assertFalse(tag1.equals(null));
    }

}
