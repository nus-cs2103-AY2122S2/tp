package seedu.contax.commons.util.datetimeparser;

import static java.util.Objects.requireNonNull;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

/**
 * Provides parsing services for time inputs used in the application.
 */
public class TimeParser {
    public static final String TIME_PATTERN = "HH:mm";

    /**
     * Parses a time input string to a {@code LocalTime} object.
     *
     * @param input The input time string to parse.
     * @return An Optional container that contains a LocalTime object if parsing was successful, or an empty
     *         Optional container if parsing was unsuccessful.
     */
    public static Optional<LocalTime> parseTime(String input) {
        requireNonNull(input);

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(TIME_PATTERN);
        try {
            return Optional.of(LocalTime.parse(input, timeFormatter));
        } catch (DateTimeParseException ex) {
            return Optional.empty();
        }
    }
}
