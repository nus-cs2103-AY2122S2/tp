package seedu.address.model.task;

import java.util.function.Predicate;

/**
 * Represents the predicate used to check whether a task is completed or not.
 */
public class CompletionStatusPredicate implements Predicate<Task> {

    // The required completion status
    private final CompletionStatus completionStatus;

    /**
     * Constructs a {@code CompletionStatusPredicate}.
     * Always return true if the {@code CompletionStatus} given is null.
     *
     * @param completionStatus the required {@code CompletionStatus}.
     */
    public CompletionStatusPredicate(CompletionStatus completionStatus) {
        this.completionStatus = completionStatus;
    }

    @Override
    public boolean test(Task task) {
        if (completionStatus == null) {
            return true;
        }
        return task.getCompletionStatus().equals(this.completionStatus);
    }
}
