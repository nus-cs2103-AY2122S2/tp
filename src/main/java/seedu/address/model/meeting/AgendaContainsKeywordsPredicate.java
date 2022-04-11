package seedu.address.model.meeting;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

public class AgendaContainsKeywordsPredicate implements Predicate<Meeting> {
    private final List<String> keywords;

    public AgendaContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Meeting meeting) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(meeting.getAgenda().description, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AgendaContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((AgendaContainsKeywordsPredicate) other).keywords)); // state check
    }
}
