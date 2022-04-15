package seedu.address.model.tag;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Tag} matches any of the keywords given.
 */
public class PersonTagContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public PersonTagContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> person.getTags().stream()
                        .anyMatch(tag -> StringUtil.containsWordIgnoreCase(tag.tagName, keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonTagContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PersonTagContainsKeywordsPredicate) other).keywords)); // state check
    }

}
