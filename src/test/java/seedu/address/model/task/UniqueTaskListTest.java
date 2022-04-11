package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTask.DUMMY_TASK;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;


public class UniqueTaskListTest {

    private final UniqueTaskList uniqueTaskList = new UniqueTaskList();

    @Test
    public void contains_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.contains(null));
    }

    @Test
    public void contains_taskNotInList_returnsFalse() {
        assertFalse(uniqueTaskList.contains(DUMMY_TASK));
    }

    @Test
    public void contains_taskInList_returnsTrue() throws CommandException {
        uniqueTaskList.addTask(DUMMY_TASK, 1);
        assertTrue(uniqueTaskList.contains(DUMMY_TASK));
    }

    @Test
    public void add_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.addTask(null));
    }


    @Test
    public void remove_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.deleteTask(null));
    }

    @Test
    public void remove_existingTask_removesTask() {
        UniqueTaskList expectedUniqueTaskList = uniqueTaskList;
        Task test = new Task("test", "30/03/2022");
        uniqueTaskList.addTask(test);
        uniqueTaskList.deleteTask(1);
        assertEquals(uniqueTaskList, expectedUniqueTaskList);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueTaskList.asUnmodifiableObservableList().remove(0));
    }
}
