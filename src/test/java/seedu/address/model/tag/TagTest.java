package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagTest {
    private final Tag t1 = new Tag("Western");
    private final Tag t2 = new Tag("Korean");

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
    public void toString_test() {
        assertEquals(t1.toString(), "[Western]");
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));
    }

    @Test
    public void equals_test() {
        Tag t1Copy = new Tag(t1.tagName);

        assertNotEquals(t1, t1.tagName);
        assertEquals(t1, t1);
        assertEquals(t1, t1Copy);
        assertNotEquals(t1, t2);
    }

    @Test
    public void hashCode_test() {
        Tag t1Copy = new Tag(t1.tagName);

        assertEquals(t1.hashCode(), t1Copy.hashCode());
        assertNotEquals(t1.hashCode(), t2.hashCode());
    }

}
