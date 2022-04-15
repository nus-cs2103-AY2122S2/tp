package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Task's priority in the task list
 * Guarantees: immutable;
 */
public enum Priority {

    // Natural ordering of the enum is in the order which constants are declared.
    // Order is necessary for sort by Priority.
    LOW("low"),
    MEDIUM("medium"),
    HIGH("high");

    public static final String MESSAGE_CONSTRAINTS =
            "Priority should be in the format low, medium, or high";

    public final String value;

    Priority(String priority) {
        requireNonNull(priority);
        this.value = priority;
    }

    /**
     * Returns true if a given string is a valid priority.
     */
    public static boolean isValidPriority(String priority) {
        requireNonNull(priority);
        for (Priority p : Priority.values()) {
            if (p.value.equals(priority)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the enum based on its value.
     */
    public static Priority valueOfLabel(String label) {
        requireNonNull(label);
        for (Priority e : values()) {
            if (e.value.equals(label)) {
                return e;
            }
        }
        throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
    }
}
