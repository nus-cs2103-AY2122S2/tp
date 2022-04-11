package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTask.DUMMY_TASK;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;

class JsonAdaptedTaskTest {

    @Test
    public void toModelType_validTask_returnsTask() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask("dummy", "01/01/2023");
        assertEquals(DUMMY_TASK.toString(), task.toModelType().toString());
    }

    @Test
    void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(null, "01/01/2023");
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "description");
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    void toModelType_nullDeadline_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask("description", null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "deadline");
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }
}
