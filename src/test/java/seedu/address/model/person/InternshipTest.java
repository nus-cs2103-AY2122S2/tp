package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class InternshipTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Internship(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidInternship = " ";
        assertThrows(IllegalArgumentException.class, () -> new Internship(invalidInternship));
    }

    @Test
    public void isValidTagName() {
        // null internship
        assertThrows(NullPointerException.class, () -> Internship.isValidTagName(null));

        // invalid internship
        assertFalse(Internship.isValidTagName("")); // empty string
        assertFalse(Internship.isValidTagName(" ")); // spaces only
        assertFalse(Internship.isValidTagName("^")); // only non-alphanumeric characters
        assertFalse(Internship.isValidTagName("bytedance*")); // contains non-alphanumeric characters

        // valid internship
        assertTrue(Internship.isValidTagName("bytedance")); // alphabets only
        assertTrue(Internship.isValidTagName("12345")); // numbers only
        assertTrue(Internship.isValidTagName("byt3danc3")); // alphanumeric characters
        assertTrue(Internship.isValidTagName("ByteDance")); // with capital letters
        assertTrue(Internship.isValidTagName("Oversea Chinese Banking Corporation")); // long names
    }
}
