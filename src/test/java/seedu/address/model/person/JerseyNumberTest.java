package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class JerseyNumberTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Age(null));
    }

    @Test
    public void constructor_invalidJerseyNumber_throwsIllegalArgumentException() {
        String invalidJerseyNumber = "";
        assertThrows(IllegalArgumentException.class, () -> new JerseyNumber(invalidJerseyNumber));
    }

    @Test
    public void isValidJerseyNumber() {
        // null jersey number
        assertThrows(NullPointerException.class, () -> JerseyNumber.isValidJerseyNumber(null));

        // invalid jersey number
        assertFalse(JerseyNumber.isValidJerseyNumber("")); // empty string
        assertFalse(JerseyNumber.isValidJerseyNumber(" ")); // spaces only
        assertFalse(JerseyNumber.isValidJerseyNumber("$%#")); // symbols
        assertFalse(JerseyNumber.isValidJerseyNumber("a24gsd")); // alpha numeric
        assertFalse(JerseyNumber.isValidJerseyNumber("-124")); // negative age
        assertFalse(JerseyNumber.isValidJerseyNumber("101")); // jersey number greater than 100
        assertFalse(JerseyNumber.isValidJerseyNumber("-1")); // negative jersey number
        assertFalse(JerseyNumber.isValidJerseyNumber("21.2")); // decimal jersey number

        // valid jersey number
        assertTrue(JerseyNumber.isValidJerseyNumber("12")); // between 0 and 99
        assertTrue(JerseyNumber.isValidJerseyNumber("99")); // inclusive 99
        assertTrue(JerseyNumber.isValidJerseyNumber("0")); // inclusive 0
    }
}
