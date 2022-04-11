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

        // PGG tag name
        assertFalse(Tag.isValidTagName("PGG"));
        // PG tag name
        assertTrue(Tag.isValidTagName("PG"));
        // pg tag name
        assertFalse(Tag.isValidTagName("pg"));

        // SGG tag name
        assertFalse(Tag.isValidTagName("SGG"));
        // SG tag name
        assertTrue(Tag.isValidTagName("SG"));
        // sg tag name
        assertFalse(Tag.isValidTagName("sg"));

        // SFF tag name
        assertFalse(Tag.isValidTagName("SFF"));
        // SF tag name
        assertTrue(Tag.isValidTagName("SF"));
        // sf tag name
        assertFalse(Tag.isValidTagName("sf"));

        // PFF tag name
        assertFalse(Tag.isValidTagName("PFF"));
        // PF tag name
        assertTrue(Tag.isValidTagName("PF"));
        // pf tag name
        assertFalse(Tag.isValidTagName("pf"));

        // CC tag name
        assertFalse(Tag.isValidTagName("CC"));
        // C tag name
        assertTrue(Tag.isValidTagName("C"));
        // c tag name
        assertFalse(Tag.isValidTagName("c"));
    }

}
