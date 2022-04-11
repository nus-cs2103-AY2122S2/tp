package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;
import static seedu.address.testutil.TypicalPersons.getTypicalHustleBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.HustleBook;
import seedu.address.model.ReadOnlyHustleBook;

public class JsonHustleBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonHustleBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readHustleBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readHustleBook(null));
    }

    private java.util.Optional<ReadOnlyHustleBook> readHustleBook(String filePath) throws Exception {
        return new JsonHustleBookStorage(Paths.get(filePath)).readHustleBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readHustleBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readHustleBook("notJsonFormatHustleBook.json"));
    }

    @Test
    public void readHustleBook_invalidPersonHustleBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readHustleBook("invalidPersonHustleBook.json"));
    }

    @Test
    public void readHustleBook_invalidAndValidPersonHustleBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readHustleBook("invalidAndValidPersonHustleBook.json"));
    }

    @Test
    public void readAndSaveHustleBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempHustleBook.json");
        HustleBook original = getTypicalHustleBook();
        JsonHustleBookStorage jsonHustleBookStorage = new JsonHustleBookStorage(filePath);

        // Save in new file and read back
        jsonHustleBookStorage.saveHustleBook(original, filePath);
        ReadOnlyHustleBook readBack = jsonHustleBookStorage.readHustleBook(filePath).get();
        assertEquals(original, new HustleBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonHustleBookStorage.saveHustleBook(original, filePath);
        readBack = jsonHustleBookStorage.readHustleBook(filePath).get();
        assertEquals(original, new HustleBook(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonHustleBookStorage.saveHustleBook(original); // file path not specified
        readBack = jsonHustleBookStorage.readHustleBook().get(); // file path not specified
        assertEquals(original, new HustleBook(readBack));

    }

    @Test
    public void saveHustleBook_nullHustleBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveHustleBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code hustleBook} at the specified {@code filePath}.
     */
    private void saveHustleBook(ReadOnlyHustleBook hustleBook, String filePath) {
        try {
            new JsonHustleBookStorage(Paths.get(filePath))
                    .saveHustleBook(hustleBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveHustleBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveHustleBook(new HustleBook(), null));
    }
}
