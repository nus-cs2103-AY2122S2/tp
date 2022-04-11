package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.TaskList;
import seedu.address.testutil.TypicalTask;

class JsonSerializableTaskListTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableTaskListTest");
    private static final Path TYPICAL_TASK_FILE = TEST_DATA_FOLDER.resolve("typicalTaskTaskList.json");
    private static final Path INVALID_TASK_FILE = TEST_DATA_FOLDER.resolve("invalidTaskTaskList.json");

    @Test
    public void toModelType_typicalTaskFile_success() throws Exception {
        JsonSerializableTaskList dataFromFile = JsonUtil.readJsonFile(TYPICAL_TASK_FILE,
                JsonSerializableTaskList.class).get();
        TaskList taskListFromFile = dataFromFile.toModelType();
        TaskList typicalTaskList = TypicalTask.getTypicalTaskList();
        assertEquals(taskListFromFile.getTaskList().toString(), typicalTaskList.getTaskList().toString());
    }

    @Test
    public void toModelType_invalidTaskFile_throwsIllegalValueException() throws Exception {
        JsonSerializableTaskList dataFromFile = JsonUtil.readJsonFile(INVALID_TASK_FILE,
                JsonSerializableTaskList.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }
}
