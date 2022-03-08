package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code ClassCode} matches any of the keywords given.
 */
public class ClassCodeContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public ClassCodeContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getClassCode().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClassCodeContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ClassCodeContainsKeywordsPredicate) other).keywords)); // state check
    }

}