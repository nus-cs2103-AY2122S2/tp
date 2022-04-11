package seedu.address.model.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.HOMEWORK;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskList;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.PriorityQueue;

import org.junit.jupiter.api.Test;

import com.sun.javafx.collections.ObservableListWrapper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.tasks.exceptions.DuplicateTaskException;
import seedu.address.model.tasks.exceptions.TaskNotFoundException;
import seedu.address.testutil.TaskBuilder;

public class PriorityTaskListTest {

    private PriorityTaskList taskList = new PriorityTaskList();

    @Test
    public void constructor() {
        assertEquals(ObservableListWrapper.class, taskList.getInternalList().getClass());
    }

    @Test
    public void add_noRepeat_success() {
        Task task = new TaskBuilder().build();

        assertTrue(taskList.add(task));
    }

    @Test
    public void add_repeatedTask_failure() {
        Task task = new TaskBuilder().build();

        taskList.add(task);

        assertThrows(DuplicateTaskException.class, () -> taskList.add(task));
    }

    @Test
    public void remove_listContainsTask_success() {
        Task task = new TaskBuilder().build();

        PriorityQueue<Task> pq = new PriorityQueue<>();

        taskList.add(task);

        assertTrue(taskList.remove(task));
    }

    @Test
    public void remove_listDoesNotContainsTask_failure() {
        Task task = new TaskBuilder().build();


        assertThrows(TaskNotFoundException.class, () -> taskList.remove(task));
    }
    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyTaskList_replacesData() {
        PriorityTaskList newData = getTypicalTaskList();
        taskList.resetData(newData);
        assertEquals(newData, taskList);
    }

    @Test
    public void resetData_withDuplicateTasksthrowsDuplicatePersonException() {
        // Two persons with the same identity fields
        Task editedHomework = new TaskBuilder(HOMEWORK).withDeadline(1, 1, 1, 1, 1).build();
        List<Task> tasks = Arrays.asList(HOMEWORK, editedHomework);
        PriorityTaskListStub newData = new PriorityTaskListStub(tasks);

        assertThrows(AssertionError.class, () -> taskList.resetData(newData));
    }

    @Test
    public void contains_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.contains(null));
    }

    @Test
    public void contains_taskNotInContactList_returnsFalse() {
        assertFalse(taskList.contains(HOMEWORK));
    }

    @Test
    public void contains_taskInContactList_returnsTrue() {
        taskList.add(HOMEWORK);
        assertTrue(taskList.contains(HOMEWORK));
    }

    /**
     * A stub ReadOnlyTaskList whose task list can violate interface constraints.
     */
    private static class PriorityTaskListStub implements ReadOnlyTaskList {
        private final ObservableList<Task> tasks = FXCollections.observableArrayList();

        PriorityTaskListStub(Collection<Task> tasks) {
            this.tasks.setAll(tasks);
        }

        @Override
        public boolean add(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean remove(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean contains(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Task get(int index) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyTaskList getCopy() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getInternalList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int size() {
            throw new AssertionError("This method should not be called.");
        }
    }

}
