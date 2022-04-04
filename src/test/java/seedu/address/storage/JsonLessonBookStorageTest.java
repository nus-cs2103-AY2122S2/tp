package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalLessons.RECURRING_GEOGRAPHY_LESSON;
import static seedu.address.testutil.TypicalLessons.TEMPORARY_HISTORY_LESSON;
import static seedu.address.testutil.TypicalLessons.TEMPORARY_PHYSICS_LESSON;
import static seedu.address.testutil.TypicalLessons.getTypicalLessonBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.LessonBook;
import seedu.address.model.ReadOnlyLessonBook;

public class JsonLessonBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonLessonBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readLessonBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readLessonBook(null));
    }

    private java.util.Optional<ReadOnlyLessonBook> readLessonBook(String filePath) throws Exception {
        return new JsonLessonBookStorage(Paths.get(filePath)).readLessonBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readLessonBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readLessonBook("notJsonFormatLessonBook.json"));
    }

    @Test
    public void readLessonBook_invalidLessonBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readLessonBook("invalidLessonBook.json"));
    }

    @Test
    public void readLessonBook_invalidAndValidLessonsLessonBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readLessonBook("invalidAndValidLessonsLessonBook.json"));
    }

    @Test
    public void readAndSaveLessonBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempLessonBook.json");
        LessonBook original = getTypicalLessonBook();
        JsonLessonBookStorage jsonLessonBookStorage = new JsonLessonBookStorage(filePath);

        // Save in new file and read back
        jsonLessonBookStorage.saveLessonBook(original, filePath);
        ReadOnlyLessonBook readBack = jsonLessonBookStorage.readLessonBook(filePath).get();
        assertEquals(original, new LessonBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addLesson(TEMPORARY_PHYSICS_LESSON);
        original.deleteLesson(TEMPORARY_HISTORY_LESSON);
        jsonLessonBookStorage.saveLessonBook(original, filePath);
        readBack = jsonLessonBookStorage.readLessonBook(filePath).get();
        assertEquals(original, new LessonBook(readBack));

        // Save and read without specifying file path
        original.addLesson(RECURRING_GEOGRAPHY_LESSON);
        jsonLessonBookStorage.saveLessonBook(original); // file path not specified
        readBack = jsonLessonBookStorage.readLessonBook().get(); // file path not specified
        assertEquals(original, new LessonBook(readBack));
    }

    @Test
    public void saveLessonBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveLessonBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code lessonBook} at the specified {@code filePath}.
     */
    private void saveLessonBook(ReadOnlyLessonBook lessonBook, String filePath) {
        try {
            new JsonLessonBookStorage(Paths.get(filePath))
                    .saveLessonBook(lessonBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveLessonBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveLessonBook(new LessonBook(), null));
    }
}
