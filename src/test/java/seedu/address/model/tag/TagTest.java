package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStrings.LONG_STRING_51_CHAR;

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


        // invalid tag name
        assertFalse(Tag.isValidTagName("ai machine learning")); // contains spaces
        assertFalse(Tag.isValidTagName(LONG_STRING_51_CHAR)); // 51 characters

        // valid tag name
        assertTrue(Tag.isValidTagName("tutorials")); // alphabets only
        assertTrue(Tag.isValidTagName("12345")); // numbers only
        assertTrue(Tag.isValidTagName("cs3240")); // alphanumeric characters
        assertTrue(Tag.isValidTagName("Cs3240")); // with capital letters
        assertTrue(Tag.isValidTagName("ExtraordinaryMachineLearningAndArtificialIntellige")); // 50 characters
    }

}
