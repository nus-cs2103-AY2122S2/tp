package seedu.address.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.Set;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Task> PREDICATE_SHOW_ALL_TASKS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' task list file path.
     */
    Path getTaskListFilePath();

    /**
     * Sets the user prefs' task list file path.
     */
    void setTaskListFilePath(Path taskListFilePath);

    /**
     * Replaces task list data with the data in {@code taskList}.
     */
    void setTaskList(ReadOnlyTaskList taskList);

    /** Returns the TaskList */
    ReadOnlyTaskList getTaskList();

    /**
     * Returns true if a task with the same identity as {@code task} exists in the task list.
     */
    boolean hasTask(Task task);

    /**
     * Deletes the given task.
     * The task must exist in the task list.
     */
    void deleteTask(Task target);

    /**
     * Adds the given task.
     * {@code task} must not already exist in the task list.
     */
    void addTask(Task task);

    /**
     * Replaces the given task {@code target} with {@code editedTask}.
     * {@code target} must exist in the task list.
     * The task identity of {@code editedTask} must not be the same as another existing task in the task list.
     */
    void setTask(Task target, Task editedTask);

    /**
     * Replaces the task {@code target} in the list with {@code editedTask} under stricter conditions.
     * {@code target} must exist in the list.
     * The task identity of {@code editedTask} must not be the same as another existing task in the list.
     */
    void strictSetTask(Task target, Task editedTask);

    /** Returns an unmodifiable view of the filtered task list */
    ObservableList<Task> getFilteredTaskList();

    /**
     * Updates the filter of the filtered task list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTaskList(Predicate<Task> predicate);

    /**
     * Updates the filtered list to be filtered by the {@code PREDICATE_SHOW_ALL_TASKS}.
     */
    void updateToDefaultFilteredTaskList();

    /**
     * Restores the previous predicate used by the filtered list.
     */
    void restorePreviousPredicate();

    /** Returns an unmodifiable view of the sorted task list */
    ObservableList<Task> getSortedTaskList();

    /**
     * Updates the {@code Comparator} of the sorted task list to sort by the given {@code comparator}.
     * @throws NullPointerException if {@code comparator} is null.
     */
    void updateSortedTaskList(Comparator<Task> comparator);

    /**
     * Resets the sortedList to be unordered. The sorted list may preserve the order according to the previous
     * comparator but would not dynamically change when edits are made to it.
     */
    void resetSortedTaskList();

    /**
     * Updates the sortedList to be ordered by the {@code COMPARATOR_SORT_DEADLINE_ASCENDING}.
     */
    void updateToDefaultSortedTaskList();

    /**
     * Updates the source ordering of the task list with the order of the order preserved in the sortedList.
     */
    void updateTaskList();

    /** Returns an unmodifiable view of the tag list */
    Set<Tag> getTagList();
}
