package seedu.address.model.entry;

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
    public void constructor_invalidTime_throwsIllegalArgumentException() {
        String invalidTime = "";
        assertThrows(IllegalArgumentException.class, () -> new Time(invalidTime));
    }

    @Test
    public void isValidTime() {
        // null
        assertThrows(NullPointerException.class, () -> Time.isValidTime(null));

        // blank time
        assertFalse(Time.isValidTime(""));
        assertFalse(Time.isValidTime(" "));

        // invalid time formats
        assertFalse(Time.isValidTime("Abba"));
        assertFalse(Time.isValidTime("1234"));
        assertFalse(Time.isValidTime("FAAFLJAJL14@$$?LK}!%%K!"));
        assertFalse(Time.isValidTime("FF:AA")); // letters instead of numbers
        assertFalse(Time.isValidTime("@@:LL")); // symbols instead of numbers
        assertFalse(Time.isValidTime("1:23")); // hour part only 1 character long
        assertFalse(Time.isValidTime("12:1")); // minute part only 1 character long
        assertFalse(Time.isValidTime("400:34")); // hour part only 3 characters long
        assertFalse(Time.isValidTime("2:415")); // minute part only 3 characters long

        // invalid times
        assertFalse(Time.isValidTime("24:00")); // hour too high
        assertFalse(Time.isValidTime("13:60")); // minute too high
        assertFalse(Time.isValidTime("08:75")); // minute too high
        assertFalse(Time.isValidTime("41:29")); // hour too high

        // valid times
        assertTrue(Time.isValidTime("08:59")); // highest possible minute value
        assertTrue(Time.isValidTime("16:00")); // lowest possible minute value
        assertTrue(Time.isValidTime("23:36")); // highest possible hour value
        assertTrue(Time.isValidTime("00:14")); // lowest possible hour value
        assertTrue(Time.isValidTime("13:29")); // average time value
    }

}
