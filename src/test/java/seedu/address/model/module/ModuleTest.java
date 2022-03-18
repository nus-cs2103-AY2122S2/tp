package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ModuleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Module(null));
    }

    @Test
    public void constructor_invalidModuleName_throwsIllegalArgumentException() {
        String invalidModuleName = "";
        assertThrows(IllegalArgumentException.class, () -> new Module(invalidModuleName));

        assertThrows(IllegalArgumentException.class, () -> new Module("CS123S ")); // Need 4 digits

        assertThrows(IllegalArgumentException.class, () -> new Module("CSCS1231S ")); // Less than 4 letters prefix

        assertThrows(IllegalArgumentException.class, () -> new Module("C1231S ")); // More than 1 digits prefix
    }

    @Test
    public void isValidModuleName() {
        // null module name
        assertThrows(NullPointerException.class, () -> Module.isValidModuleName(null));

        assertTrue(Module.isValidModuleName("CS1231S"));
        assertTrue(Module.isValidModuleName("GEQ1000"));;
    }

}
