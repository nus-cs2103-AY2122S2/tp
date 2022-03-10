package seedu.ibook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.ibook.testutil.Assert.assertThrows;
import static seedu.ibook.testutil.TypicalProducts.CHOCOLATE_BREAD;
import static seedu.ibook.testutil.TypicalProducts.PRODUCT_A;
import static seedu.ibook.testutil.TypicalProducts.PRODUCT_B;
import static seedu.ibook.testutil.TypicalProducts.getTypicalIBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.ibook.commons.exceptions.DataConversionException;
import seedu.ibook.model.IBook;
import seedu.ibook.model.ReadOnlyIBook;

public class JsonIBookStorageTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonIBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readIBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readIBook(null));
    }

    private java.util.Optional<ReadOnlyIBook> readIBook(String filePath) throws Exception {
        return new JsonIBookStorage(Paths.get(filePath)).readIBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readIBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readIBook("notJsonFormatIBook.json"));
    }

    @Test
    public void readIBook_invalidProductIBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readIBook("invalidProductIBook.json"));
    }

    @Test
    public void readIBook_invalidAndValidProductIBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readIBook("invalidAndValidProductIBook.json"));
    }

    @Test
    public void readAndSaveIBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        IBook original = getTypicalIBook();
        JsonIBookStorage jsonIBookStorage = new JsonIBookStorage(filePath);

        // Save in new file and read back
        jsonIBookStorage.saveIBook(original, filePath);
        ReadOnlyIBook readBack = jsonIBookStorage.readIBook(filePath).get();
        assertEquals(original, new IBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addProduct(PRODUCT_A);
        original.removeProduct(CHOCOLATE_BREAD);
        jsonIBookStorage.saveIBook(original, filePath);
        readBack = jsonIBookStorage.readIBook(filePath).get();
        assertEquals(original, new IBook(readBack));

        // Save and read without specifying file path
        original.addProduct(PRODUCT_B);
        jsonIBookStorage.saveIBook(original); // file path not specified
        readBack = jsonIBookStorage.readIBook().get(); // file path not specified
        assertEquals(original, new IBook(readBack));

    }

    @Test
    public void saveIBook_nullIBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveIBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code iBook} at the specified {@code filePath}.
     */
    private void saveIBook(ReadOnlyIBook iBook, String filePath) {
        try {
            new JsonIBookStorage(Paths.get(filePath))
                    .saveIBook(iBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveIBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveIBook(new IBook(), null));
    }

}
