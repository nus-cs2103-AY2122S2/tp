package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.typical.TypicalLinkyTime.getTypicalLinkyTime;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.LinkyTime;

public class JsonSerializableLinkyTimeTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableLinkyTimeTest");
    private static final Path TYPICAL_MEETINGS_FILE = TEST_DATA_FOLDER.resolve("typicalMeetingsLinkyTime.json");
    private static final Path INVALID_MEETING_FILE = TEST_DATA_FOLDER.resolve("invalidMeetingLinkyTime.json");
    private static final Path DUPLICATE_MEETING_FILE = TEST_DATA_FOLDER.resolve("duplicateMeetingLinkyTime.json");

    @Test
    public void toModelType_typicalMeetingsFile_success() throws Exception {
        JsonSerializableLinkyTime dataFromFile = JsonUtil.readJsonFile(TYPICAL_MEETINGS_FILE,
                JsonSerializableLinkyTime.class).get();
        LinkyTime linkyTimeFromFile = dataFromFile.toModelType();
        LinkyTime typicalLinkyTime = getTypicalLinkyTime();
        assertEquals(linkyTimeFromFile, typicalLinkyTime);
    }

    @Test
    public void toModelType_invalidMeetingFile_throwsIllegalValueException() throws Exception {
        JsonSerializableLinkyTime dataFromFile = JsonUtil.readJsonFile(INVALID_MEETING_FILE,
                JsonSerializableLinkyTime.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateMeetings_throwsIllegalValueException() throws Exception {
        JsonSerializableLinkyTime dataFromFile = JsonUtil.readJsonFile(DUPLICATE_MEETING_FILE,
                JsonSerializableLinkyTime.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableLinkyTime.MESSAGE_DUPLICATE_MEETING,
                dataFromFile::toModelType);
    }

}
