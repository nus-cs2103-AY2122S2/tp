package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RegionTest {

    @Test
    public void fromString_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Region.fromString(null));
    }

    @Test
    public void fromString_invalidRegion_throwsIllegalArgumentException() {
        String invalidRegion = "";
        assertThrows(IllegalArgumentException.class, () -> Region.fromString(invalidRegion));
    }

    @Test
    public void isValidRegion() {
        // null region
        assertThrows(NullPointerException.class, () -> Region.isValidRegion(null));

        // invalid regions
        assertFalse(Region.isValidRegion("")); // empty string
        assertFalse(Region.isValidRegion(" ")); // spaces only
        assertFalse(Region.isValidRegion(" north")); // leading spaces
        assertFalse(Region.isValidRegion("north ")); // trailing spaces
        assertFalse(Region.isValidRegion("northwest")); // not an allowed region

        // valid regions (all lower case)
        assertTrue(Region.isValidRegion("north"));
        assertTrue(Region.isValidRegion("south"));
        assertTrue(Region.isValidRegion("east"));
        assertTrue(Region.isValidRegion("west"));
        assertTrue(Region.isValidRegion("central"));

        // valid regions (all upper case)
        assertTrue(Region.isValidRegion("NORTH"));
        assertTrue(Region.isValidRegion("SOUTH"));
        assertTrue(Region.isValidRegion("EAST"));
        assertTrue(Region.isValidRegion("WEST"));
        assertTrue(Region.isValidRegion("CENTRAL"));

        // valid regions (first letter capitalized)
        assertTrue(Region.isValidRegion("North"));
        assertTrue(Region.isValidRegion("South"));
        assertTrue(Region.isValidRegion("East"));
        assertTrue(Region.isValidRegion("West"));
        assertTrue(Region.isValidRegion("Central"));

        // valid regions (mixed case)
        assertTrue(Region.isValidRegion("nOrTh"));
        assertTrue(Region.isValidRegion("sOuTh"));
        assertTrue(Region.isValidRegion("eASt"));
        assertTrue(Region.isValidRegion("wESt"));
        assertTrue(Region.isValidRegion("cEnTrAl"));
    }
}
