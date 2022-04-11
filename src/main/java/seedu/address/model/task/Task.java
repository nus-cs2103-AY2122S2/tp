package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents a Task's name in the ArchDuke.
 * Guarantees: immutable; is valid as declared in {@link #isValidTaskName(String)}
 */
public class Task {

    public final TaskName taskName;

    /**
     * Constructs a {@code Task}.
     *
     * @param task A valid task.
     */
    public Task(TaskName task) {
        requireNonNull(task);
        this.taskName = task;
    }

    public TaskName getTaskName() {
        return taskName;
    }

    /**
     * Compares as a weaker notion of identity between two tasks.
     *
     * @param otherTask Another Task object.
     * @return true as a boolean value if both tasks are the same.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getTaskName().toString().equalsIgnoreCase(getTaskName().toString());
    }


    /**
     * Checks if both tasks are the same.
     * This defines a stronger notion of equality between two tasks.
     *
     * @param other Another task object.
     * @return true as a boolean value if both tasks are the same.
     */
    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles null
        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        return otherTask.getTaskName().toString().equalsIgnoreCase(getTaskName().toString());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(taskName);
    }

    /**
     * Returns a String representation of a task.
     *
     * @return String representation of a task.
     */
    @Override
    public String toString() {
        return String.valueOf(taskName.getTaskName());
    }
}
