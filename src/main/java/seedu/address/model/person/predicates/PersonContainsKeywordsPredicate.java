package seedu.address.model.person.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public abstract class PersonContainsKeywordsPredicate implements Predicate<Person> {
    protected final List<String> keywords;

    public PersonContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }
}
