package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.LessonBook;
import seedu.address.testutil.TypicalLessons;

public class JsonSerializableLessonBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableLessonBookTest");
    private static final Path TYPICAL_LESSON_FILE = TEST_DATA_FOLDER.resolve("typicalLessonBook.json");
    private static final Path INVALID_LESSON_FILE = TEST_DATA_FOLDER.resolve("invalidLessonBook.json");
    private static final Path CONFLICTING_LESSON_FILE = TEST_DATA_FOLDER.resolve("conflictingLessonBook.json");

    @Test
    public void toModelType_typicalLessonFile_success() throws Exception {
        JsonSerializableLessonBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_LESSON_FILE,
                JsonSerializableLessonBook.class).get();
        LessonBook lessonBookFromFile = dataFromFile.toModelType();
        LessonBook typicalLessonBook = TypicalLessons.getTypicalLessonBook();

        assertEquals(lessonBookFromFile, typicalLessonBook);
    }

    @Test
    public void toModelType_invalidLessonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableLessonBook dataFromFile = JsonUtil.readJsonFile(INVALID_LESSON_FILE,
                JsonSerializableLessonBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_conflictingLessons_throwsIllegalValueException() throws Exception {
        JsonSerializableLessonBook dataFromFile = JsonUtil.readJsonFile(CONFLICTING_LESSON_FILE,
                JsonSerializableLessonBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

}
