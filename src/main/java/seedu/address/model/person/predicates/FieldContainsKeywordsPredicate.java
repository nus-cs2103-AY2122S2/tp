package seedu.address.model.person.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Field} matches any of the keywords given.
 */
public abstract class FieldContainsKeywordsPredicate implements Predicate<Person> {
}
