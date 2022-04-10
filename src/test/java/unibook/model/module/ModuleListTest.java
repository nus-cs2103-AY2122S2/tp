package unibook.model.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static unibook.testutil.Assert.assertThrows;
import static unibook.testutil.typicalclasses.TypicalModules.CS1231S;
import static unibook.testutil.typicalclasses.TypicalModules.CS2102;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import unibook.model.module.exceptions.DuplicateModuleException;
import unibook.model.module.exceptions.ModuleNotFoundException;
import unibook.testutil.Assert;
import unibook.testutil.builders.ModuleBuilder;

public class ModuleListTest {

    private final ModuleList moduleList = new ModuleList();
    private final ModuleBuilder moduleBuilder = new ModuleBuilder();

    @Test
    public void contains_nullModule_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> moduleList.contains((Module) null));
    }

    @Test
    public void contains_moduleNotInList_returnsFalse() {
        assertFalse(moduleList.contains(new ModuleCode("CS2103")));
        assertFalse(moduleList.contains(moduleBuilder.build()));
    }

    @Test
    public void contains_moduleInList_returnsTrue() {
        moduleList.add(moduleBuilder.build());
        assertTrue(moduleList.contains(moduleBuilder.build()));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> moduleList.add(null));
    }

    @Test
    public void add_duplicateModule_throwsDuplicateModuleException() {
        moduleList.add(moduleBuilder.build());
        assertThrows(DuplicateModuleException.class, () -> moduleList.add(moduleBuilder.build()));
    }


    @Test
    public void setPerson_editedModuleIsSameModule_success() {
        moduleList.add(CS1231S);
        moduleList.setModule(CS1231S, CS1231S);
        ModuleList expectedList = new ModuleList();
        expectedList.add(CS1231S);
        assertEquals(expectedList, moduleList);
    }


    @Test
    public void remove_nullModule_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> moduleList.remove(null));
    }

    @Test
    public void remove_moduleDoesNotExist_throwsModuleNotFoundException() {
        Assert.assertThrows(ModuleNotFoundException.class, () -> moduleList.remove(CS1231S));
    }

    @Test
    public void remove_existingModule_removesModule() {
        moduleList.add(CS1231S);
        moduleList.remove(CS1231S);
        ModuleList expectedList = new ModuleList();
        assertEquals(expectedList, moduleList);
    }

    @Test
    public void setModules_nullModuleList_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> moduleList.setModules((ModuleList) null));
    }

    @Test
    public void setModules_uniqueModuleList_replacesOwnListWithProvidedModuleList() {
        moduleList.add(CS1231S);
        ModuleList expectedList = new ModuleList();
        expectedList.add(CS2102);
        moduleList.setModules(expectedList);
        assertEquals(moduleList, expectedList);
    }

    @Test
    public void setModules_nullList_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> moduleList.setModules((List<Module>) null));
    }

    @Test
    public void setModules_list_replacesOwnListWithProvidedList() {
        moduleList.add(CS1231S);
        List<Module> modList = Collections.singletonList(CS2102);
        moduleList.setModules(modList);
        ModuleList expectedList = new ModuleList();
        expectedList.add(CS2102);
        assertEquals(expectedList, moduleList);
    }

    @Test
    public void setModules_listWithDuplicateModules_throwsDuplicateModuleException() {
        List<Module> listWithDuplicateModules = Arrays.asList(CS1231S, CS1231S);
        Assert.assertThrows(DuplicateModuleException.class, () -> moduleList.setModules(listWithDuplicateModules));
    }

}
