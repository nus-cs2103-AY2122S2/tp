package seedu.address.model.job;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class JobTitleTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new JobTitle(null));
    }

    @Test
    public void constructor_invalidJobTitle_throwsIllegalArgumentException() {
        String invalidJobTitle = "";
        assertThrows(IllegalArgumentException.class, () -> new JobTitle(invalidJobTitle));
    }

    @Test
    public void isValidJobTitle() {
        // null job title
        assertThrows(NullPointerException.class, () -> JobTitle.isValidJobTitle(null));

        // invalid job title
        assertFalse(JobTitle.isValidJobTitle("")); // empty string
        assertFalse(JobTitle.isValidJobTitle(" ")); // spaces only
        assertFalse(JobTitle.isValidJobTitle("^")); // only non-alphanumeric characters
        assertFalse(JobTitle.isValidJobTitle("Admin Assistant*")); // contains non-alphanumeric characters


        // valid job title
        assertTrue(JobTitle.isValidJobTitle("admin")); //one word
        assertTrue(JobTitle.isValidJobTitle("admin assistant")); // two words
        assertTrue(JobTitle.isValidJobTitle("1234")); // only numbers
        assertTrue(JobTitle.isValidJobTitle("Associate 1")); // alphanumeric characters
        assertTrue(JobTitle.isValidJobTitle("Admin Assistant")); // with capital letters
        assertTrue(JobTitle.isValidJobTitle("Software Developer in Testing")); // multiple words
    }
}
