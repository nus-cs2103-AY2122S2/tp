package manageezpz.model.task;

import java.util.function.Predicate;

public class TaskContainsKeywordsPredicate implements Predicate<Task> {
    @Override
    public boolean test(Task task) {
        return false;
    }
}
