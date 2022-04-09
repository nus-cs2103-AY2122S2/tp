package seedu.address.model.tamodule;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;

public class ModuleCodeTest {

    public static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "TaModuleTest");

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ModuleCode(null));
    }

    @Test
    public void constructor_invalidCode_throwsIllegalArgumentException() {
        String invalidModuleCode = "";
        assertThrows(IllegalArgumentException.class, () -> new ModuleCode(invalidModuleCode));
    }

    @Test
    public void isValidModuleCode() throws IOException {
        String modulesList = FileUtil.readFromFile(TEST_DATA_FOLDER.resolve("moduleList.json"));
        JsonAdaptedNusModule[] nusModuleList = JsonUtil.fromJsonString(modulesList, JsonAdaptedNusModule[].class);
        Arrays.asList(nusModuleList).stream().filter(x -> !ModuleCode.isValidModuleCode(x.getModuleCode()))
                .peek(x -> System.out.println(x.getModuleCode())).findAny().ifPresent(x -> fail());

        //Invalid: Empty module code
        assertFalse(ModuleCode.isValidModuleCode(""));

        //Invalid: Module code starting with invalid numbers of letters
        assertFalse(ModuleCode.isValidModuleCode("123AB"));
        assertFalse(ModuleCode.isValidModuleCode("A123AB"));
        assertFalse(ModuleCode.isValidModuleCode("ABCDE123AB"));

        //Invalid: Module code with less than 4 digit
        assertFalse(ModuleCode.isValidModuleCode("AB1"));
        assertFalse(ModuleCode.isValidModuleCode("AB12"));
        assertFalse(ModuleCode.isValidModuleCode("AB123"));
        assertFalse(ModuleCode.isValidModuleCode("AB1A"));
        assertFalse(ModuleCode.isValidModuleCode("AB12B"));
        assertFalse(ModuleCode.isValidModuleCode("AB123C"));

    }
}
