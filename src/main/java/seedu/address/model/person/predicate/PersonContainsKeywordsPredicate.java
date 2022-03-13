package seedu.address.model.person.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person} matches any of the keywords given.
 */
public class PersonContainsKeywordsPredicate extends ContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    /**
     * Creates a new {@link PersonContainsKeywordsPredicate} object with the
     * {@link PersonContainsKeywordsPredicate#keywords} initialised.
     * @param keywords contain keyword(s) to find in {@code Person}.
     */
    public PersonContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
        this.keywords = keywords;
    }

    /**
     * Tests if any part of {@code Person} matches any of the specified
     * {@link PersonContainsKeywordsPredicate#keywords}.
     * @param person object to retrieve the description.
     * @return true if a match is found, and false otherwise.
     */
    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsStringIgnoreCase(person.toString(), keyword));
    }

    /**
     * Checks if another object are instances of the same class and contains the same
     * {@link PersonContainsKeywordsPredicate#keywords}.
     * @param other object to compare against.
     * @return true if both objects are instances of {@link PersonContainsKeywordsPredicate} with the same
     * {@link PersonContainsKeywordsPredicate#keywords}.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PersonContainsKeywordsPredicate) other).keywords)); // state check
    }

}
