package manageezpz.model.task;

import static manageezpz.logic.commands.CommandTestUtil.VALID_TASK_DESCRIPTION;
import static manageezpz.testutil.TypcialTasks.GET_A_DRINK;
import static manageezpz.testutil.TypcialTasks.READ_BOOK;
import static manageezpz.testutil.TypcialTasks.RETURN_BOOK;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import manageezpz.testutil.TaskBuilder;

public class TaskTest {

    @Test
    public void isSameTask() {
        // same object -> returns true
        assertTrue(READ_BOOK.isSameTask(READ_BOOK));

        // null -> returns false
        assertFalse(READ_BOOK.isSameTask(null));

        // description differs in case -> returns false
        Task editedTask = new TaskBuilder(RETURN_BOOK).withDescription(VALID_TASK_DESCRIPTION.toLowerCase()).build();
        assertFalse(RETURN_BOOK.isSameTask(editedTask));

        // description has trailing spaces, all other attributes same -> returns false
        String desWithTrailingSpaces = VALID_TASK_DESCRIPTION + " ";
        editedTask = new TaskBuilder(RETURN_BOOK).withDescription(desWithTrailingSpaces).build();
        assertFalse(RETURN_BOOK.isSameTask(editedTask));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Task readBookCopy = new TaskBuilder(READ_BOOK).build();
        assertTrue(READ_BOOK.equals(readBookCopy));

        // same object -> returns true
        assertTrue(READ_BOOK.equals(READ_BOOK));

        // null -> returns false
        assertFalse(READ_BOOK.equals(null));

        // different type -> returns false
        assertFalse(RETURN_BOOK.equals(5));

        // different task -> returns false
        assertFalse(READ_BOOK.equals(RETURN_BOOK));

    }

    @Test
    public void isDone() {
        READ_BOOK.setTaskDone();
        assertTrue(READ_BOOK.isDone);

        GET_A_DRINK.setTaskDone();
        assertTrue(GET_A_DRINK.isDone);
    }

    @Test
    public void isNotDone() {
        READ_BOOK.setTaskNotDone();
        assertFalse(READ_BOOK.isDone);

        GET_A_DRINK.setTaskNotDone();
        assertFalse(GET_A_DRINK.isDone);
    }

}
