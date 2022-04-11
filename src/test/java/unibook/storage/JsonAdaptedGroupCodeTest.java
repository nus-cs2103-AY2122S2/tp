package unibook.storage;

import static unibook.storage.adaptedmodeltypes.JsonAdaptedGroupCode.GROUP_MODULE_DOES_NOT_EXIST;
import static unibook.storage.adaptedmodeltypes.JsonAdaptedGroupCode.GROUP_NAME_DOES_NOT_EXIST;
import static unibook.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import unibook.commons.exceptions.IllegalValueException;
import unibook.model.UniBook;
import unibook.model.module.Module;
import unibook.model.module.ModuleCode;
import unibook.model.module.ModuleName;
import unibook.storage.adaptedmodeltypes.JsonAdaptedGroupCode;
import unibook.storage.adaptedmodeltypes.JsonAdaptedModuleCode;
import unibook.testutil.typicalclasses.TypicalUniBook;

public class JsonAdaptedGroupCodeTest {
    @Test
    public void toModelType_moduleDoesNotExist_throwsIllegalValueException() {
        JsonAdaptedModuleCode nonExistentModule = new JsonAdaptedModuleCode("XXXX");
        JsonAdaptedGroupCode groupCode = new JsonAdaptedGroupCode(nonExistentModule, "YYY");
        assertThrows(IllegalValueException.class,
            String.format(GROUP_MODULE_DOES_NOT_EXIST, nonExistentModule.getModuleCode()), () ->
                    groupCode.toModelType(TypicalUniBook.getTypicalUniBook()));
    }

    @Test
    public void toModelType_groupDoesNotExist_throwsIllegalValueException() {
        String nonExistentGroup = "XXXX";
        String existentModuleCode = "CS2105";
        Module module = new Module(new ModuleName("Intro to SWE"), new ModuleCode("CS2105"));
        UniBook uniBook = TypicalUniBook.getTypicalUniBook();
        uniBook.addModule(module);
        JsonAdaptedModuleCode existentJsonModuleCode = new JsonAdaptedModuleCode(existentModuleCode);
        JsonAdaptedGroupCode groupCode = new JsonAdaptedGroupCode(existentJsonModuleCode, nonExistentGroup);
        assertThrows(IllegalValueException.class,
            String.format(GROUP_NAME_DOES_NOT_EXIST, nonExistentGroup), () ->
                    groupCode.toModelType(uniBook));
    }
}
