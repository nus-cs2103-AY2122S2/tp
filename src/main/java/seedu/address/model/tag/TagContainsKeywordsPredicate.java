package seedu.address.model.tag;

import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.core.keyword.Keyword;
import seedu.address.model.task.Task;

/**
 * Tests that a {@code Task}'s {@code Tags} matches any of the keywords given.
 */
public class TagContainsKeywordsPredicate implements Predicate<Task> {

    private final Set<Keyword> keywords;

    /**
     * Constructor for TagContainsKeywordsPredicate.
     *
     * @param keywords The set of keywords to search for.
     */
    public TagContainsKeywordsPredicate(Set<Keyword> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Task task) {
        if (keywords.size() == 0) {
            return true;
        }
        Set<Tag> tags = task.getTags();
        for (Tag tag : tags) {
            boolean containsKeyword = keywords.stream()
                .anyMatch(keyword -> Keyword.containsKeywordIgnorePunctuationAndCase(tag.tagName, keyword));
            if (containsKeyword) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TagContainsKeywordsPredicate) other).keywords)); // state check
    }
}
