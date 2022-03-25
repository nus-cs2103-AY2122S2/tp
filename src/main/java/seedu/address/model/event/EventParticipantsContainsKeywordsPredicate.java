package seedu.address.model.event;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

public class EventParticipantsContainsKeywordsPredicate implements Predicate<Event> {
    private final List<String> keywords;
    private final Function<Event, List<String>> field;

    /**
     * Constructor for EventParticipantsContainsKeywordsPredicate
     * @param keywords
     */
    public EventParticipantsContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
        this.field = event -> event.getParticipantStrings();
    }

    @Override
    public boolean test(Event event) {
        return keywords.stream()
                .anyMatch(keyword -> field.apply(event).stream()
                        .anyMatch(detail -> StringUtil.containsWordIgnoreCase(detail, keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventParticipantsContainsKeywordsPredicate // instanceof handles nulls
                && super.equals(other)); // state check
    }
}
