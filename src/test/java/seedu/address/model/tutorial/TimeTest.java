package seedu.address.model.tutorial;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TimeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Time(null));
    }

    @Test
    public void constructor_invalidDay_throwsIllegalArgumentException() {
        String invalidTime = "";
        assertThrows(IllegalArgumentException.class, () -> new Time(invalidTime));
    }

    @Test
    public void isValidTime() {
        // null times
        assertThrows(NullPointerException.class, () -> Time.isValidTime(null));

        // invalid times
        assertFalse(Time.isValidTime("")); // empty string
        assertFalse(Time.isValidTime(" ")); // spaces only
        assertFalse(Time.isValidTime("24:59")); // hour value above 23
        assertFalse(Time.isValidTime("23:60")); // minute value above 59

        // valid times
        assertTrue(Time.isValidTime("11:59")); // 11:59 am
        assertTrue(Time.isValidTime("23:59")); // 11:59 pm
        assertTrue(Time.isValidTime("00:00")); // 12 am
    }
}
