package seedu.address.model.event;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

/**
 * Tests that a {@code Event}'s {@code name} contains the given substring. Matching is
 * case-insensitive.
 */
public class EventNameContainsSubstringPredicate implements Predicate<Event> {
    private final String substring;

    /**
     * Creates an EventNameContainsSubstringPredicate object with the given substring. If empty
     * string provided, this will return true for all Events.
     *
     * @param substring Substring to check event name for.
     */
    public EventNameContainsSubstringPredicate(String substring) {
        requireNonNull(substring);
        this.substring = substring;
    }

    @Override
    public boolean test(Event event) {
        return event.hasNameSubstring(substring);
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
        return otherPredicate.substring.equals(substring);
    }
}
