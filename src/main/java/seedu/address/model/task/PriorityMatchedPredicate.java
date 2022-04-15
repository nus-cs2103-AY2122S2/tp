package seedu.address.model.task;

import java.util.Set;
import java.util.function.Predicate;

/**
 * Tests that a {@code Task}'s {@code Priority} matches any of the priorities given.
 */
public class PriorityMatchedPredicate implements Predicate<Task> {

    private final Set<Priority> prioritySet;

    public PriorityMatchedPredicate(Set<Priority> prioritySet) {
        this.prioritySet = prioritySet;
    }

    @Override
    public boolean test(Task task) {
        if (prioritySet.size() == 0) {
            return true;
        }

        return prioritySet.stream().anyMatch(priority -> priority.equals(task.getPriority()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PriorityMatchedPredicate // instanceof handles nulls
                && prioritySet.equals(((PriorityMatchedPredicate) other).prioritySet)); // state check
    }
}
