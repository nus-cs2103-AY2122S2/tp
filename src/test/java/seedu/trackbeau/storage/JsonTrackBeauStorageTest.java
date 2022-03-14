package seedu.trackbeau.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.trackbeau.testutil.Assert.assertThrows;
import static seedu.trackbeau.testutil.TypicalCustomers.ALICE;
import static seedu.trackbeau.testutil.TypicalCustomers.HOON;
import static seedu.trackbeau.testutil.TypicalCustomers.IDA;
import static seedu.trackbeau.testutil.TypicalCustomers.getTypicalTrackBeau;

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
    public void readTrackBeau_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readTrackBeau(null));
    }

    private java.util.Optional<ReadOnlyTrackBeau> readTrackBeau(String filePath) throws Exception {
        return new JsonTrackBeauStorage(Paths.get(filePath)).readTrackBeau(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readTrackBeau("NonExistentFile.json").isPresent());
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
    public void readAndSaveTrackBeau_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempTrackBeau.json");
        TrackBeau original = getTypicalTrackBeau();
        JsonTrackBeauStorage jsonTrackBeauStorage = new JsonTrackBeauStorage(filePath);

        // Save in new file and read back
        jsonTrackBeauStorage.saveTrackBeau(original, filePath);
        ReadOnlyTrackBeau readBack = jsonTrackBeauStorage.readTrackBeau(filePath).get();
        assertEquals(original, new TrackBeau(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addCustomer(HOON);
        original.removeCustomer(ALICE);
        jsonTrackBeauStorage.saveTrackBeau(original, filePath);
        readBack = jsonTrackBeauStorage.readTrackBeau(filePath).get();
        assertEquals(original, new TrackBeau(readBack));

        // Save and read without specifying file path
        original.addCustomer(IDA);
        jsonTrackBeauStorage.saveTrackBeau(original); // file path not specified
        readBack = jsonTrackBeauStorage.readTrackBeau().get(); // file path not specified
        assertEquals(original, new TrackBeau(readBack));

    }

    @Test
    public void saveTrackBeau_nullTrackBeau_throwsNullPointerException() {
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
