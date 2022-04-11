package seedu.address.model.event;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class EventFieldContainsKeywordsPredicate implements Predicate<Event> {
    private final List<String> keywords;
    private final Function<Event, String> field;

    EventFieldContainsKeywordsPredicate(List<String> keywords, Function<Event, String> field) {
        this.keywords = keywords;
        this.field = field;
    }

    @Override
    public boolean test(Event event) {
        return keywords.stream()
                .anyMatch(keyword -> field.apply(event).toLowerCase().contains(keyword.toLowerCase()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventFieldContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((EventFieldContainsKeywordsPredicate) other).keywords)); // state check
    }
}
