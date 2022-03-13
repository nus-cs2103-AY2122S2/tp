package seedu.address.model.person.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code StudentID} matches any of the keywords given.
 */
public class StudentIdContainsKeywordsPredicate extends ContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    /**
     * Creates a new {@link StudentIdContainsKeywordsPredicate} object with the
     * {@link StudentIdContainsKeywordsPredicate#keywords} initialised.
     * @param keywords contain keyword(s) to find in {@code Person}'s {@code StudentID}.
     */
    public StudentIdContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
        this.keywords = keywords;
    }

    /**
     * Tests if any part of {@code Person}'s {@code StudentID} matches any of the specified
     * {@link StudentIdContainsKeywordsPredicate#keywords}.
     * @param person object to retrieve the {@code StudentID}.
     * @return true if a match is found, and false otherwise.
     */
    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsStringIgnoreCase(person.getStudentID().toString(), keyword));
    }

    /**
     * Checks if another object are instances of the same class and contains the same
     * {@link StudentIdContainsKeywordsPredicate#keywords}.
     * @param other object to compare against.
     * @return true if both objects are instances of {@link StudentIdContainsKeywordsPredicate} with the same
     * {@link StudentIdContainsKeywordsPredicate#keywords}.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentIdContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((StudentIdContainsKeywordsPredicate) other).keywords)); // state check
    }

}
