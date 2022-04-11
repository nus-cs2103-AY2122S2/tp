package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EducationTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Education(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidEducation = " ";
        assertThrows(IllegalArgumentException.class, () -> new Education(invalidEducation));
    }

    @Test
    public void isValidTagName() {
        // null education
        assertThrows(NullPointerException.class, () -> Education.isValidTagName(null));

        // invalid education
        assertFalse(Education.isValidTagName("")); // empty string
        assertFalse(Education.isValidTagName(" ")); // spaces only
        assertFalse(Education.isValidTagName("^")); // only non-alphanumeric characters
        assertFalse(Education.isValidTagName("Computer Science*")); // contains non-alphanumeric characters

        // valid education
        assertTrue(Education.isValidTagName("computer science")); // alphabets only
        assertTrue(Education.isValidTagName("12345")); // numbers only
        assertTrue(Education.isValidTagName("computer science 2nd year")); // alphanumeric characters
        assertTrue(Education.isValidTagName("Computer Science")); // with capital letters
        assertTrue(Education.isValidTagName("Computer Science specialising in software engineering")); // long names
    }
}
