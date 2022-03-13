package seedu.address.model.person.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Email} matches any of the keywords given.
 */
public class EmailContainsKeywordsPredicate extends ContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    /**
     * Creates a new {@link EmailContainsKeywordsPredicate} object with the
     * {@link EmailContainsKeywordsPredicate#keywords} initialised.
     * @param keywords contain keyword(s) to find in {@code Person}'s {@code Email}.
     */
    public EmailContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
        this.keywords = keywords;
    }

    /**
     * Tests if any part of {@code Person}'s {@code Email} matches any of the specified
     * {@link EmailContainsKeywordsPredicate#keywords}.
     * @param person object to retrieve the {@code Email}.
     * @return true if a match is found, and false otherwise.
     */
    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsStringIgnoreCase(person.getEmail().toString(), keyword));
    }

    /**
     * Checks if another object are instances of the same class and contains the same
     * {@link EmailContainsKeywordsPredicate#keywords}.
     * @param other object to compare against.
     * @return true if both objects are instances of {@link EmailContainsKeywordsPredicate} with the same
     * {@link EmailContainsKeywordsPredicate#keywords}.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EmailContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((EmailContainsKeywordsPredicate) other).keywords)); // state check
    }

}
