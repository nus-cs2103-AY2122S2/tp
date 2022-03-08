package seedu.contax.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.contax.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Email} matches any of the keywords given.
 */
public class EmailContainsKeywordsPredicate extends ContainsKeywordsPredicate implements Predicate<Person> {

    public EmailContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Person person) {
        return super.getKeywords().stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getEmail().toString(), keyword));
    }
}
