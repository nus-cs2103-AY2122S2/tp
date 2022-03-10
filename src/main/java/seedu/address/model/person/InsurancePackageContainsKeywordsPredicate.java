package seedu.address.model.person;

import java.util.List;

import seedu.address.commons.util.StringUtil;

public class InsurancePackageContainsKeywordsPredicate extends FieldContainsKeywordsPredicate {
    private final List<String> keywords;

    public InsurancePackageContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getInsurancePackage().packageName,
                        keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InsurancePackageContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((InsurancePackageContainsKeywordsPredicate) other).keywords)); // state check
    }
}
