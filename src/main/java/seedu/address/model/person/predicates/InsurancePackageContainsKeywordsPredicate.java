package seedu.address.model.person.predicates;

import java.util.List;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code InsurancePackage} matches any of the keywords given.
 */
public class InsurancePackageContainsKeywordsPredicate extends FieldContainsKeywordsPredicate {
    private final List<String> keywords;

    public InsurancePackageContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getInsurancePackage().getPackageName(),
                        keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InsurancePackageContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((InsurancePackageContainsKeywordsPredicate) other).keywords)); // state check
    }
}
