package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.typical.TypicalLinkyTime.getTypicalLinkyTime;
import static seedu.address.testutil.typical.TypicalMeetings.CS2030;
import static seedu.address.testutil.typical.TypicalMeetings.CS2100;
import static seedu.address.testutil.typical.TypicalMeetings.CS2107;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.LinkyTime;
import seedu.address.model.ReadOnlyLinkyTime;
import seedu.address.model.module.Module;

public class JsonLinkyTimeStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonLinkyTimeStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readLinkyTime_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readLinkyTime(null));
    }

    private java.util.Optional<ReadOnlyLinkyTime> readLinkyTime(String filePath) throws Exception {
        return new JsonLinkyTimeStorage(Paths.get(filePath)).readLinkyTime(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readLinkyTime("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readLinkyTime("notJsonFormatLinkyTime.json"));
    }

    @Test
    public void readLinkyTime_invalidMeetingLinkyTime_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readLinkyTime("invalidMeetingLinkyTime.json"));
    }

    @Test
    public void readLinkyTime_invalidAndValidMeetingLinkyTime_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readLinkyTime("invalidAndValidMeetingLinkyTime.json"));
    }

    @Test
    public void readAndSaveLinkyTime_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempLinkyTime.json");
        LinkyTime original = getTypicalLinkyTime();
        JsonLinkyTimeStorage jsonLinkyTimeStorage = new JsonLinkyTimeStorage(filePath);

        // Save in new file and read back
        jsonLinkyTimeStorage.saveLinkyTime(original, filePath);
        ReadOnlyLinkyTime readBack = jsonLinkyTimeStorage.readLinkyTime(filePath).get();
        assertEquals(original, new LinkyTime(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addModule(new Module("CS2100"));
        original.addMeeting(CS2100);
        original.removeMeeting(CS2030);
        jsonLinkyTimeStorage.saveLinkyTime(original, filePath);
        readBack = jsonLinkyTimeStorage.readLinkyTime(filePath).get();
        assertEquals(original, new LinkyTime(readBack));

        // Save and read without specifying file path
        original.addModule(new Module("CS2107"));
        original.addMeeting(CS2107);
        jsonLinkyTimeStorage.saveLinkyTime(original); // file path not specified
        readBack = jsonLinkyTimeStorage.readLinkyTime().get(); // file path not specified
        assertEquals(original, new LinkyTime(readBack));

    }

    @Test
    public void saveLinkyTime_nullLinkyTime_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveLinkyTime(null, "SomeFile.json"));
    }

    /**
     * Saves {@code linkyTime} at the specified {@code filePath}.
     */
    private void saveLinkyTime(ReadOnlyLinkyTime linkyTime, String filePath) {
        try {
            new JsonLinkyTimeStorage(Paths.get(filePath))
                    .saveLinkyTime(linkyTime, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveLinkyTime_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveLinkyTime(new LinkyTime(), null));
    }
}
