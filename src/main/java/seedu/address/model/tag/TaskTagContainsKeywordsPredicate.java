package seedu.address.model.tag;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.task.Task;

/**
 * Tests that a {@code Task}'s {@code Tag} matches any of the keywords given.
 */
public class TaskTagContainsKeywordsPredicate implements Predicate<Task> {
    private final List<String> keywords;

    public TaskTagContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Task task) {
        return keywords.stream()
                .anyMatch(keyword -> task.getTags().stream()
                        .anyMatch(tag -> StringUtil.containsWordIgnoreCase(tag.tagName, keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskTagContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TaskTagContainsKeywordsPredicate) other).keywords)); // state check
    }

}
