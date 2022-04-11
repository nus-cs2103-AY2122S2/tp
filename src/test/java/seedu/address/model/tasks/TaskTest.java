package seedu.address.model.tasks;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalTasks.ASSIGNMENT;
import static seedu.address.testutil.TypicalTasks.HOMEWORK;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TaskBuilder;

public class TaskTest {
    @Test
    public void equals() {
        // same values -> returns true
        Task homework = new TaskBuilder(HOMEWORK).build();
        assertTrue(HOMEWORK.equals(homework));

        // same object -> returns true
        assertTrue(HOMEWORK.equals(HOMEWORK));

        // null -> returns false
        assertFalse(HOMEWORK.equals(null));

        // different type -> returns false
        assertFalse(HOMEWORK.equals(5));

        // different task -> returns false
        assertFalse(HOMEWORK.equals(ASSIGNMENT));

        // different desc -> returns false
        Task editedHomework = new TaskBuilder(HOMEWORK).withDesc("huh").build();
        assertFalse(HOMEWORK.equals(editedHomework));

        // different deadline -> returns false
        editedHomework = new TaskBuilder(HOMEWORK).withDeadline(1, 1, 1, 0, 0).build();
        assertFalse(HOMEWORK.equals(editedHomework));

    }
}
