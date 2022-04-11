package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void constructor_allNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Task(null, null));
    }

    @Test
    public void constructor_descriptionNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Task(null, "2022"));
    }

    @Test
    public void constructor_deadlineNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Task("description", null));
    }

    @Test
    public void getDescription_success() {
        Task task = new Task("dummy", "01/01/2022");
        assertEquals("dummy", task.getDescription());
    }

    @Test
    public void getDeadline_success() {
        Task task = new Task("dummy", "01/01/2022");
        assertEquals("01/01/2022", task.getDeadline());
    }

    @Test
    public void toString_success() {
        Task task = new Task("description", "2022");
        assertEquals(task.toString(), "Task: description 2022");
    }
}
