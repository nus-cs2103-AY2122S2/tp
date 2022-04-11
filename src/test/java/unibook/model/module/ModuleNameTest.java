package unibook.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import unibook.testutil.Assert;

public class ModuleNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new ModuleName(null));
    }

    @Test
    public void constructor_emptyName_throwsIllegalArgumentException() {
        String emptyName = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new ModuleName(emptyName));
    }

    @Test
    public void isValidModuleCode() {
        // null code
        Assert.assertThrows(NullPointerException.class, () -> ModuleName.isValidModuleName(null));

        assertFalse(ModuleName.isValidModuleName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
        assertFalse(ModuleName.isValidModuleName("$Software"));

        assertTrue(ModuleName.isValidModuleName("Software Engineering"));
        assertTrue(ModuleName.isValidModuleName("Introduction to Operating Systems"));
        assertTrue(ModuleName.isValidModuleName("Finance"));
        assertTrue(ModuleName.isValidModuleName("Excel 101"));

    }
}
