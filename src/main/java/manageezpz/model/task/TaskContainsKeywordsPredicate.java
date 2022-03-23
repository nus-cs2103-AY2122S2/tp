package manageezpz.model.task;

import java.util.List;
import java.util.function.Predicate;

import manageezpz.commons.util.StringUtil;

/**
 * Finds tasks with the given keywords. The keywords are case-insensitive.
 */
public class TaskContainsKeywordsPredicate implements Predicate<Task> {
    private final List<String> keywords;

    /**
     * The constructor for the TaskContainsKeywordsPredicate.
     * @param keywords The keywords as the search term.
     */
    public TaskContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean test(Task task) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(task.getDescription().toString(), keyword));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TaskContainsKeywordsPredicate) other).keywords)); // state check
    }
}
