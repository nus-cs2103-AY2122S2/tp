package seedu.address.model.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.person.Person;

/**
 * Tests that any data of a {@code Person} matches any of the keywords given.
 */
public class PersonContainsKeywordsPredicate implements Predicate<Person> {

    public static final String COMMAND_WORD = "all";

    private final List<String> keywords;

    public PersonContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return new NameContainsKeywordsPredicate(keywords).test(person)
                || new PhoneContainsKeywordsPredicate(keywords).test(person)
                || new EmailContainsKeywordsPredicate(keywords).test(person)
                || new AddressContainsKeywordsPredicate(keywords).test(person)
                || new PropertiesContainsKeywordsPredicate(keywords).test(person)
                || new PreferenceContainsKeywordsPredicate(keywords).test(person)
                || new UserTypeContainsKeywordsPredicate(keywords).test(person);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PersonContainsKeywordsPredicate) other).keywords)); // state check
    }

}
