package seedu.ibook.model.product;

import static java.util.Objects.requireNonNull;
import static seedu.ibook.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Product's expiry date in the ibook.
 * Guarantees: immutable;
 */
public class ExpiryDate {

    // TODO : Add a message constraint
    public static final String MESSAGE_CONSTRAINTS = "";

    private static final String DATE_FORMAT = "yyyy-MM-dd"; // default iso format
    private static final String PRETTY_DATE_FORMAT = "d MMM yyyy";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PRETTY_DATE_FORMAT);

    public final String expiryDateString;
    public final LocalDate expiryDate;

    /**
     * Constructs a {@code ExpiryDate}.
     *
     * @param date A valid date.
     */
    public ExpiryDate(String date) {
        requireNonNull(date);
        checkArgument(isValidExpiryDate(date), MESSAGE_CONSTRAINTS);
        expiryDateString = date;
        expiryDate = LocalDate.parse(date);
    }

    /**
     * Returns true if a given {@code LocalDate} is a valid expiry date.
     */
    public static boolean isValidExpiryDate(String date) {
        try {
            LocalDate.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
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
