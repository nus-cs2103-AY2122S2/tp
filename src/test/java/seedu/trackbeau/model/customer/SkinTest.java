package seedu.trackbeau.model.customer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.trackbeau.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SkinTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Skin(null));
    }

    @Test
    public void constructor_invalidSkinType_throwsIllegalArgumentException() {
        String invalidSkinType = "";
        assertThrows(IllegalArgumentException.class, () -> new Skin(invalidSkinType));
    }

    @Test
    public void isValidSkinType() {
        // null SkinType
        assertThrows(NullPointerException.class, () -> Skin.isValidSkinType(null));

        // invalid SkinTypes
        assertFalse(Skin.isValidSkinType("")); // empty string
        assertFalse(Skin.isValidSkinType(" ")); // spaces only

        // valid SkinTypes
        assertTrue(Skin.isValidSkinType("Oily"));
        assertTrue(Skin.isValidSkinType("o")); // one character
        assertTrue(Skin.isValidSkinType("normal but has psoriasis")); // detailed skin type
    }
}
