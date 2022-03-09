package seedu.trackbeau.model.customer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.trackbeau.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class HairTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Hair(null));
    }

    @Test
    public void constructor_invalidHairType_throwsIllegalArgumentException() {
        String invalidHairType = "";
        assertThrows(IllegalArgumentException.class, () -> new Hair(invalidHairType));
    }

    @Test
    public void isValidHairType() {
        // null HairType
        assertThrows(NullPointerException.class, () -> Hair.isValidHairType(null));

        // invalid HairTypes
        assertFalse(Hair.isValidHairType("")); // empty string
        assertFalse(Hair.isValidHairType(" ")); // spaces only

        // valid HairTypes
        assertTrue(Hair.isValidHairType("Oily"));
        assertTrue(Hair.isValidHairType("o")); // one character
        assertTrue(Hair.isValidHairType("2B curly hair and oily")); // detailed skin type
    }
}
