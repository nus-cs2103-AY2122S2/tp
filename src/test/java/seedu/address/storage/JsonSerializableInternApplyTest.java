package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.InternApplyMemory;
import seedu.address.testutil.TypicalApplications;

public class JsonSerializableInternApplyTest {

    private static final Path TEST_DATA_FOLDER =
            Paths.get("src", "test", "data", "JsonSerializableInternApplyMemoryTest");
    private static final Path TYPICAL_PERSONS_FILE =
            TEST_DATA_FOLDER.resolve("typicalApplicationInternApplyMemory.json");
    private static final Path INVALID_PERSON_FILE =
            TEST_DATA_FOLDER.resolve("invalidApplicationInternApplyMemory.json");
    private static final Path DUPLICATE_PERSON_FILE =
            TEST_DATA_FOLDER.resolve("duplicateApplicationInternApplyMemory.json");

    @Test
    public void toModelType_typicalApplicationsFile_success() throws Exception {
        JsonSerializableInternApply dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableInternApply.class).get();
        InternApplyMemory internApplyMemoryFromFile = dataFromFile.toModelType();
        InternApplyMemory typicalApplicationInternApplyMemory = TypicalApplications.getTypicalInternApplyMemory();
        assertEquals(internApplyMemoryFromFile, typicalApplicationInternApplyMemory);
    }

    @Test
    public void toModelType_invalidApplicationFile_throwsIllegalValueException() throws Exception {
        JsonSerializableInternApply dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableInternApply.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateApplications_throwsIllegalValueException() throws Exception {
        JsonSerializableInternApply dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableInternApply.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableInternApply.MESSAGE_DUPLICATE_APPLICATION,
                dataFromFile::toModelType);
    }

}
