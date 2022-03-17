package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.JsonSerializableTestClass;
import seedu.address.testutil.TestUtil;

/**
 * Tests JSON Read and Write
 */
public class JsonUtilTest {

    private static final Path SERIALIZATION_FILE = TestUtil.getFilePathInSandboxFolder("serialize.json");

    @Test
    public void serializeObjectToJsonFile_noExceptionThrown() throws IOException {
        JsonSerializableTestClass jsonSerializableTestClass = new JsonSerializableTestClass();
        jsonSerializableTestClass.setTestValues();

        JsonUtil.serializeObjectToJsonFile(SERIALIZATION_FILE, jsonSerializableTestClass);

        assertEquals(FileUtil.readFromFile(SERIALIZATION_FILE), JsonSerializableTestClass.JSON_STRING_REPRESENTATION);
    }

    @Test
    public void deserializeObjectFromJsonFile_noExceptionThrown() throws IOException {
        FileUtil.writeToFile(SERIALIZATION_FILE, JsonSerializableTestClass.JSON_STRING_REPRESENTATION);

        JsonSerializableTestClass jsonSerializableTestClass = JsonUtil
                .deserializeObjectFromJsonFile(SERIALIZATION_FILE, JsonSerializableTestClass.class);

        assertEquals(jsonSerializableTestClass.getName(), JsonSerializableTestClass.getNameTestValue());
        assertEquals(jsonSerializableTestClass.getListOfLocalDateTimes(),
                JsonSerializableTestClass.getListTestValues());
        assertEquals(jsonSerializableTestClass.getMapOfIntegerToString(),
                JsonSerializableTestClass.getHashMapTestValues());
    }

    //TODO: @Test jsonUtil_readJsonStringToObjectInstance_correctObject()

    //TODO: @Test jsonUtil_writeThenReadObjectToJson_correctObject()
}
