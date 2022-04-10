package seedu.address.model.record;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Represents a Record's associated date and time in the record book.
 * Guarantees: immutable; is valid as declared in {@link #validateDateTime(String)}
 */
public class StartDate {

    public static final String MESSAGE_CONSTRAINTS =
            "Start Date should be in this format: dd-MM-yyyy e.g. 02-03-2022";

    private static final String DATE_FORMAT = "dd-MM-uuuu";

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern(DATE_FORMAT).withResolverStyle(ResolverStyle.STRICT);

    public final LocalDate date;

    /**
     * Constructs a {@code DateTime}.
     *
     * @param input A valid date time string.
     */
    public StartDate(String input) {
        requireNonNull(input);
        LocalDate result = validateDateTime(input);
        if (result == null) {
            checkArgument(false, MESSAGE_CONSTRAINTS);
        }
        this.date = result;
    }

    /**
     * Alternative constructor using LocalDateTime object as input.
     *
     * @param date A valid LocalDateTime object.
     */
    public StartDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Returns a valid LocalDateTime object if a given string is valid date and time.
     */
    public static LocalDate validateDateTime(String test) {
        try {
            LocalDate date = LocalDate.parse(test, DATE_FORMATTER);
            return date;
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public LocalDate getStartDate() {
        return date;
    }

    @Override
    public String toString() {
        return date.format(DATE_FORMATTER);
    }


    @Override
    public int hashCode() {
        return date.hashCode();
    }
}
