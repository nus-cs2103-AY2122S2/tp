package seedu.address.model.entry;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Event}'s  {@code companyName} matches any of the keywords given.
 */
public class CompanyNameContainsKeywordsPredicate implements Predicate<Event> {
    private final List<String> keywords;

    public CompanyNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Event event) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(event.getCompanyName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CompanyNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((CompanyNameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
