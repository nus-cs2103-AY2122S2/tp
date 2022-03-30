package seedu.address.model.entry.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.entry.Event;

/**
 * Tests that a {@code Event}'s  {@code Date} matches any of the keywords given.
 */
public class DateContainsKeywordsPredicate implements Predicate<Event> {
    private final List<String> keywords;

    public DateContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Event event) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(event.getDate().getPure().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((DateContainsKeywordsPredicate) other).keywords)); // state check
    }

}
