package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_NAME_MEETING;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.MEETING;
import static seedu.address.testutil.TypicalTasks.NON_EXISTENT_TASK;
import static seedu.address.testutil.TypicalTasks.PRESENTATION;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.exceptions.DuplicateTaskException;
import seedu.address.model.task.exceptions.TaskNotFoundException;
import seedu.address.testutil.TaskBuilder;

/**
 * Contains unit tests for {@code UniqueTaskList}.
 */
public class UniqueTaskListTest {

    private final UniqueTaskList uniqueTaskList = new UniqueTaskList();

    @Test
    public void contains_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.contains(null));
    }

    @Test
    public void contains_taskNotInList_returnsFalse() {
        assertFalse(uniqueTaskList.contains(NON_EXISTENT_TASK));
    }

    @Test
    public void contains_taskInList_returnsTrue() {
        uniqueTaskList.add(MEETING);
        assertTrue(uniqueTaskList.contains(MEETING));
    }

    @Test
    public void contains_taskWithSameTaskName_returnsTrue() {
        uniqueTaskList.add(MEETING);
        Task editedTask = new TaskBuilder(MEETING).build();
        assertTrue(uniqueTaskList.contains(editedTask));
    }

    @Test
    public void add_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.add(null));
    }

    @Test
    public void add_duplicateTask_throwsDuplicateGroupException() {
        uniqueTaskList.add(MEETING);
        assertThrows(DuplicateTaskException.class, () -> uniqueTaskList.add(MEETING));
    }

    @Test
    public void setTask_nullTargetTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.setTask(null, MEETING));
    }

    @Test
    public void setTask_nullEditedTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.setTask(MEETING, null));
    }

    @Test
    public void setTask_targetTaskNotInList_throwsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> uniqueTaskList
                .setTask(MEETING, MEETING));
    }

    @Test
    public void setTask_editedTaskIsSameTask_success() {
        uniqueTaskList.add(MEETING);
        uniqueTaskList.setTask(MEETING, MEETING);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(MEETING);
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTask_editedTaskHasSameTaskName_success() {
        uniqueTaskList.add(MEETING);
        Task editedTask = new TaskBuilder(MEETING)
                .withTaskName(VALID_TASK_NAME_MEETING).build();
        uniqueTaskList.setTask(MEETING, editedTask);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(editedTask);
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTask_editedTaskHasDifferentIdentity_success() {
        uniqueTaskList.add(MEETING);
        uniqueTaskList.setTask(MEETING, PRESENTATION);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(PRESENTATION);
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTask_editedTaskHasNonUniqueIdentity_throwsDuplicateGroupException() {
        uniqueTaskList.add(MEETING);
        uniqueTaskList.add(PRESENTATION);
        assertThrows(DuplicateTaskException.class, () ->
                uniqueTaskList.setTask(MEETING, PRESENTATION));
    }
}
