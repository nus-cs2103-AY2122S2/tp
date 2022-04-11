package seedu.address.model.tasks;

import javafx.collections.ObservableList;

/**
 * Unmodifiable view of a task list
 */
public interface ReadOnlyTaskList {
    /**
     * Adds a task to the task list.
     *
     * @param task the task to add.
     * @return true if added successfully.
     */
    boolean add(Task task);

    /**
     * Removes {@code task} from the task list if it is present.
     *
     * @param task the task to delete.
     * @return true if removed successfully.
     */
    boolean remove(Task task);

    /**
     * Checks if the current tasklist contains the specified task. Note that this method
     * relies on {@code Task}'s equals method.
     *
     * @param task the task to check for
     * @return true if the current tasklist does contain the {@code task}
     */
    boolean contains(Task task);

    /**
     * Gets the task with the index specified at {@code index}.
     * @param index the index, 0 based.
     */
    Task get(int index);

    /**
     * Gets a copy of this object to prevent unintended mutation.
     */
    ReadOnlyTaskList getCopy();

    /**
     * Returns an unmodifiable view of the task list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Task> getInternalList();

    /**
     * Returns the number of tasks in the task list.
     */
    int size();
}
