package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_MIDTERM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_TEST;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.CS2105_MIDTERM;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskList;

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

public class TaskListTest {

    private final TaskList taskList = new TaskList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), taskList.getTaskList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyTaskList_replacesData() {
        TaskList newData = getTypicalTaskList();
        taskList.resetData(newData);
        assertEquals(newData, taskList);
    }

    @Test
    public void resetData_withDuplicateTasks_throwsDuplicateTaskException() {
        // Two tasks with the same identity fields
        Task editedCS2105 = new TaskBuilder(CS2105_MIDTERM)
                .withDescription(VALID_DESCRIPTION_MIDTERM)
                .withTags(VALID_TAG_TEST)
                .build();
        List<Task> newTasks = Arrays.asList(CS2105_MIDTERM, editedCS2105);
        TaskListStub newData = new TaskListStub(newTasks);

        assertThrows(DuplicateTaskException.class, () -> taskList.resetData(newData));
    }

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.hasTask(null));
    }

    @Test
    public void hasTask_taskNotInTaskList_returnsFalse() {
        assertFalse(taskList.hasTask(CS2105_MIDTERM));
    }

    @Test
    public void hasTask_taskInTaskList_returnsTrue() {
        taskList.addTask(CS2105_MIDTERM);
        assertTrue(taskList.hasTask(CS2105_MIDTERM));
    }

    @Test
    public void hasTask_taskWithSameIdentityFieldsInTaskList_returnsTrue() {
        taskList.addTask(CS2105_MIDTERM);
        Task editedCS2105 = new TaskBuilder(CS2105_MIDTERM).withDescription(VALID_DESCRIPTION_MIDTERM)
                .withTags(VALID_TAG_TEST).build();
        assertTrue(taskList.hasTask(editedCS2105));
    }

    @Test
    public void getTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> taskList.getTaskList().remove(0));
    }

    /**
     * A stub ReadOnlyTaskList whose tasks list can violate interface constraints.
     */
    private static class TaskListStub implements ReadOnlyTaskList {
        private final ObservableList<Task> tasks = FXCollections.observableArrayList();

        TaskListStub(Collection<Task> tasks) {
            this.tasks.setAll(tasks);
        }

        @Override
        public ObservableList<Task> getTaskList() {
            return tasks;
        }
    }

}
