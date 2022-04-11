package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Represents a Person's contacted status in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidContactedDate(String)}
 */
public class ContactedDate {

    /** String message that represents the prefix last contacted on. */
    public static final String MESSAGE_CONTACTED_PREFIX = "Last contacted on %s";

    /** String message that represents not contacted. */
    public static final String MESSAGE_NOT_CONTACTED = "Not contacted";

    /** String that represents the dd-mm-yyyy date format. */
    public static final String DATE_FORMAT = "dd-MM-uuuu";

    /**
     * A strict {@code DateTimeFormatter} of {@code DATE_FORMAT}, strict enforces and prevents invalid leap dates
     * and invalid dates such as February 30.
     */
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT)
            .withResolverStyle(ResolverStyle.STRICT);

    /** String message that represents message constraints. */
    public static final String MESSAGE_CONSTRAINTS = "Last contacted date can only either be empty or "
            + "a VALID AD date following the dd-mm-yyyy format that is not in the future.";

    /** A static {@code contactedDate} object that represents an empty contacted date. */
    public static final ContactedDate EMPTY_CONTACTED_DATE = new ContactedDate("");

    /** String representation of {@code ContactedDate}. */
    public final String contactedDate;

    /**
     * Constructs a {@code ContactedDate}.
     *
     * @param contactedDate A valid contacted date.
     */
    public ContactedDate(String contactedDate) {
        requireNonNull(contactedDate);
        checkArgument(isValidContactedDate(contactedDate), MESSAGE_CONSTRAINTS);
        this.contactedDate = contactedDate;
    }

    /**
     * Returns true if a given string is a valid contacted date.
     *
     * @param contactedDate A contacted date to be checked for validity.
     * @return If valid true; otherwise false.
     */
    public static boolean isValidContactedDate(String contactedDate) {
        if (contactedDate == null) {
            return false;
        }
        return contactedDate.isEmpty() || isValidDate(contactedDate);
    }

    /**
     * Returns true if a given string is a valid date that follows the {@code DATE_FORMAT} and is not in the future,
     * otherwise false.
     *
     * @param contactedDate A contacted date to be checked for validity.
     * @return If valid {@code DATE_FORMAT} and not in the future, true; otherwise false.
     */
    private static boolean isValidDate(String contactedDate) {
        LocalDate contactedDateFormatted;

        try {
            contactedDateFormatted = LocalDate.parse(contactedDate, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            // Invalid date format.
            return false;
        }

        LocalDate today = LocalDate.now();

        // contactedDateFormatted will be instantiated in the try block
        return contactedDateFormatted.compareTo(today) <= 0;
    }

    /**
     * Returns true if {@code ContactedDate} is equal to {@code EMPTY_CONTACTED_DATE}, false otherwise.
     *
     * @return If {@code ContactedDate} is equal to {@code EMPTY_CONTACTED_DATE}, true; otherwise false.
     */
    public boolean isEmpty() {
        return this.equals(EMPTY_CONTACTED_DATE);
    }

    /**
     * Returns string representation of {@code ContactedDate}.
     *
     * @return String representation of {@code ContactedDate}.
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return MESSAGE_NOT_CONTACTED;
        }

        return String.format(MESSAGE_CONTACTED_PREFIX, this.contactedDate);
    }

    /**
     * Checks if two {@code ContactedDate} object is equal.
     *
     * @param other The other {@code ContactedDate} object.
     * @return If equal true; otherwise false.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContactedDate // instanceof handles nulls
                && contactedDate.equals(((ContactedDate) other).contactedDate)); // case-insensitive
    }

    /**
     * Returns true if both contacted dates are identical (case/formatting-sensitive).
     *
     * @param otherContactedDate The other contacted date.
     * @return true if both contacted dates are identical.
     */
    public boolean exactEquals(ContactedDate otherContactedDate) {
        return otherContactedDate != null
                && contactedDate.equals(otherContactedDate.contactedDate);
    }

    /**
     * Returns hashcode of {@code ContactedDate}.
     *
     * @return Hashcode of {@code ContactedDate}.
     */
    @Override
    public int hashCode() {
        return contactedDate.hashCode();
    }
}
