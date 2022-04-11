package seedu.contax.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.contax.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DurationTest {

    private static final int INVALID_DURATION = -1;

    @Test
    public void constructor_invalidDuration_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Duration(INVALID_DURATION));
    }

    @Test
    public void isValidDuration() {
        // EP: < 1, Invalid Duration
        assertFalse(Duration.isValidDuration(INVALID_DURATION));
        assertFalse(Duration.isValidDuration(-123456789));
        assertFalse(Duration.isValidDuration(0)); // Boundary of validity

        // EP: >= 1, Valid Duration
        assertTrue(Duration.isValidDuration(1)); // Boundary of validity
        assertTrue(Duration.isValidDuration(10)); // Small Integer
        assertTrue(Duration.isValidDuration(Integer.MAX_VALUE)); // Large integer
    }

    @Test
    public void stringConversion() {
        assertEquals("100 Minutes", new Duration(100).toString());
        assertEquals("2147483647 Minutes", new Duration(2147483647).toString());
    }

    @Test
    public void objectEquality() {
        Duration reference1 = new Duration(1);
        Duration reference2 = new Duration(2);

        assertTrue(reference1.equals(reference1));
        assertTrue(reference1.equals(new Duration(1)));

        assertFalse(reference1.equals(null)); // Null
        assertFalse(reference1.equals(123456)); // Different Type

        assertFalse(reference1.equals(reference2)); // Different data
        assertFalse(reference1.equals(new Duration(3))); // Different data
    }

    @Test
    public void hashCodeEquality() {
        Duration reference = new Duration(123);

        assertEquals(reference.hashCode(), new Duration(123).hashCode());
        assertNotEquals(reference.hashCode(), new Duration(321).hashCode());
    }
}
