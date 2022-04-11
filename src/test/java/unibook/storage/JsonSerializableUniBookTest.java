package unibook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import unibook.commons.exceptions.IllegalValueException;
import unibook.commons.util.JsonUtil;
import unibook.model.UniBook;
import unibook.storage.adaptedmodeltypes.JsonAdaptedModule;
import unibook.testutil.Assert;
import unibook.testutil.typicalclasses.TypicalUniBook;

public class JsonSerializableUniBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableUniBookTest");
    private static final Path TYPICAL_UNIBOOK_FILE = TEST_DATA_FOLDER.resolve("typicalUniBook.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidUniBook.json");
    private static final Path DUPLICATE_PHONE_FILE = TEST_DATA_FOLDER.resolve("duplicatePhoneUniBook.json");
    private static final Path DUPLICATE_EMAIL_FILE = TEST_DATA_FOLDER.resolve("duplicateEmailUniBook.json");
    private static final Path DUPLICATE_MODULE_CODE_FILE = TEST_DATA_FOLDER.resolve("duplicateModuleCodeUniBook.json");
    private static final Path DUPLICATE_GROUP_FILE = TEST_DATA_FOLDER.resolve("duplicateGroupUniBook.json");
    private static final Path DUPLICATE_KEY_EVENT_FILE = TEST_DATA_FOLDER.resolve("duplicateKeyEventUniBook.json");

    @Test
    public void toModelType_typicalUniBookFile_success() throws Exception {
        JsonSerializableUniBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_UNIBOOK_FILE,
            JsonSerializableUniBook.class).get();
        UniBook uniBookFromFile = dataFromFile.toModelType();
        UniBook typicalPersonsUniBook = TypicalUniBook.getTypicalUniBook();
        assertEquals(uniBookFromFile, typicalPersonsUniBook);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableUniBook dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
            JsonSerializableUniBook.class).get();
        Assert.assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePhoneNumber_throwsIllegalValueException() throws Exception {
        JsonSerializableUniBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PHONE_FILE,
            JsonSerializableUniBook.class).get();
        Assert.assertThrows(IllegalValueException.class, JsonSerializableUniBook.MESSAGE_DUPLICATE_PHONE,
            dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateEmail_throwsIllegalValueException() throws Exception {
        JsonSerializableUniBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_EMAIL_FILE,
            JsonSerializableUniBook.class).get();
        Assert.assertThrows(IllegalValueException.class, JsonSerializableUniBook.MESSAGE_DUPLICATE_EMAIL,
            dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateModule_throwsIllegalValueException() throws Exception {
        JsonSerializableUniBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_MODULE_CODE_FILE,
            JsonSerializableUniBook.class).get();
        Assert.assertThrows(IllegalValueException.class, JsonSerializableUniBook.MESSAGE_DUPLICATE_MODULE,
            dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateGroup_throwsIllegalValueException() throws Exception {
        JsonSerializableUniBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_GROUP_FILE,
            JsonSerializableUniBook.class).get();
        Assert.assertThrows(IllegalValueException.class, String.format(
                JsonAdaptedModule.GROUP_ALREADY_IN_MODULE_MESSAGE_FORMAT, "W16-1", "CS2103"),
            dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateKeyEvent_throwsIllegalValueException() throws Exception {
        JsonSerializableUniBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_KEY_EVENT_FILE,
            JsonSerializableUniBook.class).get();
        Assert.assertThrows(IllegalValueException.class, String.format(
                JsonAdaptedModule.KEY_EVENT_ALREADY_IN_MODULE_MESSAGE_FORMAT, "EXAM", "2022-05-04T14:00", "CS2103"),
            dataFromFile::toModelType);
    }

}
