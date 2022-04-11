package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.HOON;
import static seedu.address.testutil.TypicalStudents.IDA;
import static seedu.address.testutil.TypicalStudents.getTypicalStudentBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyStudentBook;
import seedu.address.model.StudentBook;

public class JsonStudentBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonStudentBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAddressBook(null));
    }

    private java.util.Optional<ReadOnlyStudentBook> readAddressBook(String filePath) throws Exception {
        return new JsonStudentBookStorage(Paths.get(filePath)).readStudentBook(addToTestDataPathIfNotNull(filePath));
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
        assertThrows(DataConversionException.class, () -> readAddressBook("notJsonFormatStudentBook.json"));
    }

    @Test
    public void readAddressBook_invalidStudentAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidStudentBook.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidStudentAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidAndValidStudentBook.json"));
    }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        StudentBook original = getTypicalStudentBook();
        JsonStudentBookStorage jsonStudentBookStorage = new JsonStudentBookStorage(filePath);

        // Save in new file and read back
        jsonStudentBookStorage.saveStudentBook(original, filePath);
        ReadOnlyStudentBook readBack = jsonStudentBookStorage.readStudentBook(filePath).get();
        assertEquals(original, new StudentBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addStudent(HOON);
        original.removeStudent(ALICE);
        jsonStudentBookStorage.saveStudentBook(original, filePath);
        readBack = jsonStudentBookStorage.readStudentBook(filePath).get();
        assertEquals(original, new StudentBook(readBack));

        // Save and read without specifying file path
        original.addStudent(IDA);
        jsonStudentBookStorage.saveStudentBook(original); // file path not specified
        readBack = jsonStudentBookStorage.readStudentBook().get(); // file path not specified
        assertEquals(original, new StudentBook(readBack));

    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveAddressBook(ReadOnlyStudentBook addressBook, String filePath) {
        try {
            new JsonStudentBookStorage(Paths.get(filePath))
                    .saveStudentBook(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(new StudentBook(), null));
    }
}
