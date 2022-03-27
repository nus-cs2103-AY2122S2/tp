package manageezpz.model.task;

import static manageezpz.testutil.Assert.assertThrows;
import static manageezpz.testutil.TypicalTasks.READ_BOOK;
import static manageezpz.testutil.TypicalTasks.RETURN_BOOK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import manageezpz.model.task.exceptions.DuplicateTaskException;
import manageezpz.model.task.exceptions.TaskNotFoundException;

public class UniqueTaskListTest {

    private final UniqueTaskList uniqueTaskList = new UniqueTaskList();

    @Test
    public void contains_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.contains(null));
    }

    @Test
    public void contains_taskNotInList_returnsFalse() {
        assertFalse(uniqueTaskList.contains(READ_BOOK));
    }

    @Test
    public void contains_taskInList_returnsTrue() {
        uniqueTaskList.add(READ_BOOK);
        assertTrue(uniqueTaskList.contains(READ_BOOK));
    }

    @Test
    public void add_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.add(null));
    }

    @Test
    public void setTask_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.setTask(null, RETURN_BOOK));
    }

    @Test
    public void setTask_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.setTask(RETURN_BOOK, null));
    }

    @Test
    public void setTask_targetTaskNotInList_throwsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> uniqueTaskList.setTask(READ_BOOK, READ_BOOK));
    }

    @Test
    public void setTask_editedTaskIsSameTask_success() {
        uniqueTaskList.add(READ_BOOK);
        uniqueTaskList.setTask(READ_BOOK, READ_BOOK);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(READ_BOOK);
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTask_editedTaskHasNonUniqueIdentity_throwsDuplicateTaskException() {
        uniqueTaskList.add(READ_BOOK);
        uniqueTaskList.add(RETURN_BOOK);
        assertThrows(DuplicateTaskException.class, () -> uniqueTaskList.setTask(READ_BOOK, RETURN_BOOK));
    }

    @Test
    public void remove_nullTask_throwsNullTaskException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.remove(null));
    }

    @Test
    public void remove_taskDoesNotExist_throwsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> uniqueTaskList.remove(READ_BOOK));
    }

    @Test
    public void remove_existingTask_removesTask() {
        uniqueTaskList.add(READ_BOOK);
        uniqueTaskList.remove(READ_BOOK);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTask_nullUniqueTaskList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.setTasks((UniqueTaskList) null));
    }

    @Test
    public void setTask_listWithDuplicateTask_throwsDuplicateTaskException() {
        List<Task> listWithDuplicateTasks = Arrays.asList(READ_BOOK, READ_BOOK);
        assertThrows(DuplicateTaskException.class, () -> uniqueTaskList.setTasks(listWithDuplicateTasks));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                uniqueTaskList.asUnmodifiableObservableList().remove(0));
    }
}
