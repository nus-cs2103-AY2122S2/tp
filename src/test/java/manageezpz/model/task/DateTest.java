package manageezpz.model.task;

import static manageezpz.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Date(null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate1 = "2022-18-09";
        String invalidDate2 = "2022-11-60";
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDate1));
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDate2));
    }

    @Test
    public void isValidDate() {
        // null date
        assertThrows(NullPointerException.class, () -> Date.isValidDate(null));

        // invalid date
        assertFalse(Date.isValidDate("2022-25-90")); //both month and date wrong
        assertFalse(Date.isValidDate("2022-25-02")); //month is wrong
        assertFalse(Date.isValidDate("2022-02-99")); //day is wrong
        assertFalse(Date.isValidDate("")); //empty String given
        assertFalse(Date.isValidDate("1254055")); //random numbers
        assertFalse(Date.isValidDate("2022/02/20")); // "/" used instead of "-"
        assertFalse(Date.isValidDate("!@##$#%^&")); //empty String given

        // valid date
        assertTrue(Date.isValidDate("2022-05-09"));
        assertTrue(Date.isValidDate("2024-05-09"));
    }

    @Test
    public void testGetters() {
        Date testDate = new Date(LocalDate.now().toString());
        assertEquals(testDate, Date.getTodayDate());
    }
}
