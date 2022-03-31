package manageezpz.model.task;

import static java.util.Objects.requireNonNull;
import static manageezpz.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import manageezpz.model.person.Person;
import manageezpz.model.task.exceptions.DuplicateTaskException;
import manageezpz.model.task.exceptions.InvalidTaskTypeException;
import manageezpz.model.task.exceptions.TaskNotFoundException;

public class UniqueTaskList implements Iterable<Task> {
    private final ObservableList<Task> internalList = FXCollections.observableArrayList();
    private final ObservableList<Task> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);
    /**
     * Returns true if the list contains an equivalent Task as the given argument.
     */
    public boolean contains(Task toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameTask);
    }

    /**
     * Adds a task to the list.
     * The task must not already exist in the list.
     */
    public void add(Task toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTaskException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the task {@code target} in the list with {@code editedTask}.
     * {@code target} must exist in the list.
     * The task identity of {@code editedTask} must not be the same as another existing task in the list.
     */
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new TaskNotFoundException();
        }

        if (!target.isSameTask(editedTask) && contains(editedTask)) {
            throw new DuplicateTaskException();
        }

        internalList.set(index, editedTask);
    }

    /**
     * Updates the task with the edited person.
     * @param toUpdate the task to be updated.
     * @param assigneesIndex the index of the person in List of assignees.
     * @param editedPerson the edited person.
     */
    public void updateTaskWithEditedPerson(Task toUpdate, int assigneesIndex, Person editedPerson) {
        requireAllNonNull(toUpdate, assigneesIndex, editedPerson);

        Task updatedTask = duplicateTask(toUpdate);
        updatedTask.assignedTo(assigneesIndex, editedPerson);

        setTask(toUpdate, updatedTask);
    }

    /**
     * Marks a task in the list as done.
     * The task must already exist in the list.
     */
    public Task markTask(Task toMark) {
        requireNonNull(toMark);

        Task markedTask = duplicateTask(toMark);
        markedTask.setTaskDone();

        setTask(toMark, markedTask);

        return markedTask;
    }

    /**
     * Unmarks a task in the list as not done yet.
     * The task must already exist in the list.
     */
    public Task unmarkTask(Task toUnmark) {
        requireNonNull(toUnmark);

        Task unmarkedTask = duplicateTask(toUnmark);
        unmarkedTask.setTaskNotDone();

        setTask(toUnmark, unmarkedTask);

        return unmarkedTask;
    }

    /**
     * Tags a priority to the task.
     */
    public Task tagPriorityToTask(Task toTagPriority, Priority priority) {
        requireNonNull(toTagPriority);
        requireNonNull(priority);

        Task taggedPriorityTask = duplicateTask(toTagPriority);
        taggedPriorityTask.setPriority(priority);

        setTask(toTagPriority, taggedPriorityTask);

        return taggedPriorityTask;
    }

    /**
     * Tags an employee to the task.
     */
    public Task tagEmployeeToTask(Task toTagEmployee, Person person) {
        requireNonNull(toTagEmployee);
        requireNonNull(person);

        Task taggedEmployeeTask = duplicateTask(toTagEmployee);
        taggedEmployeeTask.assignedTo(person);

        setTask(toTagEmployee, taggedEmployeeTask);

        return taggedEmployeeTask;
    }

    /**
     * Untags an employee to the task.
     */
    public Task untagEmployeeFromTask(Task toUntagEmployee, Person person) {
        requireNonNull(toUntagEmployee);
        requireNonNull(person);

        Task untaggedEmployeeTask = duplicateTask(toUntagEmployee);
        untaggedEmployeeTask.removeAssigned(person);

        setTask(toUntagEmployee, untaggedEmployeeTask);

        return untaggedEmployeeTask;
    }

    private Task duplicateTask(Task task) {
        if (task instanceof Todo) {
            return new Todo((Todo) task);
        } else if (task instanceof Deadline) {
            return new Deadline((Deadline) task);
        } else if (task instanceof Event) {
            return new Event((Event) task);
        } else {
            // The else statement should not be reached since there are
            // only three types of tasks, i.e., todo, deadline and event
            throw new InvalidTaskTypeException();
        }
    }

    /**
     * Removes the equivalent task from the list.
     * The task must exist in the list.
     */
    public void remove(Task toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TaskNotFoundException();
        }
    }

    public void setTasks(UniqueTaskList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code tasks}.
     * {@code tasks} must not contain duplicate tasks.
     */
    public void setTasks(List<Task> tasks) {
        requireAllNonNull(tasks);
        if (!tasksAreUnique(tasks)) {
            throw new DuplicateTaskException();
        }

        internalList.setAll(tasks);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Task> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Task> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueTaskList // instanceof handles nulls
                && internalList.equals(((UniqueTaskList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code persons} contains only unique Task.
     */
    private boolean tasksAreUnique(List<Task> tasks) {
        for (int i = 0; i < tasks.size() - 1; i++) {
            for (int j = i + 1; j < tasks.size(); j++) {
                if (tasks.get(i).isSameTask(tasks.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
