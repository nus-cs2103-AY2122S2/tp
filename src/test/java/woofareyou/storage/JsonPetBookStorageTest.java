package woofareyou.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static woofareyou.testutil.Assert.assertThrows;
import static woofareyou.testutil.TypicalPets.BOBA;
import static woofareyou.testutil.TypicalPets.HOON;
import static woofareyou.testutil.TypicalPets.IDA;
import static woofareyou.testutil.TypicalPets.getTypicalPetBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import woofareyou.commons.exceptions.DataConversionException;
import woofareyou.model.PetBook;
import woofareyou.model.ReadOnlyPetBook;

public class JsonPetBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonPetBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readPetBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readPetBook(null));
    }

    private java.util.Optional<ReadOnlyPetBook> readPetBook(String filePath) throws Exception {
        return new JsonPetBookStorage(Paths.get(filePath)).readPetBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readPetBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readPetBook("notJsonFormatPetBook.json"));
    }

    @Test
    public void readPetBook_invalidPetBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readPetBook("invalidPetsPetBook.json"));
    }

    @Test
    public void readPetBook_invalidAndValidPetBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readPetBook("invalidAndValidPetBook.json"));
    }

    @Test
    public void readAndSavePetBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        PetBook original = getTypicalPetBook();
        JsonPetBookStorage jsonAddressBookStorage = new JsonPetBookStorage(filePath);

        // Save in new file and read back
        jsonAddressBookStorage.savePetBook(original, filePath);
        ReadOnlyPetBook readBack = jsonAddressBookStorage.readPetBook(filePath).get();
        assertEquals(original, new PetBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPet(HOON);
        original.removePet(BOBA);
        jsonAddressBookStorage.savePetBook(original, filePath);
        readBack = jsonAddressBookStorage.readPetBook(filePath).get();
        assertEquals(original, new PetBook(readBack));

        // Save and read without specifying file path
        original.addPet(IDA);
        jsonAddressBookStorage.savePetBook(original); // file path not specified
        readBack = jsonAddressBookStorage.readPetBook().get(); // file path not specified
        assertEquals(original, new PetBook(readBack));

    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveAddressBook(ReadOnlyPetBook addressBook, String filePath) {
        try {
            new JsonPetBookStorage(Paths.get(filePath))
                    .savePetBook(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(new PetBook(), null));
    }
}
