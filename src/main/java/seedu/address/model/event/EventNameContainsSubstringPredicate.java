package seedu.address.model.event;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

/**
 * Tests that a {@code Event}'s {@code name} contains the given substring. Matching is
 * case-insensitive.
 */
public class EventNameContainsSubstringPredicate implements Predicate<Event> {
    private final EventName nameSubstring;

    /**
     * Creates an EventNameContainsSubstringPredicate object with the given substring. If empty
     * string provided, this will return true for all Events.
     *
     * @param nameSubstring Substring to check event name for.
     */
    public EventNameContainsSubstringPredicate(EventName nameSubstring) {
        requireNonNull(nameSubstring);
        this.nameSubstring = nameSubstring;
    }

    @Override
    public boolean test(Event event) {
        return event.hasNameSubstring(nameSubstring);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof EventNameContainsSubstringPredicate)) {
            return false;
        }

        EventNameContainsSubstringPredicate otherPredicate = (EventNameContainsSubstringPredicate) other;
        return otherPredicate.nameSubstring.equals(nameSubstring);
    }
}
