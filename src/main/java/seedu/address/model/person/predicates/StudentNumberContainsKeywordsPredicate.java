package seedu.address.model.person.predicates;

import java.util.List;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code StudentNumber} matches any of the keywords given.
 */
public class StudentNumberContainsKeywordsPredicate extends PersonContainsKeywordsPredicate {

    public StudentNumberContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword ->
                        StringUtil.containsWordIgnoreCaseSubstring(person.getStudentNumber().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentNumberContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((StudentNumberContainsKeywordsPredicate) other).keywords)); // state check
    }

}
