package seedu.address.model.product;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Product's expiry date in the address book.
 * Guarantees: immutable;
 */
public class ExpiryDate {

    private static final String DATE_FORMAT = "d MMM yyyy";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public final LocalDate expiryDate;

    /**
     * Constructs a {@code ExpiryDate}.
     *
     * @param date A valid date.
     */
    public ExpiryDate(LocalDate date) {
        expiryDate = date;
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
