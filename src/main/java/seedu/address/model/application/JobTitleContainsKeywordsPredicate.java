package seedu.address.model.application;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Application}'s {@code JobTitle} matches any of the keywords given.
 */
public class JobTitleContainsKeywordsPredicate implements Predicate<Application> {
    private final List<String> keywords;

    public JobTitleContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Application application) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(application.getJobTitle().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof JobTitleContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((JobTitleContainsKeywordsPredicate) other).keywords)); // state check
    }

}
