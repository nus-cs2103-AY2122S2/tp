package seedu.address.model.tasks;

import static java.util.Objects.requireNonNull;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.tasks.exceptions.DuplicateTaskException;
import seedu.address.model.tasks.exceptions.TaskNotFoundException;

/**
 * Wraps all data at the task list level
 * Duplicates are not allowed (by equals comparison)
 */
public class PriorityTaskList implements ReadOnlyTaskList {

    /** The internal representation of the task list is an ObservableList.*/
    private final ObservableList<Task> internalList = FXCollections.observableArrayList();

    /**
     * Constructs a PriorityTaskList. This should be used as the default.
     */
    public PriorityTaskList() {}

    /**
     * Constructs a PriorityTaskList with a given ObservableList to copy from.
     * @param list the ObservableList to copy from.
     */
    public PriorityTaskList(ObservableList<Task> list) {
        this.internalList.clear();
        this.internalList.addAll(list);
    }

    /**
     * Constructs a PriorityTaskList with a given ReadOnlyTaskList to copy from.
     *
     * @param other the ReadOnlyTaskList to copy from.
     */
    public PriorityTaskList(ReadOnlyTaskList other) {
        requireNonNull(other);
        this.internalList.clear();
        this.internalList.addAll(other.getInternalList());
    }

    /**
     * Resets the existing data of this {@code PriorityTaskList} with {@code newData}.
     */
    public void resetData(ReadOnlyTaskList newData) {
        requireNonNull(newData);
        this.internalList.clear();
        this.internalList.addAll(newData.getInternalList());
    }

    /**
     * Adds a task using priority considerations. Priority is based on the task's deadlines.
     *
     * @param task the task to add.
     * @return true if the addition was successful.
     */
    @Override
    public boolean add(Task task) {
        requireNonNull(task);
        if (this.contains(task)) {
            throw new DuplicateTaskException();
        }
        if (internalList.isEmpty()) {
            internalList.add(task);
            return true;
        }

        ObservableList<Task> copy = FXCollections.observableArrayList();

        boolean hasBeenAdded = false;
        for (int i = 0; i < this.size(); i++) {
            Task t = internalList.get(i);
            if (!hasBeenAdded) {
                int compared = t.compareTo(task);
                if (compared > 0) {
                    copy.add(task);
                    hasBeenAdded = true;
                }
            }
            copy.add(t);
            if (!hasBeenAdded && i == size() - 1) {
                copy.add(task);
            }
        }
        this.internalList.clear();
        this.internalList.addAll(copy);
        return true;
    }

    @Override
    public boolean remove(Task task) {
        requireNonNull(task);
        if (!internalList.remove(task)) {
            throw new TaskNotFoundException();
        }
        return true;
    }


    @Override
    public boolean contains(Task task) {
        requireNonNull(task);
        return internalList.stream().anyMatch(task::equals);
    }

    @Override
    public Task get(int index) {
        if (index < 0 || index >= size()) {
            throw new TaskNotFoundException();
        }
        return internalList.get(index);
    }

    @Override
    public ReadOnlyTaskList getCopy() {
        return new PriorityTaskList(this.internalList);
    }

    @Override
    public ObservableList<Task> getInternalList() {
        return internalList;
    }

    @Override
    public int size() {
        return internalList.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PriorityTaskList that = (PriorityTaskList) o;
        return internalList.stream().allMatch(that::contains) && that.internalList.stream().allMatch(this::contains);
    }
}
