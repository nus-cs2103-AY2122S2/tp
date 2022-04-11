package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalApplicants.ALICE;
import static seedu.address.testutil.TypicalApplicants.HOON;
import static seedu.address.testutil.TypicalApplicants.IDA;
import static seedu.address.testutil.TypicalHireLah.getTypicalHireLah;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.HireLah;
import seedu.address.model.ReadOnlyHireLah;

public class JsonHireLahStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonHireLahStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readHireLah_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readHireLah(null));
    }

    private java.util.Optional<ReadOnlyHireLah> readHireLah(String filePath) throws Exception {
        return new JsonHireLahStorage(Paths.get(filePath)).readHireLah(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readHireLah("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readHireLah("notJsonFormatHireLah.json"));
    }

    @Test
    public void readHireLah_invalidApplicantHireLah_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readHireLah("invalidApplicantHireLah.json"));
    }

    @Test
    public void readHireLah_invalidAndValidApplicantHireLah_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readHireLah("invalidAndValidApplicantHireLah.json"));
    }

    @Test
    public void readAndSaveHireLah_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        HireLah original = getTypicalHireLah();
        JsonHireLahStorage jsonAddressBookStorage = new JsonHireLahStorage(filePath);

        // Save in new file and read back
        jsonAddressBookStorage.saveHireLah(original, filePath);
        ReadOnlyHireLah readBack = jsonAddressBookStorage.readHireLah(filePath).get();
        assertEquals(original, new HireLah(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addApplicant(HOON);
        original.removeApplicant(ALICE);
        jsonAddressBookStorage.saveHireLah(original, filePath);
        readBack = jsonAddressBookStorage.readHireLah(filePath).get();
        assertEquals(original, new HireLah(readBack));

        // Save and read without specifying file path
        original.addApplicant(IDA);
        jsonAddressBookStorage.saveHireLah(original); // file path not specified
        readBack = jsonAddressBookStorage.readHireLah().get(); // file path not specified
        assertEquals(original, new HireLah(readBack));

    }

    @Test
    public void saveHireLah_nullHireLah_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveHireLah(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */

    private void saveHireLah(ReadOnlyHireLah addressBook, String filePath) {
        try {
            new JsonHireLahStorage(Paths.get(filePath))
                    .saveHireLah(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveHireLah_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveHireLah(new HireLah(), null));
    }
}

