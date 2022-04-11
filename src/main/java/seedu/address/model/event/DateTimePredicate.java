package seedu.address.model.event;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class DateTimePredicate implements Predicate<Event> {
    private final List<DateTime> keywords;
    private final Function<Event, DateTime> field;

    /**
     * Constructor for DateTimePredicate
     * @param keywords
     */
    public DateTimePredicate(List<DateTime> keywords) {
        this.keywords = keywords;
        this.field = event -> event.getDateTime();
    }

    @Override
    public boolean test(Event event) {
        return keywords.stream()
                .anyMatch(keyword -> keyword.equals(field.apply(event)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateTimePredicate // instanceof handles nulls
                && super.equals(other)); // state check
    }
}
