package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an Event's date-time.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateTime(String)}
 */
public class DateTime implements Comparable<DateTime> {
    public static final String MESSAGE_CONSTRAINTS = "The date & time needs to be in the following format: "
            + "DD-MM-YYYY hhmm\n"
            + "Tip: 0s can be omitted where ambiguity will not be created.\n"
            + "e.g. 5-5-2021 instead of 05-05-2021 is a valid input.";
    private static final DateTimeFormatter INPUT_FORMATTER =
            DateTimeFormatter.ofPattern("d-M-yyyy Hmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER =
            DateTimeFormatter.ofPattern("MMM d yyyy hh:mma");

    private final LocalDateTime value; // please retrieve string via toInputFormat() instead.

    /**
     * Constructs an {@code DateTime}.
     *
     * @param dateTimeString A valid date-time String, in the format DD-MM-YYYY hhmm.
     */
    public DateTime(String dateTimeString) {
        requireNonNull(dateTimeString);
        checkArgument(isValidDateTime(dateTimeString), MESSAGE_CONSTRAINTS);
        value = LocalDateTime.parse(dateTimeString, INPUT_FORMATTER);
    }

    /**
     * Returns true if a given string is in a valid date time format.
     *
     * @param dateTimeString String to check validity for.
     * @return True if the given string is in a valid date time format.
     */
    public static Boolean isValidDateTime(String dateTimeString) {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public boolean isAfterNow() {
        return this.value.isAfter(LocalDateTime.now());
    }

    public boolean hasDateBefore(LocalDate date) {
        return value.toLocalDate().isBefore(date);
    }

    public boolean hasDateAfter(LocalDate date) {
        return value.toLocalDate().isAfter(date);
    }

    public boolean isBeforeNow() {
        return this.value.isBefore(LocalDateTime.now());
    }

    public boolean hasSameDate(DateTime dateTime) {
        return value.toLocalDate().equals(dateTime.value.toLocalDate());
    }

    /**
     * Returns the dateTime in the correct input format for the DateTime constructor.
     *
     * @return dateTime in the correct input format for the DateTime constructor.
     */
    public String toInputFormat() {
        return value.format(INPUT_FORMATTER);
    }

    @Override
    public String toString() {
        return value.format(OUTPUT_FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof DateTime)) {
            return false;
        }
        DateTime otherDateTime = (DateTime) other;
        return this.value.equals(otherDateTime.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(DateTime dateTime) {
        return this.value.compareTo(dateTime.value);
    }
}
