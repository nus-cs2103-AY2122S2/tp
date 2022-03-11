package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ModuleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Module(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidModule = " ";
        assertThrows(IllegalArgumentException.class, () -> new Module(invalidModule));
    }

    @Test
    public void isValidTagName() {
        // null module
        assertThrows(NullPointerException.class, () -> Module.isValidTagName(null));

        // invalid module
        assertFalse(Module.isValidTagName("")); // empty string
        assertFalse(Module.isValidTagName(" ")); // spaces only
        assertFalse(Module.isValidTagName("^")); // only non-alphanumeric characters
        assertFalse(Module.isValidTagName("cs2040s*")); // contains non-alphanumeric characters

        // valid module
        assertTrue(Module.isValidTagName("discrete math")); // alphabets only
        assertTrue(Module.isValidTagName("12345")); // numbers only
        assertTrue(Module.isValidTagName("cs2040s")); // alphanumeric characters
        assertTrue(Module.isValidTagName("CS2040S")); // with capital letters
        assertTrue(Module.isValidTagName("Effective Communication for Computing Professionals")); // long names
    }
}
