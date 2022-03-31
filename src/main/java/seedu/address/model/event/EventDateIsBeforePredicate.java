package seedu.address.model.event;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.function.Predicate;

/**
 * Tests that {@code Event}'s {@code date} is before the given date.
 */
public class EventDateIsBeforePredicate implements Predicate<Event> {
    private final LocalDate date;

    /**
     * Creates an EventDateIsBeforePredicate object with the given date.
     *
     * @param date Date to check that event falls on or before.
     */
    public EventDateIsBeforePredicate(LocalDate date) {
        requireNonNull(date);
        this.date = date;
    }

    @Override
    public boolean test(Event event) {
        return event.isBeforeDate(date);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof EventDateIsBeforePredicate)) {
            return false;
        }

        EventDateIsBeforePredicate otherPredicate = (EventDateIsBeforePredicate) other;
        return otherPredicate.date.equals(date);
    }
}
