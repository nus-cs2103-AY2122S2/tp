package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class BirthdayTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Birthday(null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new Birthday(invalidDate));
    }

    @Test
    public void isValidBirthday() {
        // null dates
        assertThrows(NullPointerException.class, () -> Birthday.isValidBirthday(null));
        assertThrows(NullPointerException.class, () -> Birthday.isPastBirthday((null)));

        // invalid dates
        assertFalse(Birthday.isValidBirthday("")); // empty string
        assertFalse(Birthday.isValidBirthday(" ")); // spaces only
        assertFalse(Birthday.isValidBirthday("00-00-0000")); // all zeros
        assertFalse(Birthday.isValidBirthday("31-04-2021")); // non-existent date
        assertFalse(Birthday.isValidBirthday("29-02-2022")); // non-existent leap date
        assertFalse(Birthday.isPastBirthday("21-03-2103")); // future date

        // valid date time
        assertTrue(Birthday.isValidBirthday("21-03-2022"));
        assertTrue(Birthday.isValidBirthday("29-02-2020")); // existent leap date
    }
}
