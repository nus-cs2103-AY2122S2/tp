package seedu.unite.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.unite.testutil.Assert.assertThrows;
import static seedu.unite.testutil.TypicalPersons.ALICE;
import static seedu.unite.testutil.TypicalPersons.HOON;
import static seedu.unite.testutil.TypicalPersons.IDA;
import static seedu.unite.testutil.TypicalPersons.getTypicalUnite;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.unite.commons.exceptions.DataConversionException;
import seedu.unite.model.ReadOnlyUnite;
import seedu.unite.model.Unite;

public class JsonUniteStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonUniteStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readUnite_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readUnite(null));
    }

    private java.util.Optional<ReadOnlyUnite> readUnite(String filePath) throws Exception {
        return new JsonUniteStorage(Paths.get(filePath)).readUnite(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readUnite("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readUnite("notJsonFormatUnite.json"));
    }

    @Test
    public void readUnite_invalidPersonUnite_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readUnite("invalidPersonUnite.json"));
    }

    @Test
    public void readUnite_invalidAndValidPersonUnite_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readUnite("invalidAndValidPersonUnite.json"));
    }

    @Test
    public void readAndSaveUnite_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempUnite.json");
        Unite original = getTypicalUnite();
        JsonUniteStorage jsonUniteStorage = new JsonUniteStorage(filePath);

        // Save in new file and read back
        jsonUniteStorage.saveUnite(original, filePath);
        ReadOnlyUnite readBack = jsonUniteStorage.readUnite(filePath).get();
        assertEquals(original, new Unite(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonUniteStorage.saveUnite(original, filePath);
        readBack = jsonUniteStorage.readUnite(filePath).get();
        assertEquals(original, new Unite(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonUniteStorage.saveUnite(original); // file path not specified
        readBack = jsonUniteStorage.readUnite().get(); // file path not specified
        assertEquals(original, new Unite(readBack));

    }

    @Test
    public void saveUnite_nullUnite_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveUnite(null, "SomeFile.json"));
    }

    /**
     * Saves {@code Unite} at the specified {@code filePath}.
     */
    private void saveUnite(ReadOnlyUnite unite, String filePath) {
        try {
            new JsonUniteStorage(Paths.get(filePath))
                    .saveUnite(unite, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveUnite_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveUnite(new Unite(), null));
    }
}
