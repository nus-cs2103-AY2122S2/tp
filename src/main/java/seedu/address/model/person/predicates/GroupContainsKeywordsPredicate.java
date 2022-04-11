package seedu.address.model.person.predicates;

import java.util.List;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Group} matches any of the keywords given.
 */
public class GroupContainsKeywordsPredicate extends PersonContainsKeywordsPredicate {

    public GroupContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCaseSubstring(person.getGroup().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GroupContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((GroupContainsKeywordsPredicate) other).keywords)); // state check
    }

}
