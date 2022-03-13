package seedu.address.model.position;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PositionOpeningsTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PositionOpenings(null));
    }

    @Test
    public void constructor_invalidPositionOpenings_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new PositionOpenings(""));
        assertThrows(IllegalArgumentException.class, () -> new PositionOpenings(" "));
        assertThrows(IllegalArgumentException.class, () -> new PositionOpenings("abc"));
    }

    @Test
    public void isValidPositionOpenings() {
        // null position openings
        assertThrows(NullPointerException.class, () -> PositionOpenings.isValidNumber(null));

        // invalid position openings
        assertFalse(PositionOpenings.isValidNumber("")); // empty string
        assertFalse(PositionOpenings.isValidNumber(" ")); // white space only
        assertFalse(PositionOpenings.isValidNumber("abc")); // non-numeric
        assertFalse(PositionOpenings.isValidNumber("-1")); // integer less than 0
        assertFalse(PositionOpenings.isValidNumber("9999999999999")); // integer too large
        assertFalse(PositionOpenings.isValidNumber("234.2131")); // floating point number

        // valid position openings
        assertTrue(PositionOpenings.isValidNumber("0")); // integer 0
        assertTrue(PositionOpenings.isValidNumber("10")); // integer 10
        assertTrue(PositionOpenings.isValidNumber("99999")); // integer 99999
        assertTrue(PositionOpenings.isValidNumber("234")); // integer 235
    }
}
