package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class JobTitleTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new JobTitle(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalid = "";
        assertThrows(IllegalArgumentException.class, () -> new JobTitle(invalid));
    }

    @Test
    public void isValidJobTitle() {
        //Job title should start with an alphabet, can take any alphanumeric values with spaces,
        // and it should not be blank

        // null
        assertThrows(NullPointerException.class, () -> JobTitle.isValidJobTitle(null));

        // invalid titles
        assertFalse(JobTitle.isValidJobTitle("")); // empty string
        assertFalse(JobTitle.isValidJobTitle(" ")); // spaces only
        assertFalse(JobTitle.isValidJobTitle("2chef")); //starts with a non-alphabet
        assertFalse(JobTitle.isValidJobTitle("ch@f")); //non-alphanumeric character
        assertFalse(JobTitle.isValidJobTitle(" chef")); //first character blank
        assertFalse(JobTitle.isValidJobTitle("Chief Executive Officer (CEO)")); // non-alphanumeric character

        // valid titles
        assertTrue(JobTitle.isValidJobTitle("peter jack")); // alphabets only
        assertTrue(JobTitle.isValidJobTitle("chef12345")); // alphanumeric with no space
        assertTrue(JobTitle.isValidJobTitle("peter the 2nd")); // alphanumeric with space
        assertTrue(JobTitle.isValidJobTitle("Capital Tan")); // with capital letters
        assertTrue(JobTitle.isValidJobTitle("David Roger Jackson Ray Jr 2nd")); // long titles

    }
}
