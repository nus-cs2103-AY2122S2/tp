package seedu.contax.commons.util.datetimeparser;

import static java.util.Objects.requireNonNull;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.util.Optional;
import java.util.regex.Matcher;

/**
 * Provides parsing services for time inputs used in the application.
 */
public class TimeParser {

    /**
     * Parses a time input string to a {@code LocalTime} object.
     *
     * @param input The input time string to parse.
     * @return An Optional container that contains a LocalTime object if parsing was successful, or an empty
     *         Optional container if parsing was unsuccessful.
     */
    public static Optional<LocalTime> parseTime(String input) {
        requireNonNull(input);

        return match12HourFormat(input).flatMap(TimeParser::parse12HourFormat)
                .or(() -> match24HourFormat(input).flatMap(TimeParser::parse24HourFormat));
    }

    /**
     * Returns a {@link Matcher} object if the input is in the 24-hour time format, contained in an
     * {@code Optional}, and an empty {@code Optional} otherwise.
     * See {@link TimeParserPatternProvider#TIME_24H_PATTERN} for more details on the expected format.
     *
     * @param input The input time string to match.
     * @return A {@code Matcher} object in an Optional container if the supplied input matches, or an empty
     *         optional otherwise.
     */
    private static Optional<Matcher> match24HourFormat(String input) {
        Matcher match = TimeParserPatternProvider.TIME_24H_PATTERN.matcher(input);
        if (!match.matches()) {
            return Optional.empty();
        }
        return Optional.of(match);
    }

    /**
     * Parses a 24-hour time format match into a {@link LocalTime} object.
     *
     * @param match The match object generated from matching an input string against
     *              {@link TimeParserPatternProvider#TIME_24H_PATTERN}.
     * @return A {@code LocalTime} object contained in an {@code Optional} if the input is valid, or an
     *         empty {@code Optional} otherwise.
     */
    private static Optional<LocalTime> parse24HourFormat(Matcher match) {
        try {
            int hour = Integer.parseInt(match.group(1));
            int minute = Integer.parseInt(match.group(2));

            return Optional.of(LocalTime.of(hour, minute, 0));
        } catch (NumberFormatException | DateTimeException ex) {
            return Optional.empty();
        }
    }

    /**
     * Returns a {@link Matcher} object if the input is in the 12-hour time format, contained in an
     * {@code Optional}, and an empty {@code Optional} otherwise.
     * See {@link TimeParserPatternProvider#TIME_12H_PATTERN} for more details on the expected format.
     *
     * @param input The input time string to match.
     * @return A {@code Matcher} object in an Optional container if the supplied input matches, or an empty
     *         optional otherwise.
     */
    private static Optional<Matcher> match12HourFormat(String input) {
        Matcher match = TimeParserPatternProvider.TIME_12H_PATTERN.matcher(input);
        if (!match.matches()) {
            return Optional.empty();
        }
        return Optional.of(match);
    }

    /**
     * Parses a 12-hour time format match into a {@link LocalTime} object.
     *
     * @param match The match object generated from matching an input string against
     *              {@link TimeParserPatternProvider#TIME_12H_PATTERN}.
     * @return A {@code LocalTime} object contained in an {@code Optional} if the input is valid, or an
     *         empty {@code Optional} otherwise.
     */
    private static Optional<LocalTime> parse12HourFormat(Matcher match) {
        try {
            int hour = Integer.parseInt(match.group(1));
            int minute = Integer.parseInt(match.group(2));
            boolean isPm = TimeParserPatternProvider.TIME_MODIFIER_PM.equalsIgnoreCase(match.group(3));

            // 12am and 12pm require special handling due to 12am being 0 instead of 12
            if (hour == 12) {
                hour = isPm ? 12 : 0;
            } else if (isPm) {
                hour += 12;
            }

            return Optional.of(LocalTime.of(hour, minute, 0));
        } catch (NumberFormatException | DateTimeException ex) {
            return Optional.empty();
        }
    }
}
