package seedu.address.model.tamodule;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.CS2101;
import static seedu.address.testutil.TypicalModules.CS2103T;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ModuleBuilder;
import seedu.address.testutil.TypicalModules;

public class TaModuleTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        TaModule module = new ModuleBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> module.getStudents().remove(0));
    }

    @Test
    public void isSameModule() {
        // same object -> returns true
        assertTrue(TypicalModules.getModule(CS2103T).isSameModule(TypicalModules.getModule(CS2103T)));

        // null -> returns false
        assertFalse(TypicalModules.getModule(CS2103T).isSameModule(null));

        // different module code, all other attributes same -> returns false
        TaModule editedModule = new ModuleBuilder(TypicalModules.getModule(CS2103T)).withModuleCode("CS2103").build();
        assertFalse(TypicalModules.getModule(CS2103T).isSameModule(editedModule));

        // different Academic Year code -> returns false
        editedModule = new ModuleBuilder(TypicalModules.getModule(CS2103T)).withAcademicYear("21S4").build();
        assertFalse(TypicalModules.getModule(CS2103T).isSameModule(editedModule));
    }

    @Test
    public void equals() {
        // same values -> returns true
        TaModule moduleCopy = new ModuleBuilder(TypicalModules.getModule(CS2103T)).build();
        assertTrue(TypicalModules.getModule(CS2103T).equals(moduleCopy));

        // same object -> returns true
        assertTrue(TypicalModules.getModule(CS2103T).equals(TypicalModules.getModule(CS2103T)));

        // null -> returns false
        assertFalse(TypicalModules.getModule(CS2103T).equals(null));

        // different type -> returns false
        assertFalse(TypicalModules.getModule(CS2103T).equals(5));

        // different module -> returns false
        assertFalse(TypicalModules.getModule(CS2103T).equals(TypicalModules.getModule(CS2101)));

        // different name -> returns false
        TaModule editedModule = new ModuleBuilder(TypicalModules.getModule(CS2103T))
                .withModuleName("Software Engineering 2").build();
        assertFalse(TypicalModules.getModule(CS2103T).equals(editedModule));

        // name differs in case, all other attributes same -> returns false
        editedModule = new ModuleBuilder(TypicalModules.getModule(CS2103T)).withModuleName(
                TypicalModules.getModule(CS2103T).getModuleName().value.toLowerCase()).build();
        assertFalse(TypicalModules.getModule(CS2103T).equals(editedModule));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = TypicalModules.getModule(CS2103T).getModuleName().value.toLowerCase() + " ";
        editedModule = new ModuleBuilder(TypicalModules.getModule(CS2103T))
                .withModuleName(nameWithTrailingSpaces).build();
        assertFalse(TypicalModules.getModule(CS2103T).equals(editedModule));

        // different module code -> returns false
        editedModule = new ModuleBuilder(TypicalModules.getModule(CS2103T)).withModuleCode("CS2103").build();
        assertFalse(TypicalModules.getModule(CS2103T).equals(editedModule));

        // different Academic Year code -> returns false
        editedModule = new ModuleBuilder(TypicalModules.getModule(CS2103T)).withAcademicYear("21S4").build();
        assertFalse(TypicalModules.getModule(CS2103T).equals(editedModule));

    }
}
