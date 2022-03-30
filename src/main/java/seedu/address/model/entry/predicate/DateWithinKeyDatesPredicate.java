package seedu.address.model.entry.predicate;

import java.time.LocalDate;
import java.util.function.Predicate;

import seedu.address.model.entry.Date;
import seedu.address.model.entry.Event;

/**
 * Tests that a {@code Event}'s  {@code Date} matches any of the keywords given.
 */
public class DateWithinKeyDatesPredicate implements Predicate<Event> {
    private LocalDate startDate = LocalDate.MIN;
    private LocalDate endDate = LocalDate.MAX;

    /**
     * Creates a DateWithinKeyDatesPredicate object.
     */
    public DateWithinKeyDatesPredicate(Date startDate, Date endDate) {
        if (startDate != null) {
            this.startDate = startDate.getPure();
        }

        if (endDate != null) {
            this.endDate = endDate.getPure();
        }
    }

    @Override
    public boolean test(Event event) {
        LocalDate eventDate = event.getDate().getPure();
        return (eventDate.isEqual(startDate) || eventDate.isAfter(startDate))
                && (eventDate.isEqual(endDate) || eventDate.isBefore(endDate));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateWithinKeyDatesPredicate // instanceof handles nulls
                && startDate.equals(((DateWithinKeyDatesPredicate) other).startDate)
                && endDate.equals(((DateWithinKeyDatesPredicate) other).endDate)); // state check
    }

}
