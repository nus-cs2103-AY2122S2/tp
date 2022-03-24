package seedu.address.model.event;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Event}'s {@code name, date, friend names} matches the given arguments. Matching is
 * case-insensitive.
 */
public class EventFilterPredicate implements Predicate<Event> {
    private final String eventNameSubstring;
    private final boolean isFilteredByDate;
    private final LocalDate date;
    private final List<String> friendNameSubstrings;

    /**
     * Creates an EventFilterPredicate that filters by all the given parameters.
     *
     * @param eventNameSubstring Substring that the event name must contain.
     * @param date Date that the Event must be on.
     * @param friendNameSubstrings Substring that must be found in the event's friend names.
     */
    public EventFilterPredicate(String eventNameSubstring, LocalDate date, List<String> friendNameSubstrings) {
        requireAllNonNull(eventNameSubstring, date, friendNameSubstrings);
        this.eventNameSubstring = eventNameSubstring;
        this.date = date;
        this.friendNameSubstrings = friendNameSubstrings;
        this.isFilteredByDate = true;
    }

    /**
     * Creates an EventFilterPredicate that filters by all the given parameters.
     *
     * @param eventNameSubstring Substring that the event name must contain.
     * @param friendNameSubstrings Substring that must be found in the event's friend names.
     */
    public EventFilterPredicate(String eventNameSubstring, List<String> friendNameSubstrings) {
        requireAllNonNull(eventNameSubstring, friendNameSubstrings);
        this.eventNameSubstring = eventNameSubstring;
        this.date = null;
        this.friendNameSubstrings = friendNameSubstrings;
        this.isFilteredByDate = false;
    }

    @Override
    public boolean test(Event event) {
        boolean eventNameMatch = event.hasNameSubstring(eventNameSubstring);

        boolean friendNamesMatch = friendNameSubstrings.stream()
                .allMatch(event::hasFriendNameSubstring);

        if (isFilteredByDate) {
            boolean eventDateMatch = event.isOnDate(date);
            return (eventNameMatch && eventDateMatch && friendNamesMatch);
        } else {
            return (eventNameMatch && friendNamesMatch);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof EventFilterPredicate)) {
            return false;
        }

        EventFilterPredicate otherPredicate = (EventFilterPredicate) other;
        boolean result = otherPredicate.eventNameSubstring.equals(eventNameSubstring)
                && otherPredicate.isFilteredByDate == isFilteredByDate
                && otherPredicate.friendNameSubstrings.equals(friendNameSubstrings);
        if (isFilteredByDate) {
            result = result && otherPredicate.date.equals(date);
        }
        return result;
    }
}
