package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.MEETING;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.TaskName;

/**
 * Contains unit tests for {@code JsonAdaptedTask}.
 */
public class JsonAdaptedTaskTest {

    private static final String INVALID_TASK_NAME = "";

    @Test
    public void toModelType_validTaskName_returnsTask() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(MEETING);
        assertEquals(MEETING, task.toModelType());
    }

    @Test
    public void toModelType_invalidTaskName_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(INVALID_TASK_NAME);
        String expectedMessage = TaskName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }
}
