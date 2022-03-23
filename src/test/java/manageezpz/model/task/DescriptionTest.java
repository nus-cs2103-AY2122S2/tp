package manageezpz.model.task;

import static manageezpz.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Description(null));
    }

    @Test
    public void constructor_invalidDescription_throwsIllegalArgumentException() {
        String invalidDescription = "";
        assertThrows(IllegalArgumentException.class, () -> new Description(invalidDescription));
    }

    @Test
    public void isValidDescription() {
        // null description
        assertThrows(NullPointerException.class, () -> Description.isValidDescription(null));

        // invalid description
        assertFalse(Description.isValidDescription("")); // empty string
        assertFalse(Description.isValidDescription(" ")); // spaces only

        // valid description
        assertTrue(Description.isValidDescription("read book")); // alphabets only
        assertTrue(Description.isValidDescription("12345")); // numbers only
        assertTrue(Description.isValidDescription("read 3 books")); // alphanumeric characters
        assertTrue(Description.isValidDescription("Go Run")); // with capital letters
        assertTrue(Description.isValidDescription("Read 50 books in the library non stop")); // long descriptions
        assertTrue(Description.isValidDescription("^!@#$%%^%&^*&(*&(")); // only non-alphanumeric characters
        assertTrue(Description.isValidDescription("peter*")); // contains non-alphanumeric characters
    }
}
