package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Tag} matches any of the keywords given.
 */
public class TagContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    /**
     * Constructs a TagContainKeywordsPredicate to test if a {@code Person} has all the tags listed in the {@code
     * List<String>} of keywords
     * @param keywords the keywords to check
     */
    public TagContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        boolean hasAllMatches = true;

        if (keywords.size() == 0) {
            return false;
        }

        for (String keyword : keywords) {
            boolean doesKeywordMatchAnyTags = person.getTags().stream()
                    .anyMatch(tag -> StringUtil.containsWordIgnoreCase(tag.tagName, keyword));
            hasAllMatches = hasAllMatches && doesKeywordMatchAnyTags;
        }

        return hasAllMatches;
    }

    public List<String> getMatchedKeyowrds() {
        return this.keywords;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TagContainsKeywordsPredicate) other).keywords)); // state check
    }

}
