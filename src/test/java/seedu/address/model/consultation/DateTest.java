package seedu.address.model.consultation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Date(null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDate));
    }

    @Test
    public void isValidDate() {
        // null date
        assertThrows(NullPointerException.class, () -> Date.isValidDate(null));

        // invalid date
        assertFalse(Date.isValidDate("")); // empty string
        assertFalse(Date.isValidDate(" ")); // space only
        assertFalse(Date.isValidDate("Lorem Ipsum")); // non-date string
        assertFalse(Date.isValidDate("20-09-2020")); // wrong format
        assertFalse(Date.isValidDate("31-02-2020")); // does not exist
        assertTrue(Date.isValidDate("2021-02-29")); // not leap year

        // valid date
        assertTrue(Date.isValidDate("2020-09-19"));
        assertTrue(Date.isValidDate("2020-02-29")); // leap year
    }
}
