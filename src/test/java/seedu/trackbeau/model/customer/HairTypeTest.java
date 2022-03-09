package seedu.trackbeau.model.customer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.trackbeau.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class HairTypeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new HairType(null));
    }

    @Test
    public void constructor_invalidHairType_throwsIllegalArgumentException() {
        String invalidHairType = "";
        assertThrows(IllegalArgumentException.class, () -> new HairType(invalidHairType));
    }

    @Test
    public void isValidHairType() {
        // null HairType
        assertThrows(NullPointerException.class, () -> HairType.isValidHairType(null));

        // invalid HairTypes
        assertFalse(HairType.isValidHairType("")); // empty string
        assertFalse(HairType.isValidHairType(" ")); // spaces only

        // valid HairTypes
        assertTrue(HairType.isValidHairType("Oily"));
        assertTrue(HairType.isValidHairType("o")); // one character
        assertTrue(HairType.isValidHairType("2B curly hair and oily")); // detailed skin type
    }
}
