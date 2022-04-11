package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;

/**
 * A list of tasks that enforces uniqueness between its elements and does not allow nulls.
 * A person is considered unique by comparing using {@code Task#isSameTask(Task)}. As such, adding and updating of
 * persons uses Task#isSameTask(Task) for equality so as to ensure that the task being added or updated is
 * unique in terms of identity in the UniquePTaskList. However, the removal of a person uses Task#equals(Object) so
 * as to ensure that the task with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 */
public class UniqueTaskList {

    private final ObservableList<Task> internalList = FXCollections.observableArrayList();
    private final ObservableList<Task> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent task as the given argument.
     */
    public boolean contains(Task toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameTask);
    }

    /**
     * Add a task to the taskList.
     *
     * @param task the Task to be added which must not be null
     */
    public void addTask(Task task) {
        requireNonNull(task);
        this.internalList.add(task);
    }

    /**
     * Adds a task to the list at a specific index.
     * The task must not already exist in the list.
     *
     * @param toAdd the Task to be added which must not be null
     * @param taskId index of the list the task is to be added
     */

    public void addTask(Task toAdd, Integer taskId) throws CommandException {
        requireNonNull(toAdd);
        requireNonNull(taskId);
        internalList.add(taskId - 1, toAdd); // to convert to zero-based indexing
    }

    /**
     * Sets the observable list of tasks from storage.
     */
    public void addAll(List<Task> tasks) {
        requireNonNull(tasks);
        this.internalList.setAll(tasks);
    }

    /**
     * Removes the equivalent task from the list.
     * The task must exist in the list.
     */

    public void deleteTask(Integer taskNumber) {
        requireNonNull(taskNumber);
        internalList.remove(taskNumber - 1);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Task> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

}
