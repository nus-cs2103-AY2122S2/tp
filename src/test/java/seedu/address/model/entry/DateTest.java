package seedu.address.model.entry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

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
    public void constructor_todayRelativeDate_success() {
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        Date todayDate = new Date("today");
        Date tomorrowDate = new Date("today 1");

        assertEquals(today, todayDate.getPure());
        assertEquals(tomorrow, tomorrowDate.getPure());
    }

    @Test
    public void isValidDate() {
        // null
        assertThrows(NullPointerException.class, () -> Date.isValidDate(null));

        // blank time
        assertFalse(Date.isValidDate(""));
        assertFalse(Date.isValidDate(" "));

        // invalid date formats
        assertFalse(Date.isValidDate("Abba"));
        assertFalse(Date.isValidDate("1234"));
        assertFalse(Date.isValidDate("FAAFLJAJL14@$$?LK}!%%K!"));
        assertFalse(Date.isValidDate("Dec. 04, 2021")); // MMM. YY, DDDD
        assertFalse(Date.isValidDate("04-20-2021")); // MM-DD-YYYY
        assertFalse(Date.isValidDate("18-01-2019")); // DD-MM-YYYY
        assertFalse(Date.isValidDate("11-06")); // year part missing
        assertFalse(Date.isValidDate("2019-03")); // month/day part missing
        assertFalse(Date.isValidDate("2021-08-1")); // day only 1 character long
        assertFalse(Date.isValidDate("2021-5-19")); // month only 1 character long
        assertFalse(Date.isValidDate("999-06-14")); // year only 3 characters long
        assertFalse(Date.isValidDate("today f")); // second word needs to be anumber

        // invalid dates
        assertFalse(Date.isValidDate("2019-13-01")); // month too high
        assertFalse(Date.isValidDate("2020-00-12")); // month too low
        assertFalse(Date.isValidDate("2021-01-32")); // day too high
        assertFalse(Date.isValidDate("2021-02-30")); // day too high
        assertFalse(Date.isValidDate("2021-04-31")); // day too high
        assertFalse(Date.isValidDate("2017-06-00")); // day too low

        // valid times
        assertTrue(Date.isValidDate("2022-12-20")); // highest possible month value
        assertTrue(Date.isValidDate("2022-01-08")); // lowest possible month value
        assertTrue(Date.isValidDate("2020-05-31")); // highest possible day value
        assertTrue(Date.isValidDate("2018-09-30")); // highest possible day value
        assertTrue(Date.isValidDate("2021-02-28")); // highest possible day value
        assertTrue(Date.isValidDate("2020-02-29")); // highest possible day value
        assertTrue(Date.isValidDate("2019-07-01")); // lowest possible day value
        assertTrue(Date.isValidDate("2015-10-17")); // average year/month/day value
        assertTrue(Date.isValidDate("today"));
        assertTrue(Date.isValidDate("TODAY"));
        assertTrue(Date.isValidDate("tOdAy -1"));
        assertTrue(Date.isValidDate("ToDaY 44"));
    }
}
