package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTask.getTypicalTaskList;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTaskList;
import seedu.address.model.TaskList;
import seedu.address.model.task.Task;

class JsonTaskListStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonTaskListStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readTaskList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readTaskList(null));
    }

    private java.util.Optional<ReadOnlyTaskList> readTaskList(String filePath) throws Exception {
        return new JsonTaskListStorage(Paths.get(filePath)).readTaskList(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void readTaskList_missingFile_emptyResult() throws Exception {
        assertFalse(readTaskList("NonExistentFile.json").isPresent());
    }

    @Test
    public void readTaskList_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readTaskList("notJsonFormatAddressBook.json"));
    }

    @Test
    public void readTaskList_invalidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTaskList("invalidTaskTaskList.json"));
    }

    @Test
    public void readTaskList_invalidAndValidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTaskList("invalidAndValidTaskTaskList.json"));
    }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        TaskList original = getTypicalTaskList();
        JsonTaskListStorage jsonTaskListStorage = new JsonTaskListStorage(filePath);

        // Save in new file and read back
        jsonTaskListStorage.saveTaskList(original, filePath);
        ReadOnlyTaskList readBack = jsonTaskListStorage.readTaskList(filePath).get();
        assertEquals(original.getTaskList().toString(), new TaskList(readBack).getTaskList().toString());

        // Modify data, overwrite exiting file, and read back
        original.addTask(new Task("dummy", "01/01/2023"));
        original.deleteTask(1);
        jsonTaskListStorage.saveTaskList(original, filePath);
        readBack = jsonTaskListStorage.readTaskList(filePath).get();
        assertEquals(original.getTaskList().toString(), new TaskList(readBack).getTaskList().toString());

        // Save and read without specifying file path
        original.addTask(new Task("dummy", "01/01/2023"));
        jsonTaskListStorage.saveTaskList(original); // file path not specified
        readBack = jsonTaskListStorage.readTaskList().get(); // file path not specified
        assertEquals(original.getTaskList().toString(), new TaskList(readBack).getTaskList().toString());
    }

    @Test
    public void saveTaskList_nullTaskList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTaskList(null, "SomeFile.json"));
    }

    private void saveTaskList(ReadOnlyTaskList taskList, String filePath) {
        try {
            new JsonTaskListStorage(Paths.get(filePath)).saveTaskList(taskList, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveTaskList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTaskList(new TaskList(), null));
    }
}
