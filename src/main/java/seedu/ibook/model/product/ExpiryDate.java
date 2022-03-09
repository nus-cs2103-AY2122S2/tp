package seedu.ibook.model.product;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Product's expiry date in the ibook.
 * Guarantees: immutable;
 */
public class ExpiryDate {

    private static class WildExpiryDate extends ExpiryDate {
        private WildExpiryDate() {};

        @Override
        public boolean equals(Object other) {
            if (other instanceof ExpiryDate) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static final ExpiryDate WILDEXPIRYDATE = new WildExpiryDate();
    // TODO : Add a message constraint
    public static final String MESSAGE_CONSTRAINTS = "";

    private static final String DATE_FORMAT = "d MMM yyyy";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public final LocalDate expiryDate;

    private ExpiryDate() {
        expiryDate = LocalDate.parse("0000-01-01");
    }

    /**
     * Constructs a {@code ExpiryDate}.
     *
     * @param date A valid date.
     */
    public ExpiryDate(LocalDate date) {
        expiryDate = date;
    }

    /**
     * Returns true if a given {@code LocalDate} is a valid expiry date.
     */
    public static boolean isValidExpiryDate(LocalDate expiryDate) {
        // TODO : implement this method
        return true;
    }

    public boolean isPast() {
        return expiryDate.isBefore(LocalDate.now());
    }

    @Override
    public String toString() {
        return expiryDate.format(FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExpiryDate // instanceof handles nulls
                && expiryDate.equals(((ExpiryDate) other).expiryDate)); // state check
    }

    @Override
    public int hashCode() {
        return expiryDate.hashCode();
    }

}
