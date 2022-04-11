package seedu.address.model.applicant;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

public class NameApplicantContainsKeywordsPredicate implements Predicate<Applicant> {
    private final List<String> keywords;

    public NameApplicantContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Applicant applicant) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(applicant.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameApplicantContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameApplicantContainsKeywordsPredicate) other).keywords)); // state check
    }
}
