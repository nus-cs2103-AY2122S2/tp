package seedu.trackermon.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.trackermon.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.trackermon.commons.exceptions.IllegalValueException;
import seedu.trackermon.commons.util.JsonUtil;
import seedu.trackermon.model.ShowList;
import seedu.trackermon.testutil.TypicalShows;

/**
 * Contains integration tests (interaction with the Storage) for {@code JsonSerializableShowList}.
 */
public class JsonSerializableShowListTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableShowListTest");
    private static final Path TYPICAL_SHOWS_FILE = TEST_DATA_FOLDER.resolve("typicalShowsShowList.json");
    private static final Path INVALID_SHOW_FILE = TEST_DATA_FOLDER.resolve("invalidShowShowList.json");
    private static final Path DUPLICATE_SHOW_FILE = TEST_DATA_FOLDER.resolve("duplicateShowShowList.json");

    /**
     * Tests the successful conversion of {@code TYPICAL_SHOWS_FILE} to {@code ShowList}.
     */
    @Test
    public void toModelType_typicalShowsFile_success() throws Exception {
        JsonSerializableShowList dataFromFile = JsonUtil.readJsonFile(TYPICAL_SHOWS_FILE,
                JsonSerializableShowList.class).get();
        ShowList showListFromFile = dataFromFile.toModelType();
        ShowList typicalPersonsShowList = TypicalShows.getTypicalShowList();
        assertEquals(showListFromFile, typicalPersonsShowList);
    }

    /**
     * Tests the error message thrown during unsuccessful conversion of {@code INVALID_SHOWS_FILE} to {@code ShowList}.
     */
    @Test
    public void toModelType_invalidShowFile_throwsIllegalValueException() throws Exception {
        JsonSerializableShowList dataFromFile = JsonUtil.readJsonFile(INVALID_SHOW_FILE,
                JsonSerializableShowList.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    /**
     * Tests the unsuccessful conversion of {@code DUPLICATE_SHOWS_FILE} to {@code ShowList}.
     */
    @Test
    public void toModelType_duplicateShows_throwsIllegalValueException() throws Exception {
        JsonSerializableShowList dataFromFile = JsonUtil.readJsonFile(DUPLICATE_SHOW_FILE,
                JsonSerializableShowList.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableShowList.MESSAGE_DUPLICATE_SHOW,
                dataFromFile::toModelType);
    }

}
