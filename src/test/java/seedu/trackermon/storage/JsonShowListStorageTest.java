package seedu.trackermon.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.trackermon.testutil.Assert.assertThrows;
import static seedu.trackermon.testutil.TypicalShows.HIMYM;
import static seedu.trackermon.testutil.TypicalShows.WEATHERING_WITH_YOU;
import static seedu.trackermon.testutil.TypicalShows.getTypicalShowList;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.trackermon.commons.exceptions.DataConversionException;
import seedu.trackermon.model.ReadOnlyShowList;
import seedu.trackermon.model.ShowList;

/**
 * Contains integration tests (interaction with the Storage) for {@code JsonShowListStorage}.
 */
public class JsonShowListStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonShowListStorageTest");

    @TempDir
    public Path testFolder;

    /**
     * Tests the error thrown when {@code readShowList} reads a null {@code FilePath}.
     */
    @Test
    public void readShowList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readShowList(null));
    }

    private java.util.Optional<ReadOnlyShowList> readShowList(String filePath) throws Exception {
        return new JsonShowListStorage(Paths.get(filePath)).readShowList(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    /**
     * Tests that a {@code ReadOnlyShowList} is not obtained after {@code ShowList} reads a non-existent file.
     */
    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readShowList("NonExistentFile.json").isPresent());
    }

    /**
     * Tests that a {@code DataConversionException} is thrown when {@code readShowList} is provided
     * a non-Json format data file.
     */
    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readShowList("notJsonFormatShowList.json"));
    }

    /**
     * Tests that a {@code DataConversionException} is thrown when {@code readShowList} is provided
     * a data file with invalid Shows.
     */
    @Test
    public void readShowList_invalidShowShowList_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readShowList("invalidShowShowList.json"));
    }

    /**
     * Tests that a {@code DataConversionException} is thrown when {@code readShowList} is provided
     * a data file with both valid and invalid Shows.
     */
    @Test
    public void readShowList_invalidAndValidShowShowList_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readShowList("invalidAndValidShowShowList.json"));
    }

    /**
     * Tests that no errors are thrown when providing {@code readShowList} and {@code saveShowList} valid inputs.
     */
    @Test
    public void readAndSaveShowList_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempShowList.json");
        ShowList original = getTypicalShowList();
        JsonShowListStorage jsonShowListStorage = new JsonShowListStorage(filePath);

        // Save in new file and read back
        jsonShowListStorage.saveShowList(original, filePath);
        ReadOnlyShowList readBack = jsonShowListStorage.readShowList(filePath).get();
        assertEquals(original, new ShowList(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addShow(WEATHERING_WITH_YOU);
        original.removeShow(HIMYM);
        jsonShowListStorage.saveShowList(original, filePath);
        readBack = jsonShowListStorage.readShowList(filePath).get();
        assertEquals(original, new ShowList(readBack));

        // Save and read without specifying file path
        original.addShow(HIMYM);
        jsonShowListStorage.saveShowList(original); // file path not specified
        readBack = jsonShowListStorage.readShowList().get(); // file path not specified
        assertEquals(original, new ShowList(readBack));

    }

    /**
     * Tests that a {@code NullPointerException} is thrown when {@code saveShowList}
     * attempts to save a null {@code ShowList}.
     */
    @Test
    public void saveShowList_nullShowList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveShowList(null, "SomeFile.json"));
    }

    /**
     * Saves {@code showList} at the specified {@code filePath}.
     */
    private void saveShowList(ReadOnlyShowList showList, String filePath) {
        try {
            new JsonShowListStorage(Paths.get(filePath))
                    .saveShowList(showList, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    /**
     * Tests that a {@code NullPointerException} is thrown when {@code saveShowList}
     * attempts to save {@code ShowList} to a null file path.
     */
    @Test
    public void saveShowList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveShowList(new ShowList(), null));
    }
}
