package seedu.address.model.person;

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
    public void isValidDate() {
        // null availability
        assertThrows(NullPointerException.class, () -> Availability.isValidDate(null));

        // invalid availability
        assertFalse(Availability.isValidDate(""));
        assertFalse(Availability.isValidDate(","));
        assertFalse(Availability.isValidDate("0"));
        assertFalse(Availability.isValidDate("8"));
        assertFalse(Availability.isValidDate(",1"));
        assertFalse(Availability.isValidDate("1,"));
        assertFalse(Availability.isValidDate("1,,2"));
        assertFalse(Availability.isValidDate("12"));
        assertFalse(Availability.isValidDate("a"));
        assertFalse(Availability.isValidDate(",a"));
        assertFalse(Availability.isValidDate("a,"));
        assertFalse(Availability.isValidDate("a,a"));

        // valid availability
        assertTrue(Availability.isValidDate("2"));
        assertTrue(Availability.isValidDate("1,2,3,4,5,6,7"));
        assertTrue(Availability.isValidDate("7,6,5,4,3,2,1"));
    }
}
