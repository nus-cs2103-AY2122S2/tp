package seedu.address.model.tag;

import java.util.List;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.AttributeContainsKeywordsPredicate;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class TagContainsKeywordsPredicate implements AttributeContainsKeywordsPredicate {
    private final List<String> keywords;

    public TagContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        for (Tag tag: person.getTags()) {
            if (keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(tag.tagName, keyword)) == true) {
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
