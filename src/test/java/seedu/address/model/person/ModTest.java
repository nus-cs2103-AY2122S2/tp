package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ModTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Mod(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new Mod(invalidAddress));
    }

    @Test
    public void isValidMod() {
        // null mod
        assertThrows(NullPointerException.class, () -> Mod.isValidMod(null));

        // invalid mods
        assertFalse(Mod.isValidMod("")); // empty string
        assertFalse(Mod.isValidMod(" ")); // spaces only
        assertFalse(Mod.isValidMod("cs2030S")); // non-capital prefix
        assertFalse(Mod.isValidMod("CS2040s")); // non-capital suffix
        assertFalse(Mod.isValidMod("X1000")); // 1 character prefix
        assertFalse(Mod.isValidMod("ABCD1000")); // >3 character prefix
        assertFalse(Mod.isValidMod("CS1100XD")); // >1 character suffix
        assertFalse(Mod.isValidMod("CS100")); // <4 digits
        assertFalse(Mod.isValidMod("CS10000")); // >4 digits
        assertFalse(Mod.isValidMod("CS12X34")); // characters between digits

        // valid mods
        assertTrue(Mod.isValidMod("CS2100")); // 2 character prefix
        assertTrue(Mod.isValidMod("GER1000")); // 3 character prefix
        assertTrue(Mod.isValidMod("CS1101S")); // 2 character prefix, 1 character suffix
        assertTrue(Mod.isValidMod("NUR1107B")); // 3 character prefix, 1 character suffix

    }
}
