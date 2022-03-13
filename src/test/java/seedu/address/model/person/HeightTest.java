package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class HeightTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Height(null));
    }

    @Test
    public void constructor_invalidAge_throwsIllegalArgumentException() {
        String invalidHeight = "";
        assertThrows(IllegalArgumentException.class, () -> new Height(invalidHeight));
    }

    @Test
    public void isValidHeight() {
        // null height
        assertThrows(NullPointerException.class, () -> Height.isValidHeight(null));

        // invalid height
        assertFalse(Height.isValidHeight("")); // empty string
        assertFalse(Height.isValidHeight(" ")); // spaces only
        assertFalse(Height.isValidHeight("$%#")); // symbols
        assertFalse(Height.isValidHeight("a24gsd")); // alpha numeric
        assertFalse(Height.isValidHeight("-124")); // negative height
        assertFalse(Height.isValidHeight("301")); // height greater than 300
        assertFalse(Height.isValidHeight("0")); // height lesser than 1
        assertFalse(Height.isValidHeight("21.2")); // decimal height

        // valid height
        assertTrue(Height.isValidHeight("200")); // between 1 and 300
        assertTrue(Height.isValidHeight("300")); // inclusive 100
        assertTrue(Height.isValidHeight("1")); // inclusive 1
    }
}
