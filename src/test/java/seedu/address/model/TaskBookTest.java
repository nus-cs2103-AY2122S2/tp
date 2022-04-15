package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.FIRST_TASK;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.task.Task;
import seedu.address.model.task.exceptions.DuplicateTaskException;
import seedu.address.testutil.TaskBuilder;

public class TaskBookTest {

    private final TaskBook taskBook = new TaskBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), taskBook.getTaskList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyTaskBook_replacesData() {
        TaskBook newData = getTypicalTaskBook();
        taskBook.resetData(newData);
        assertEquals(newData, taskBook);
    }

    @Test
    public void resetData_withDuplicateTasks_throwsDuplicateTaskException() {
        // Two tasks with the same identity fields
        Task editedFirstTask = new TaskBuilder(FIRST_TASK).withDate("10-03-2022").withTags("team1")
                .build();
        List<Task> newTasks = Arrays.asList(FIRST_TASK, editedFirstTask);
        TaskBookTest.TaskBookStub newData = new TaskBookTest.TaskBookStub(newTasks);

        assertThrows(DuplicateTaskException.class, () -> taskBook.resetData(newData));
    }

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskBook.hasTask(null));
    }

    @Test
    public void hasTask_taskNotInTaskBook_returnsFalse() {
        assertFalse(taskBook.hasTask(FIRST_TASK));
    }

    @Test
    public void hasTask_taskInTaskBook_returnsTrue() {
        taskBook.addTask(FIRST_TASK);
        assertTrue(taskBook.hasTask(FIRST_TASK));
    }

    @Test
    public void hasTask_taskWithSameIdentityFieldsInTaskBook_returnsTrue() {
        taskBook.addTask(FIRST_TASK);
        Task editedFirstTask = new TaskBuilder(FIRST_TASK).withDate("10-03-2022").withTags("team1")
                .build();
        assertTrue(taskBook.hasTask(editedFirstTask));
    }

    @Test
    public void getTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> taskBook.getTaskList().remove(0));
    }

    /**
     * A stub ReadOnlyTaskBook whose tasks list can violate interface constraints.
     */
    private static class TaskBookStub implements ReadOnlyTaskBook {
        private final ObservableList<Task> tasks = FXCollections.observableArrayList();

        TaskBookStub(Collection<Task> tasks) {
            this.tasks.setAll(tasks);
        }

        @Override
        public ObservableList<Task> getTaskList() {
            return tasks;
        }
    }
}
