package unibook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import unibook.commons.exceptions.DataConversionException;
import unibook.model.ReadOnlyUniBook;
import unibook.model.UniBook;
import unibook.testutil.Assert;
import unibook.testutil.TypicalPersons;

public class JsonUniBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonUniBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readUniBook_nullFilePath_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> readUniBook(null));
    }

    private java.util.Optional<ReadOnlyUniBook> readUniBook(String filePath) throws Exception {
        return new JsonUniBookStorage(Paths.get(filePath)).readUniBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
            ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
            : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readUniBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        Assert.assertThrows(DataConversionException.class, () -> readUniBook("notJsonFormatUniBook.json"));
    }

    @Test
    public void readUniBook_invalidPersonUniBook_throwDataConversionException() {
        Assert.assertThrows(DataConversionException.class, () -> readUniBook("invalidUniBook.json"));
    }

    @Test
    public void readUniBook_invalidAndValidPersonUniBook_throwDataConversionException() {
        Assert.assertThrows(DataConversionException.class, () -> readUniBook("invalidAndValidPersonUniBook.json"));
    }

    @Test
    public void readAndSaveUniBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempUniBook.json");
        UniBook original = TypicalPersons.getTypicalUniBook();
        JsonUniBookStorage jsonUniBookStorage = new JsonUniBookStorage(filePath);

        // Save in new file and read back
        jsonUniBookStorage.saveUniBook(original, filePath);
        ReadOnlyUniBook readBack = jsonUniBookStorage.readUniBook(filePath).get();
        assertEquals(original, new UniBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(TypicalPersons.HOON);
        original.removePerson(TypicalPersons.ALICE);
        jsonUniBookStorage.saveUniBook(original, filePath);
        readBack = jsonUniBookStorage.readUniBook(filePath).get();
        assertEquals(original, new UniBook(readBack));

        // Save and read without specifying file path
        original.addPerson(TypicalPersons.IDA);
        jsonUniBookStorage.saveUniBook(original); // file path not specified
        readBack = jsonUniBookStorage.readUniBook().get(); // file path not specified
        assertEquals(original, new UniBook(readBack));

    }

    @Test
    public void saveUniBook_nullUniBook_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> saveUniBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code uniBook} at the specified {@code filePath}.
     */
    private void saveUniBook(ReadOnlyUniBook uniBook, String filePath) {
        try {
            new JsonUniBookStorage(Paths.get(filePath))
                .saveUniBook(uniBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveUniBook_nullFilePath_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> saveUniBook(new UniBook(), null));
    }
}
