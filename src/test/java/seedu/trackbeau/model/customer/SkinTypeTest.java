package seedu.trackbeau.model.customer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.trackbeau.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SkinTypeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SkinType(null));
    }

    @Test
    public void constructor_invalidSkinType_throwsIllegalArgumentException() {
        String invalidSkinType = "";
        assertThrows(IllegalArgumentException.class, () -> new SkinType(invalidSkinType));
    }

    @Test
    public void isValidSkinType() {
        // null SkinType
        assertThrows(NullPointerException.class, () -> SkinType.isValidSkinType(null));

        // invalid SkinTypes
        assertFalse(SkinType.isValidSkinType("")); // empty string
        assertFalse(SkinType.isValidSkinType(" ")); // spaces only

        // valid SkinTypes
        assertTrue(SkinType.isValidSkinType("Oily"));
        assertTrue(SkinType.isValidSkinType("o")); // one character
        assertTrue(SkinType.isValidSkinType("normal but has psoriasis")); // detailed skin type
    }
}
