package seedu.trackermon.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.trackermon.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Contains unit tests for {@code Tag}.
 */
public class TagTest {

    /**
     * Tests constructor of {@code Tag} in the event of null input.
     */
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    /**
     * Tests constructor of {@code Name} in the event of invalid input.
     */
    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        String invalidTagName21Characters = "dasdasdasdasdasdarfqq";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName));
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName21Characters));
    }

    /**
     * Tests isValidName method of {@code Name}.
     */
    @Test
    public void isValidTagName() {
        String validTagName = "horror";
        String invalidTagName = "";
        String invalidTagName21Characters = "dasdasdasdasdasdarfqq";
        assertFalse(Tag.isValidTagName(invalidTagName)); // empty string
        assertFalse(Tag.isValidTagName(invalidTagName21Characters)); // exceed limit
        assertTrue(Tag.isValidTagName(validTagName)); //valid string
    }

}
