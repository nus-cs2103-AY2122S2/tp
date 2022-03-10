package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Field} matches any of the keywords given.
 */
public abstract class FieldContainsKeywordsPredicate implements Predicate<Person> {
}
