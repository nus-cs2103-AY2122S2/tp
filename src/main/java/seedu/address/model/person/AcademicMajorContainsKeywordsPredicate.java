package seedu.address.model.person;

import java.util.List;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Academic Major} matches any of the keywords given.
 */
public class AcademicMajorContainsKeywordsPredicate implements AttributeContainsKeywordsPredicate {
    private final List<String> keywords;

    public AcademicMajorContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword ->
                        StringUtil.containsWordIgnoreCase(person.getAcademicMajor().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AcademicMajorContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((AcademicMajorContainsKeywordsPredicate) other).keywords)); // state check
    }

}
