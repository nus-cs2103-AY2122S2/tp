package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_HOMEWORK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_HOMEWORK;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.HOMEWORK;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tasks.Deadline;
import seedu.address.model.tasks.Description;

public class JsonAdaptedTaskTest {
    private static final String INVALID_DESC = " ";
    private static final String INVALID_DEADLINE = "i am dead";

    @Test
    public void toModelType_validTaskDetails_returnsTask() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(HOMEWORK);
        assertEquals(HOMEWORK, task.toModelType());
    }

    @Test
    public void toModelType_invalidDesc_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(INVALID_DESC, VALID_DEADLINE_HOMEWORK);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidDeadline_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_DESCRIPTION_HOMEWORK, INVALID_DEADLINE);
        String expectedMessage = Deadline.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }
}
