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
        String invalidTagNameEmpty = "";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagNameEmpty));
        String invalidTagNameInvalidRegex = "!";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagNameInvalidRegex));
    }

    @Test
    public void constructor_invalidTagLength_throwsIllegalArgumentException() {
        String invalidTagName = "";
        for (int i = 0; i < Tag.MAX_CHARACTER_LENGTH + 1; i++) {
            invalidTagName += 'a';
        }
        final String invalidTagLength = invalidTagName;
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagLength));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));

        // invalid name
        assertFalse(Tag.isValidTagName(("peter_jackson"))); // contains non-alphanumeric characters
        assertFalse(Tag.isValidTagName(("*"))); // contains non-alphanumeric characters
        assertFalse(Tag.isValidTagName(("melvin@melvin.com"))); // contains non-alphanumeric characters
        assertFalse(Tag.isValidTagName(("what?"))); // contains non-alphanumeric characters
        assertFalse(Tag.isValidTagName("")); // empty string
        assertFalse(Tag.isValidTagName(("^"))); // only non-alphanumeric characters

        // valid name
        assertTrue(Tag.isValidTagName((" "))); // spaces only
        assertTrue(Tag.isValidTagName(("  peter jack  "))); // alphabets only
        assertTrue(Tag.isValidTagName(("12345"))); // numbers only
        assertTrue(Tag.isValidTagName(("peter the 2nd"))); // alphanumeric characters
        assertTrue(Tag.isValidTagName(("Capital Tan"))); // with capital letters
        assertTrue(Tag.isValidTagName(("David Roger Jackson Ray Jr 2nd"))); // long names
    }

    @Test
    public void isValidTagLength() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagLength(null));

        String validTagWithOneLessThanMaxCharacters = "";
        for (int i = 0; i < Tag.MAX_CHARACTER_LENGTH - 1; i++) {
            validTagWithOneLessThanMaxCharacters += "a";
        }

        String validTagWithEqualsMaxCharacters = "";
        for (int i = 0; i < Tag.MAX_CHARACTER_LENGTH; i++) {
            validTagWithEqualsMaxCharacters += "a";
        }

        String invalidTagWithOneMoreThanMaxCharacters = "";
        for (int i = 0; i < Tag.MAX_CHARACTER_LENGTH + 1; i++) {
            invalidTagWithOneMoreThanMaxCharacters += "a";
        }

        assertTrue(Tag.isValidTagLength(validTagWithOneLessThanMaxCharacters));
        assertTrue(Tag.isValidTagLength(validTagWithEqualsMaxCharacters));
        assertFalse(Tag.isValidTagLength(invalidTagWithOneMoreThanMaxCharacters));
    }
}
