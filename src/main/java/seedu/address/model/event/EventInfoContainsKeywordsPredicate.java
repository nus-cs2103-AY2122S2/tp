package seedu.address.model.event;

import java.util.List;

public class EventInfoContainsKeywordsPredicate extends EventFieldContainsKeywordsPredicate {
    public EventInfoContainsKeywordsPredicate(List<String> keywords) {
        super(keywords, event -> event.getEventInfo().value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventInfoContainsKeywordsPredicate // instanceof handles nulls
                && super.equals(other)); // state check
    }
}
