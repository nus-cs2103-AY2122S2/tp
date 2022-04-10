package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Represents an Appointment's associated date and time in the address book.
 * Guarantees: immutable; is valid as declared in {@link #validateDateTime(String)}
 */
public class DateTime {
    public static final String MESSAGE_CONSTRAINTS =
            "Date and time should not be a past time"
                    + " and should be in this format: dd-MM-yyyy HH:mm, e.g. 02-03-2022 18:00";

    private static final String DATE_TIME_FORMAT = "dd-MM-uuuu HH:mm";

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern(DATE_TIME_FORMAT).withResolverStyle(ResolverStyle.STRICT);

    public final LocalDateTime dateTime;

    /**
     * Constructs a {@code DateTime}.
     *
     * @param input A valid date time string.
     */
    public DateTime(String input) {
        requireNonNull(input);
        LocalDateTime result = validateDateTime(input);
        if (result == null) {
            checkArgument(false, MESSAGE_CONSTRAINTS);
        }
        this.dateTime = result;
    }

    /**
     * Alternative constructor using LocalDateTime object as input.
     *
     * @param dateTime A valid LocalDateTime object.
     */
    public DateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    public LocalDateTime getLocalDateTime() {
        return this.dateTime;
    }

    /**
     * Returns a valid LocalDateTime object if a given string is valid date and time.
     */
    public static LocalDateTime validateDateTime(String test) {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(test, DATE_TIME_FORMATTER);
            return dateTime;
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return dateTime.format(DATE_TIME_FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateTime // instanceof handles nulls
                && dateTime.equals(((DateTime) other).dateTime)); // state check
    }

    @Override
    public int hashCode() {
        return dateTime.hashCode();
    }

    public boolean isBefore(LocalDateTime now) {
        return this.dateTime.isBefore(now);
    }
}
