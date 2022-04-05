package seedu.contax.commons.util.datetimeparser;

import static java.util.Objects.requireNonNull;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;

/**
 * Provides parsing services for date inputs used in the application.
 */
public class DateParser {

    /**
     * Parses a date input string to a {@code LocalDate} object.
     *
     * @param input The input date string to parse.
     * @return An Optional container that contains a LocalDate object if parsing was successful, or an empty
     *         Optional container if parsing was unsuccessful.
     */
    public static Optional<LocalDate> parseDate(String input) {
        requireNonNull(input);

        return matchStandardFormat(input).flatMap(DateParser::parseStandardFormat)
            .or(() -> matchNaturalFormat(input).flatMap(DateParser::parseNaturalFormat));
    }

    /**
     * Returns a {@link Matcher} object if the input is in the standard date format, contained in an
     * {@code Optional}, and an empty {@code Optional} otherwise.
     *
     * @param input The input date string to match.
     * @return A {@code Matcher} object in an Optional container if the supplied input matches, or an empty
     *         optional otherwise.
     */
    private static Optional<Matcher> matchStandardFormat(String input) {
        Matcher match = DateParserPatternProvider.STANDARD_DATE_PATTERN.matcher(input);
        if (!match.matches()) {
            return Optional.empty();
        }
        return Optional.of(match);
    }

    /**
     * Parses a standard date format match into a {@link LocalDate} object.
     *
     * @param match The match object generated from matching an input string against
     *              {@link DateParserPatternProvider#STANDARD_DATE_PATTERN}.
     * @return A {@code LocalDate} object contained in an {@code Optional} if the input is valid, or an
     *         empty {@code Optional} otherwise.
     */
    private static Optional<LocalDate> parseStandardFormat(Matcher match) {
        try {
            int day = Integer.parseInt(match.group(1));
            int month = Integer.parseInt(match.group(2));
            int year = Integer.parseInt(match.group(3));

            return Optional.of(makeDateObject(day, month, year));
        } catch (NumberFormatException | DateTimeException ex) {
            return Optional.empty();
        }
    }

    /**
     * Returns a {@link Matcher} object if the input matches a natural date format, contained in an
     * {@code Optional}, and an empty {@code Optional} otherwise.
     * See {@link DateParserPatternProvider#NATURAL_DATE_PATTERN} for more details on the valid natural
     * date patterns.
     *
     * @param input The input date string to match.
     * @return A {@code Matcher} object in an Optional container if the supplied input matches, or an empty
     *         optional otherwise.
     */
    private static Optional<Matcher> matchNaturalFormat(String input) {
        final String paddedLowerCaseInput = " " + input.toLowerCase() + " ";

        // Check if there are duplicates
        Matcher dayMatch = DateParserPatternProvider.NATURAL_DAY_OF_MONTH_PATTERN.matcher(paddedLowerCaseInput);
        Matcher monthMatch = DateParserPatternProvider.NATURAL_MONTH_PATTERN.matcher(paddedLowerCaseInput);
        Matcher yearMatch = DateParserPatternProvider.NATURAL_YEAR_PATTERN.matcher(paddedLowerCaseInput);

        // There is < 1 occurrence of the component.
        if (!dayMatch.find() || !monthMatch.find() || !yearMatch.find()) {
            return Optional.empty();
        }
        // There is > 1 occurrence of the component. It takes end - 1 because they may share the space
        if (dayMatch.find(dayMatch.end() - 1) || monthMatch.find(monthMatch.end() - 1)
                || yearMatch.find(yearMatch.end() - 1)) {
            return Optional.empty();
        }

        Matcher match = DateParserPatternProvider.NATURAL_DATE_PATTERN.matcher(paddedLowerCaseInput);
        if (!match.matches()) {
            return Optional.empty();
        }
        return Optional.of(match);
    }

    /**
     * Parses a natural date format match into a {@link LocalDate} object.
     *
     * @param match The match object generated from matching an input string against
     *              {@link DateParserPatternProvider#NATURAL_DATE_PATTERN}.
     * @return A {@code LocalDate} object contained in an {@code Optional} if the input is valid, or an
     *         empty {@code Optional} otherwise.
     */
    private static Optional<LocalDate> parseNaturalFormat(Matcher match) {
        try {
            int day = Integer.parseInt(match.group(1));
            int month = DateParserPatternProvider.convertMonthStringToDecimal(match.group(2));
            int year = Integer.parseInt(match.group(3));

            if (month < 1) {
                return Optional.empty();
            }

            return Optional.of(makeDateObject(day, month, year));
        } catch (NumberFormatException | DateTimeException ex) {
            return Optional.empty();
        }
    }

    /**
     * Creates a {@code LocalDate} object with the supplied day, month and year values.
     * Checks if the created object was automatically adjusted by java into another value during the
     * conversion process of {@code LocalDate}.
     *
     * @param day The day-of-month of the created LocalDate.
     * @param month The month of the created LocalDate.
     * @param year The year of the created LocalDate.
     * @return A LocalDate with the specified values.
     * @throws DateTimeException If any of the year, month or day values were invalid.
     */
    private static LocalDate makeDateObject(int day, int month, int year) throws DateTimeException {
        LocalDate parsedDate = LocalDate.of(year, month, day);

        // Detect if Java performed any auto-adjustments to the date object
        if (parsedDate.getDayOfMonth() != day || parsedDate.getMonthValue() != month
                || parsedDate.getYear() != year) {
            throw new DateTimeException("Invalid day, month or year");
        }
        return parsedDate;
    }
}
