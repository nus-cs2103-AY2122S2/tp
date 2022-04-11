package seedu.address.model.person.predicates;

import java.util.List;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Mod} matches any of the keywords given.
 */
public class ModContainsKeywordsPredicate extends PersonContainsKeywordsPredicate {

    public ModContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCaseSubstring(person.getMod().toString(),
                        keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModContainsKeywordsPredicate //
                // instanceof handles nulls
                && keywords.equals(((ModContainsKeywordsPredicate) other).keywords)); // state check
    }

}
