package seedu.address.model.task;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.core.keyword.Keyword;

/**
 * Tests that a {@code Task}'s {@code Description} matches any of the keywords given.
 */
public class DescriptionContainsKeywordsPredicate implements Predicate<Task> {

    private final Set<Keyword> keywords;

    public DescriptionContainsKeywordsPredicate(Set<Keyword> keywords) {
        this.keywords = new HashSet<>(keywords);
    }

    @Override
    public boolean test(Task task) {
        if (keywords.size() == 0) {
            return true;
        }
        return keywords.stream()
                .anyMatch(keyword -> Keyword
                        .containsKeywordIgnorePunctuationAndCase(task.getDescription().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DescriptionContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((DescriptionContainsKeywordsPredicate) other).keywords)); // state check
    }
}
