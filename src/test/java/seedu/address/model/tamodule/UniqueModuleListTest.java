package seedu.address.model.tamodule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.CS2101;
import static seedu.address.testutil.TypicalModules.CS2103T;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.tamodule.exceptions.DuplicateModuleException;
import seedu.address.model.tamodule.exceptions.ModuleNotFoundException;
import seedu.address.testutil.ModuleBuilder;
import seedu.address.testutil.TypicalModules;


public class UniqueModuleListTest {

    private final UniqueModuleList uniqueModuleList = new UniqueModuleList();

    @Test
    public void contains_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleList.contains(null));
    }

    @Test
    public void contains_moduleNotInList_returnsFalse() {
        assertFalse(uniqueModuleList.contains(TypicalModules.getModule(CS2103T)));
    }

    @Test
    public void contains_moduleInList_returnsTrue() {
        uniqueModuleList.add(TypicalModules.getModule(CS2103T));
        assertTrue(uniqueModuleList.contains(TypicalModules.getModule(CS2103T)));
    }

    @Test
    public void contains_moduleWithSameIdentityFieldsInList_returnsTrue() {
        uniqueModuleList.add(TypicalModules.getModule(CS2103T));
        TaModule editedModule = new ModuleBuilder()
                .withModuleCode("CS2103T").withAcademicYear("21S1").build();
        assertTrue(uniqueModuleList.contains(editedModule));
    }

    @Test
    public void add_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleList.add(null));
    }

    @Test
    public void add_duplicateModule_throwsDuplicateModuleException() {
        uniqueModuleList.add(TypicalModules.getModule(CS2103T));
        assertThrows(DuplicateModuleException.class, () ->
                uniqueModuleList.add(TypicalModules.getModule(CS2103T)));
    }

    @Test
    public void setModule_nullTargetModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueModuleList.setModule(null, TypicalModules.getModule(CS2103T)));
    }

    @Test
    public void setModule_nullEditedModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueModuleList.setModule(TypicalModules.getModule(CS2103T), null));
    }

    @Test
    public void setModule_targetModuleNotInList_throwsModuleNotFoundException() {
        assertThrows(ModuleNotFoundException.class, () -> uniqueModuleList.setModule(TypicalModules.getModule(CS2103T),
                TypicalModules.getModule(CS2103T)));
    }

    @Test
    public void setModule_editedModuleIsSameModule_success() {
        uniqueModuleList.add(TypicalModules.getModule(CS2103T));
        uniqueModuleList.setModule(TypicalModules.getModule(CS2103T), TypicalModules.getModule(CS2103T));
        UniqueModuleList expectedUniqueModuleList = new UniqueModuleList();
        expectedUniqueModuleList.add(TypicalModules.getModule(CS2103T));
        assertEquals(expectedUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModule_editedModuleHasDifferentIdentity_success() {
        uniqueModuleList.add(TypicalModules.getModule(CS2103T));
        uniqueModuleList.setModule(TypicalModules.getModule(CS2103T), TypicalModules.getModule(CS2101));
        UniqueModuleList expectedUniqueModuleList = new UniqueModuleList();
        expectedUniqueModuleList.add(TypicalModules.getModule(CS2101));
        assertEquals(expectedUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModule_editedModuleHasNonUniqueIdentity_throwsDuplicateModuleException() {
        uniqueModuleList.add(TypicalModules.getModule(CS2103T));
        uniqueModuleList.add(TypicalModules.getModule(CS2101));
        assertThrows(DuplicateModuleException.class, () ->
                uniqueModuleList.setModule(TypicalModules.getModule(CS2103T), TypicalModules.getModule(CS2101)));
    }

    @Test
    public void remove_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleList.remove(null));
    }

    @Test
    public void remove_moduleDoesNotExist_throwsModuleNotFoundException() {
        assertThrows(ModuleNotFoundException.class, () -> uniqueModuleList.remove(TypicalModules.getModule(CS2103T)));
    }

    @Test
    public void remove_existingModule_removesModule() {
        uniqueModuleList.add(TypicalModules.getModule(CS2103T));
        uniqueModuleList.remove(TypicalModules.getModule(CS2103T));
        UniqueModuleList expectedUniqueModuleList = new UniqueModuleList();
        assertEquals(expectedUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModules_nullUniqueModuleList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleList.setModules((UniqueModuleList) null));
    }

    @Test
    public void setModules_uniqueModuleList_replacesOwnListWithProvidedUniqueModuleList() {
        uniqueModuleList.add(TypicalModules.getModule(CS2103T));
        UniqueModuleList expectedUniqueModuleList = new UniqueModuleList();
        expectedUniqueModuleList.add(TypicalModules.getModule(CS2101));
        uniqueModuleList.setModules(expectedUniqueModuleList);
        assertEquals(expectedUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModules_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleList.setModules((List<TaModule>) null));
    }

    @Test
    public void setModules_list_replacesOwnListWithProvidedList() {
        uniqueModuleList.add(TypicalModules.getModule(CS2103T));
        List<TaModule> moduleList = Collections.singletonList(TypicalModules.getModule(CS2101));
        uniqueModuleList.setModules(moduleList);
        UniqueModuleList expectedUniqueModuleList = new UniqueModuleList();
        expectedUniqueModuleList.add(TypicalModules.getModule(CS2101));
        assertEquals(expectedUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModules_listWithDuplicateModules_throwsDuplicateModuleException() {
        List<TaModule> listWithDuplicateModules = Arrays.asList(TypicalModules.getModule(CS2103T),
                TypicalModules.getModule(CS2103T));
        assertThrows(DuplicateModuleException.class, () -> uniqueModuleList.setModules(listWithDuplicateModules));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueModuleList.asUnmodifiableObservableList().remove(0));
    }
}
