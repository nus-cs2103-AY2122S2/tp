package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalApplications.GRAB;
import static seedu.address.testutil.TypicalApplications.MASTERCARD;
import static seedu.address.testutil.TypicalApplications.VISA;
import static seedu.address.testutil.TypicalApplications.getTypicalInternApplyMemory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.InternApplyMemory;
import seedu.address.model.ReadOnlyInternApplyMemory;

public class JsonInternApplyStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonInternApplyStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readInternApplyMemory_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readInternApplyMemory(null));
    }

    private java.util.Optional<ReadOnlyInternApplyMemory> readInternApplyMemory(String filePath) throws Exception {
        return new JsonInternApplyStorage(Paths.get(filePath))
                .readInternApplyMemory(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readInternApplyMemory("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readInternApplyMemory("notJsonFormatInternApplyMemory.json"));
    }

    @Test
    public void readInternApplyMemory_invalidApplicationInternApplyMemory_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
                readInternApplyMemory("invalidApplicationInternApplyMemory.json"));
    }

    @Test
    public void readInternApplyMemory_invalidAndValidApplicationInternApplyMemory_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
                readInternApplyMemory("invalidAndValidApplicationInternApplyMemory.json"));
    }

    @Test
    public void readAndSaveInternApplyMemory_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempInternApplyMemory.json");
        InternApplyMemory original = getTypicalInternApplyMemory();
        JsonInternApplyStorage jsonInternApplyStorage = new JsonInternApplyStorage(filePath);

        // Save in new file and read back
        jsonInternApplyStorage.saveInternApply(original, filePath);
        ReadOnlyInternApplyMemory readBack = jsonInternApplyStorage.readInternApplyMemory(filePath).get();
        assertEquals(original, new InternApplyMemory(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addApplication(VISA);
        original.removeApplication(GRAB);
        jsonInternApplyStorage.saveInternApply(original, filePath);
        readBack = jsonInternApplyStorage.readInternApplyMemory(filePath).get();
        assertEquals(original, new InternApplyMemory(readBack));

        // Save and read without specifying file path
        original.addApplication(MASTERCARD);
        jsonInternApplyStorage.saveInternApply(original); // file path not specified
        readBack = jsonInternApplyStorage.readInternApplyMemory().get(); // file path not specified
        assertEquals(original, new InternApplyMemory(readBack));

    }

    @Test
    public void saveInternApplyMemory_nullInternApplyMemory_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveInternApplyMemory(null, "SomeFile.json"));
    }

    /**
     * Saves {@code internApplyMemory} at the specified {@code filePath}.
     */
    private void saveInternApplyMemory(ReadOnlyInternApplyMemory internApplyMemory, String filePath) {
        try {
            new JsonInternApplyStorage(Paths.get(filePath))
                    .saveInternApply(internApplyMemory, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveInternApplyMemory_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveInternApplyMemory(new InternApplyMemory(), null));
    }
}
