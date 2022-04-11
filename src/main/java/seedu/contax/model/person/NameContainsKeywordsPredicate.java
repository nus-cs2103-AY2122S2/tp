package seedu.contax.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.contax.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate extends ContainsKeywordsPredicate implements Predicate<Person> {

    public NameContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Person person) {
        return super.getKeywords().stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && super.getKeywords().equals(((NameContainsKeywordsPredicate) other).getKeywords())); // state check
    }
}
