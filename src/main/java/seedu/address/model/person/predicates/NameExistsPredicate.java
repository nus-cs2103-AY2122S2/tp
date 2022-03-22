package seedu.address.model.person.predicates;

import java.util.function.Predicate;

import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Name} matches exactly with the keyword given.
 */
public class NameExistsPredicate implements Predicate<Person> {
    private final Name keyword;

    public NameExistsPredicate(Name keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Person person) {
        return keyword.equalsIgnoreCasing(person.getName());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameExistsPredicate // instanceof handles nulls
                && keyword.equals(((NameExistsPredicate) other).keyword)); // state check
    }
}
