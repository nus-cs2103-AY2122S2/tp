package seedu.address.model.applicant;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Applicant}'s {@code Name} matches any of the keywords given.
 */
public class ApplicantNamePredicate implements Predicate<Applicant> {
    private final List<String> keywords;

    public ApplicantNamePredicate(List<String> keywords) {
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
                || (other instanceof ApplicantNamePredicate // instanceof handles nulls
                && keywords.equals(((ApplicantNamePredicate) other).keywords)); // state check
    }
}
