package seedu.unite.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.unite.testutil.Assert.assertThrows;
import static seedu.unite.testutil.TypicalPersons.ALICE;
import static seedu.unite.testutil.TypicalPersons.HOON;
import static seedu.unite.testutil.TypicalPersons.IDA;
import static seedu.unite.testutil.TypicalPersons.getTypicalAddressBook;

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
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAddressBook(null));
    }

    private java.util.Optional<ReadOnlyUnite> readAddressBook(String filePath) throws Exception {
        return new JsonUniteStorage(Paths.get(filePath)).readUnite(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readAddressBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readAddressBook("notJsonFormatUnite.json"));
    }

    @Test
    public void readAddressBook_invalidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidPersonUnite.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidAndValidPersonUnite.json"));
    }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        Unite original = getTypicalAddressBook();
        JsonUniteStorage jsonAddressBookStorage = new JsonUniteStorage(filePath);

        // Save in new file and read back
        jsonAddressBookStorage.saveUnite(original, filePath);
        ReadOnlyUnite readBack = jsonAddressBookStorage.readUnite(filePath).get();
        assertEquals(original, new Unite(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonAddressBookStorage.saveUnite(original, filePath);
        readBack = jsonAddressBookStorage.readUnite(filePath).get();
        assertEquals(original, new Unite(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonAddressBookStorage.saveUnite(original); // file path not specified
        readBack = jsonAddressBookStorage.readUnite().get(); // file path not specified
        assertEquals(original, new Unite(readBack));

    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveAddressBook(ReadOnlyUnite addressBook, String filePath) {
        try {
            new JsonUniteStorage(Paths.get(filePath))
                    .saveUnite(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(new Unite(), null));
    }
}
