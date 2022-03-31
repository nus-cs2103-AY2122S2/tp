package seedu.address.model.event;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

/**
 * Tests that at least one of {@code Event}'s {@code friend names} contains the given substring. Matching is
 * case-insensitive.
 */
public class EventFriendNamesContainSubstringPredicate implements Predicate<Event>{
    private final String substring;

    /**
     * Creates an EventNameContainsSubstringPredicate object with the given substring. If empty
     * string provided, this will return true for all Events.
     *
     * @param substring Substring to check event friend names for.
     */
    public EventFriendNamesContainSubstringPredicate(String substring) {
        requireNonNull(substring);
        this.substring = substring;
    }

    @Override
    public boolean test(Event event) {
        return event.hasFriendNameSubstring(substring);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof EventFriendNamesContainSubstringPredicate)) {
            return false;
        }

        EventFriendNamesContainSubstringPredicate otherPredicate = (EventFriendNamesContainSubstringPredicate) other;
        return otherPredicate.substring.equals(substring);
    }
}
