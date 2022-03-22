package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Attributes} matches any of the keywords given.
 */
public interface AttributeContainsKeywordsPredicate extends Predicate<Person> {

    boolean test(Person person);
    boolean equals(Object other);
}
