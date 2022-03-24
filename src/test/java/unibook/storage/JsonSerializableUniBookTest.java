package unibook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import unibook.commons.exceptions.IllegalValueException;
import unibook.commons.util.JsonUtil;
import unibook.model.UniBook;
import unibook.testutil.Assert;
import unibook.testutil.typicalclasses.TypicalUniBook;

public class JsonSerializableUniBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableUniBookTest");
    private static final Path TYPICAL_UNIBOOK_FILE = TEST_DATA_FOLDER.resolve("typicalUniBook.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidUniBook.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonUniBook.json");
    private static final Path DUPLICATE_MODULE_FILE = TEST_DATA_FOLDER.resolve("duplicateModuleUniBook.json");
    private static final Path DUPLICATE_MODULE_CODE_FILE = TEST_DATA_FOLDER.resolve("duplicateModuleCodeUniBook.json");
    private static final Path DUPLICATE_GROUP_FILE = TEST_DATA_FOLDER.resolve("duplicateGroupUniBook.json");
    private static final Path DUPLICATE_GROUP_CODE_FILE = TEST_DATA_FOLDER.resolve("duplicateGroupCodeUniBook.json");

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
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableUniBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
            JsonSerializableUniBook.class).get();
        Assert.assertThrows(IllegalValueException.class, JsonSerializableUniBook.MESSAGE_DUPLICATE_PERSON,
            dataFromFile::toModelType);
    }

    //TODO add other duplicate tests

}
