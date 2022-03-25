package seedu.address.model.event;

import java.util.List;

public class EventNameContainsKeywordsPredicate extends EventFieldContainsKeywordsPredicate {
    public EventNameContainsKeywordsPredicate(List<String> keywords) {
        super(keywords, event -> event.getEventName().value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventNameContainsKeywordsPredicate // instanceof handles nulls
                && super.equals(other)); // state check
    }
}
