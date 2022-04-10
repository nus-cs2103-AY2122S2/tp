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

public class ModuleNameTest {

    public static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "TaModuleTest");

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ModuleName(null));
    }

    @Test
    public void constructor_invalidModuleName_throwsIllegalArgumentException() {
        String invalidModuleName = "";
        assertThrows(IllegalArgumentException.class, () -> new ModuleName(invalidModuleName));
    }

    @Test
    public void isValidModuleName() throws IOException {
        String modulesList = FileUtil.readFromFile(TEST_DATA_FOLDER.resolve("moduleList.json"));
        JsonAdaptedNusModule[] nusModuleList = JsonUtil.fromJsonString(modulesList, JsonAdaptedNusModule[].class);
        Arrays.asList(nusModuleList).stream().filter(x -> !ModuleName.isValidModuleName(x.getTitle()))
                .peek(x -> System.out.println(x.getTitle())).findAny().ifPresent(x -> fail());

        String invalidModuleName = "";
        assertFalse(ModuleName.isValidModuleName(invalidModuleName));

    }
}
