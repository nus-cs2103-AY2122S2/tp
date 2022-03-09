package seedu.trackbeau.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.trackbeau.testutil.Assert.assertThrows;
import static seedu.trackbeau.testutil.TypicalPersons.ALICE;
import static seedu.trackbeau.testutil.TypicalPersons.HOON;
import static seedu.trackbeau.testutil.TypicalPersons.IDA;
import static seedu.trackbeau.testutil.TypicalPersons.getTypicalAddressBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.trackbeau.model.ReadOnlyTrackBeau;
import seedu.trackbeau.model.TrackBeau;

public class JsonTrackBeauStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonAddressBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAddressBook(null));
    }

    private java.util.Optional<ReadOnlyTrackBeau> readAddressBook(String filePath) throws Exception {
        return new JsonTrackBeauStorage(Paths.get(filePath)).readTrackBeau(addToTestDataPathIfNotNull(filePath));
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

    /*
    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readAddressBook("notJsonFormatTrackBeau.json"));
    }

    @Test
    public void readAddressBook_invalidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidCustomerTrackBeau.json"));
    }
*/
    /*
    @Test
    public void readAddressBook_invalidAndValidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidAndValidCustomerTrackBeau.json"));
    }
*/
    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        TrackBeau original = getTypicalAddressBook();
        JsonTrackBeauStorage jsonAddressBookStorage = new JsonTrackBeauStorage(filePath);

        // Save in new file and read back
        jsonAddressBookStorage.saveTrackBeau(original, filePath);
        ReadOnlyTrackBeau readBack = jsonAddressBookStorage.readTrackBeau(filePath).get();
        assertEquals(original, new TrackBeau(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addCustomer(HOON);
        original.removePerson(ALICE);
        jsonAddressBookStorage.saveTrackBeau(original, filePath);
        readBack = jsonAddressBookStorage.readTrackBeau(filePath).get();
        assertEquals(original, new TrackBeau(readBack));

        // Save and read without specifying file path
        original.addCustomer(IDA);
        jsonAddressBookStorage.saveTrackBeau(original); // file path not specified
        readBack = jsonAddressBookStorage.readTrackBeau().get(); // file path not specified
        assertEquals(original, new TrackBeau(readBack));

    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTrackBeau(null, "SomeFile.json"));
    }

    /**
     * Saves {@code trackBeau} at the specified {@code filePath}.
     */
    private void saveTrackBeau(ReadOnlyTrackBeau trackBeau, String filePath) {
        try {
            new JsonTrackBeauStorage(Paths.get(filePath))
                    .saveTrackBeau(trackBeau, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveTrackBeau_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTrackBeau(new TrackBeau(), null));
    }
}
