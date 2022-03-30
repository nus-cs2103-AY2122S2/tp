package seedu.address.model.applicant;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.tag.Tag;

/**
 * Tests that an {@code Applicant} has a {@code Tag} that matches any of the keywords given.
 */
public class ApplicantTagPredicate implements Predicate<Applicant> {
    private final List<String> keywords;

    public ApplicantTagPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Applicant applicant) {
        for (Tag tag : applicant.getTags()) {
            if (keywords.stream().anyMatch(keyword -> StringUtil.containsWordIgnoreCase(tag.tagName, keyword))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ApplicantTagPredicate // instanceof handles nulls
                && keywords.equals(((ApplicantTagPredicate) other).keywords)); // state check
    }
}
