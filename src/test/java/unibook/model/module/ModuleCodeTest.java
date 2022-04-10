package unibook.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import unibook.testutil.Assert;

public class ModuleCodeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new ModuleCode(null));
    }

    @Test
    public void constructor_emptyCode_throwsIllegalArgumentException() {
        String emptyCode = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new ModuleCode(emptyCode));
    }

    @Test
    public void isValidModuleCode() {
        // null code
        Assert.assertThrows(NullPointerException.class, () -> ModuleCode.isValidModuleCode(null));

        // invalid code
        assertFalse(ModuleCode.isValidModuleCode(" ")); //Blank with just spaces
        assertFalse(ModuleCode.isValidModuleCode("CS 2103")); //Cannot contain spaces
        assertFalse(ModuleCode.isValidModuleCode("CS1234567890123")); //Cannot contain more than 10 characters

        // valid code
        assertTrue(ModuleCode.isValidModuleCode("CS2103"));
        assertTrue(ModuleCode.isValidModuleCode("CS50"));
        assertTrue(ModuleCode.isValidModuleCode("CS2103/T"));
        assertTrue(ModuleCode.isValidModuleCode("FIN2704"));
        assertTrue(ModuleCode.isValidModuleCode("BSP1701X"));
    }
}
