package seedu.address.model.event;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.function.Predicate;

/**
 * Tests that {@code Event}'s {@code date} is after the given date.
 */
public class EventDateIsAfterPredicate implements Predicate<Event> {
    private final LocalDate date;

    /**
     * Creates an EventDateIsAfterPredicate object with the given date.
     *
     * @param date Date to check that event falls on or after.
     */
    public EventDateIsAfterPredicate(LocalDate date) {
        requireNonNull(date);
        this.date = date;
    }

    @Override
    public boolean test(Event event) {
        return event.isAfterDate(date);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof EventDateIsAfterPredicate)) {
            return false;
        }

        EventDateIsAfterPredicate otherPredicate = (EventDateIsAfterPredicate) other;
        return otherPredicate.date.equals(date);
    }
}
