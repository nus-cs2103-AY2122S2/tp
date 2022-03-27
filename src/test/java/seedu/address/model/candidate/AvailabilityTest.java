package seedu.address.model.candidate;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AvailabilityTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Availability(null));
    }

    @Test
    public void isValidDay() {
        // null availability
        assertThrows(NullPointerException.class, () -> Availability.isValidDay(null));

        // invalid availability
        assertFalse(Availability.isValidDay(""));
        assertFalse(Availability.isValidDay(","));
        assertFalse(Availability.isValidDay("0"));
        assertFalse(Availability.isValidDay("8"));
        assertFalse(Availability.isValidDay(",1"));
        assertFalse(Availability.isValidDay("1,"));
        assertFalse(Availability.isValidDay("1,,2"));
        assertFalse(Availability.isValidDay("12"));
        assertFalse(Availability.isValidDay("a"));
        assertFalse(Availability.isValidDay(",a"));
        assertFalse(Availability.isValidDay("a,"));
        assertFalse(Availability.isValidDay("a,a"));

        // valid availability
        assertTrue(Availability.isValidDay("2"));
        assertTrue(Availability.isValidDay("1,2,3,4,5"));
        assertTrue(Availability.isValidDay("5,4,3,2,1"));
    }

    @Test
    public void getAvailableListAsBoolean_oneAvailableDays() {
        Availability availability = new Availability("1");
        boolean[] oneAvailableDay = new boolean[6];
        oneAvailableDay[0] = true;
        assertArrayEquals(oneAvailableDay, availability.getAvailableListAsBoolean());
    }
}
