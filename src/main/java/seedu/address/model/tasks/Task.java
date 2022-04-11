package seedu.address.model.tasks;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Task in the Task List.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task implements Comparable<Task> {
    private final Description description;
    private final Deadline deadline;

    /**
     * Constructs a Task. All fields must not be null.
     */
    public Task(Description description, Deadline deadline) {
        requireAllNonNull(description, deadline);
        this.description = description;
        this.deadline = deadline;
    }

    public Description getDescription() {
        return description;
    }

    public Deadline getDeadline() {
        return deadline;
    }

    @Override
    public int compareTo(Task other) {
        return this.deadline.compareTo(other.deadline);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Task // instanceof handles nulls
                && description.equals(((Task) other).description)
                && deadline.equals(((Task) other).deadline)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, deadline);
    }

    @Override
    public String toString() {
        return String.format("%s, %s", description, deadline.toStringNiceFormat());
    }
}
