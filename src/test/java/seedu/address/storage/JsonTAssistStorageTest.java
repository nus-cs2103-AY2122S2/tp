package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.HOON;
import static seedu.address.testutil.TypicalStudents.IDA;
import static seedu.address.testutil.TypicalTAssist.getTypicalTAssist;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTAssist;
import seedu.address.model.TAssist;

public class JsonTAssistStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonTAssistStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readTAssist_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readTAssist(null));
    }

    private java.util.Optional<ReadOnlyTAssist> readTAssist(String filePath) throws Exception {
        return new JsonTAssistStorage(Paths.get(filePath)).readTAssist(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readTAssist("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readTAssist("notJsonFormatTAssist.json"));
    }

    @Test
    public void readTAssist_invalidPersonTAssist_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTAssist("invalidStudentTAssist.json"));
    }

    @Test
    public void readTAssist_invalidAndValidPersonTAssist_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTAssist("invalidAndValidStudentTAssist.json"));
    }

    @Test
    public void readAndSaveTAssist_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempTAssist.json");
        TAssist original = getTypicalTAssist();
        JsonTAssistStorage jsonTAssistStorage = new JsonTAssistStorage(filePath);

        // Save in new file and read back
        jsonTAssistStorage.saveTAssist(original, filePath);
        ReadOnlyTAssist readBack = jsonTAssistStorage.readTAssist(filePath).get();
        assertEquals(original, new TAssist(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addStudent(HOON);
        original.removeStudent(ALICE);
        jsonTAssistStorage.saveTAssist(original, filePath);
        readBack = jsonTAssistStorage.readTAssist(filePath).get();
        assertEquals(original, new TAssist(readBack));

        // Save and read without specifying file path
        original.addStudent(IDA);
        jsonTAssistStorage.saveTAssist(original); // file path not specified
        readBack = jsonTAssistStorage.readTAssist().get(); // file path not specified
        assertEquals(original, new TAssist(readBack));

    }

    @Test
    public void saveTAssist_nullTAssist_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTAssist(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveTAssist(ReadOnlyTAssist addressBook, String filePath) {
        try {
            new JsonTAssistStorage(Paths.get(filePath))
                    .saveTAssist(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveTAssist_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTAssist(new TAssist(), null));
    }
}
