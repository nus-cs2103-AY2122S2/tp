package seedu.address.model.entry.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.entry.Event;

/**
 * Tests that a {@code Event}'s  {@code Time} matches any of the keywords given.
 */
public class TimeContainsKeywordsPredicate implements Predicate<Event> {
    private final List<String> keywords;

    public TimeContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Event event) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(event.getTime().getPure().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TimeContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TimeContainsKeywordsPredicate) other).keywords)); // state check
    }

}
