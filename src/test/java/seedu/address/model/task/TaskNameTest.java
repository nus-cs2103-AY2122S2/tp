package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Contains unit tests for {@code TaskName}.
 */
public class TaskNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskName(null));
    }

    @Test
    public void constructor_invalidTaskName_throwsIllegalArgumentException() {
        String invalidTaskName = "";
        assertThrows(IllegalArgumentException.class, () -> new TaskName(invalidTaskName));
    }

    @Test
    public void isValidTaskName() {
        // null name -> throws NullPointerException
        assertThrows(NullPointerException.class, () -> TaskName.isValidTaskName(null));

        // invalid name -> returns false
        assertFalse(TaskName.isValidTaskName("")); // empty string
        assertFalse(TaskName.isValidTaskName(" ")); // spaces only

        // valid name -> returns true
        assertTrue(TaskName.isValidTaskName("^")); // only non-alphanumeric characters
        assertTrue(TaskName.isValidTaskName("president's help * 3")); // contains non-alphanumeric characters
        assertTrue(TaskName.isValidTaskName("finish tutorial")); // alphabets only
        assertTrue(TaskName.isValidTaskName("90/100")); // numbers only
        assertTrue(TaskName.isValidTaskName("prepare for lecture at 3pm")); // alphanumeric characters
        assertTrue(TaskName.isValidTaskName("Apply DYOM (Do Your own Module)")); // with capital letters
        assertTrue(TaskName.isValidTaskName("Do Presentation at 3pm in the NUS Club Society Room")); // long names
    }
}
