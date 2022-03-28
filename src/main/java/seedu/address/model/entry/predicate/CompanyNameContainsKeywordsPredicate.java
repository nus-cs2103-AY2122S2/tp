package seedu.address.model.entry.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.entry.Entry;
import seedu.address.model.entry.Event;
import seedu.address.model.entry.Person;

/**
 * Tests that a {@code Event}'s or {@code Person}'s  {@code companyName} matches any of the keywords given.
 */
public class CompanyNameContainsKeywordsPredicate implements Predicate<Entry> {
    private final List<String> keywords;

    public CompanyNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Entry entry) {
        if (entry instanceof Event) {
            Event event = (Event) entry;
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(event.getCompanyName().fullName, keyword));
        }
        if (entry instanceof Person) {
            Person person = (Person) entry;
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getCompanyName().fullName, keyword));
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CompanyNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((CompanyNameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
