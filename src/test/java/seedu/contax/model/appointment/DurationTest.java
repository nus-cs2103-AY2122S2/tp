package seedu.contax.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.contax.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DurationTest {

    @Test
    public void constructor_invalidDuration_throwsIllegalArgumentException() {
        int invalidDuration = -1;
        assertThrows(IllegalArgumentException.class, () -> new Duration(invalidDuration));
    }

    @Test
    public void isValidDuration() {
        // invalid duration
        assertFalse(Duration.isValidDuration(-1)); // negative integer
        assertFalse(Duration.isValidDuration(-123456789)); // negative integer
        assertFalse(Duration.isValidDuration(0)); // zero is also not allowed

        // valid duration
        assertTrue(Duration.isValidDuration(1)); // edge of validity
        assertTrue(Duration.isValidDuration(10)); // small integer
        assertTrue(Duration.isValidDuration(2147483646)); // large integer
    }

    @Test
    public void stringConversion() {
        assertEquals("100 Minutes", new Duration(100).toString());
        assertEquals("2147483646 Minutes", new Duration(2147483646).toString());
    }

    @Test
    public void objectEquality() {
        Duration reference1 = new Duration(1);
        Duration reference2 = new Duration(2);

        assertTrue(reference1.equals(new Duration(1)));
        assertTrue(reference2.equals(new Duration(2)));
        assertTrue(reference1.equals(reference1));

        assertFalse(reference1.equals(123456));
        assertFalse(reference1.equals(reference2));
        assertFalse(reference1.equals(new Duration(3)));
    }

    @Test
    public void hashCodeEquality() {
        Duration reference = new Duration(123);

        assertEquals(reference.hashCode(), new Duration(123).hashCode());
        assertNotEquals(reference.hashCode(), new Duration(321).hashCode());
    }
}
