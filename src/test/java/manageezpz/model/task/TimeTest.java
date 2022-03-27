package manageezpz.model.task;

import static manageezpz.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Time(null));
    }

    @Test
    public void constructor_invalidTime_throwsIllegalArgumentException() {
        String invalidTime1 = "9999";
        String invalidTime2 = "2459";
        assertThrows(IllegalArgumentException.class, () -> new Time(invalidTime1));
        assertThrows(IllegalArgumentException.class, () -> new Time(invalidTime2));
    }

    @Test
    public void isValidTime() {
        // null date
        assertThrows(NullPointerException.class, () -> Time.isValidTime(null));

        // invalid Time
        assertFalse(Time.isValidTime("6500")); //greater than 24hr format
        assertFalse(Time.isValidTime("1290")); //greater than 24hr format
        assertFalse(Time.isValidTime("23-00")); //"-" used"
        assertFalse(Time.isValidTime("")); //empty String given
        assertFalse(Time.isValidTime("1254055")); //random numbers
        assertFalse(Time.isValidTime("23:00")); // ":" used
        assertFalse(Time.isValidTime("!!@#$#%#%^")); // random symbols

        // valid date
        assertTrue(Time.isValidTime("2000"));
        assertTrue(Time.isValidTime("1423"));
    }
}
