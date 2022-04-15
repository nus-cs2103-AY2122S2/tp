package seedu.address.model.task;

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

        //invalid date
        assertFalse(Date.isValidDate("")); // empty string
        assertFalse(Date.isValidDate(" ")); // spaces only
        assertFalse(Date.isValidDate("29-02-2021")); // non-leap year
        assertFalse(Date.isValidDate("28-02-22")); // 2 digits year format
        assertFalse(Date.isValidDate("28-2-2022")); // 1 digit month format
        assertFalse(Date.isValidDate("8-02-2022")); // 1 digit day format
        assertFalse(Date.isValidDate("32-01-2022")); // invalid day range
        assertFalse(Date.isValidDate("04-14-2022")); // invalid month range

        //valid date
        assertTrue(Date.isValidDate("29-02-2020")); // leap year
        assertTrue(Date.isValidDate("20-03-2021")); //valid day, month, and year
    }
}
