package seedu.address.model.tag;

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

        assertThrows(IllegalArgumentException.class, () -> new Tag("CS123S ")); // Need 4 digits

        assertThrows(IllegalArgumentException.class, () -> new Tag("CSCS1231S ")); // Less than 4 letters prefix

        assertThrows(IllegalArgumentException.class, () -> new Tag("C1231S ")); // More than 1 digits prefix
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));

        assertTrue(Tag.isValidTagName("CS1231S"));
        assertTrue(Tag.isValidTagName("GEQ1000"));;
    }

}
