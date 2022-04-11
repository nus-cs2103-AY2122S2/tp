package seedu.address.model.consultation;

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
    public void isValidDate() {
        // null date
        assertThrows(NullPointerException.class, () -> Time.isValidTime(null));

        // invalid time
        assertFalse(Time.isValidTime("")); // empty string
        assertFalse(Time.isValidTime(" ")); // space only
        assertFalse(Time.isValidTime("Lorem Ipsum")); // non-time string
        assertFalse(Time.isValidTime("19:00")); // wrong format
        assertFalse(Time.isValidTime("24-00")); // represented by 00-00
        assertFalse(Time.isValidTime("00-65")); // does not exist

        // valid time
        assertTrue(Time.isValidTime("19-00"));
        assertTrue(Time.isValidTime("00-00"));
    }
}
