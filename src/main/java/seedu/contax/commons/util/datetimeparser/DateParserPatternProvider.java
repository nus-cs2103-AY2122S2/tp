package seedu.contax.commons.util.datetimeparser;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Provides regex patterns for parsing dates. This class is package-private and is only visible to
 * the {@code datetimeparser} package.
 */
class DateParserPatternProvider {

    // Intermediate parts for natural human-readable dates
    private static final String[] MONTH_STRINGS = new String[] {
        "january", "february", "march", "april", "may", "june", "july", "august", "september", "october",
        "november", "december", "jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov",
        "dec"
    };

    // Template regex group patterns
    private static final String COMBINED_MONTH_STRING = String.join("|", MONTH_STRINGS);
    private static final String DAY_OF_MONTH_GROUP = "(%s (\\d{1,2}) )";
    private static final String MONTH_GROUP = "(%s (" + COMBINED_MONTH_STRING + ") )";
    private static final String YEAR_GROUP = "(%s (\\d{4}) )";
    private static final String LOOKAHEAD_WILDCARD_PATTERN = "?=.*";
    private static final String ANCHOR_PATTERN = "^%s.*$";

    // Actual concrete lookahead regex group patterns
    private static final String DAY_OF_MONTH_PATTERN = String.format(DAY_OF_MONTH_GROUP,
            LOOKAHEAD_WILDCARD_PATTERN);
    private static final String MONTH_PATTERN = String.format(MONTH_GROUP, LOOKAHEAD_WILDCARD_PATTERN);
    private static final String YEAR_PATTERN = String.format(YEAR_GROUP, LOOKAHEAD_WILDCARD_PATTERN);

    /** Matches dd-MM-yyyy and dd/MM/yyyy formats. */
    private static final String STANDARD_DATE_PATTERN_STRING = "(\\d{2})[/-](\\d{2})[/-](\\d{4})";

    /**
     * Matches human-readable date formats like 10 Jan 2022 and 2011 Feb 20.
     * If the same component appears twice (e.g. 10 Jan Feb 2022), only the last appearance will be taken.
     */
    private static final String NATURAL_DATE_PATTERN_STRING =
            String.format(ANCHOR_PATTERN, DAY_OF_MONTH_PATTERN + MONTH_PATTERN + YEAR_PATTERN);

    /** Matches the day component of {@link #NATURAL_DATE_PATTERN_STRING}. */
    private static final String NATURAL_DAY_OF_MONTH_PATTERN_STRING = String.format(DAY_OF_MONTH_GROUP, "");

    /** Matches the month component of {@link #NATURAL_DATE_PATTERN_STRING}. */
    private static final String NATURAL_MONTH_PATTERN_STRING = String.format(MONTH_GROUP, "");

    /** Matches the year component of {@link #NATURAL_DATE_PATTERN_STRING}. */
    private static final String NATURAL_YEAR_PATTERN_STRING = String.format(YEAR_GROUP, "");

    /** Compiled pattern for dd-MM-yyyy and dd/MM/yyyy formats. */
    static final Pattern STANDARD_DATE_PATTERN = Pattern.compile(STANDARD_DATE_PATTERN_STRING);

    /**
     * Compiled pattern for human-readable date formats.
     * See {@link #NATURAL_DATE_PATTERN_STRING} for pattern details.
     **/
    static final Pattern NATURAL_DATE_PATTERN = Pattern.compile(NATURAL_DATE_PATTERN_STRING,
            Pattern.CASE_INSENSITIVE);

    /** Compiled pattern for {@link #NATURAL_DAY_OF_MONTH_PATTERN_STRING} */
    static final Pattern NATURAL_DAY_OF_MONTH_PATTERN = Pattern.compile(NATURAL_DAY_OF_MONTH_PATTERN_STRING);

    /** Compiled pattern for {@link #NATURAL_MONTH_PATTERN_STRING} */
    static final Pattern NATURAL_MONTH_PATTERN = Pattern.compile(NATURAL_MONTH_PATTERN_STRING,
            Pattern.CASE_INSENSITIVE);

    /** Compiled pattern for {@link #NATURAL_YEAR_PATTERN_STRING} */
    static final Pattern NATURAL_YEAR_PATTERN = Pattern.compile(NATURAL_YEAR_PATTERN_STRING);

    /**
     * Translates the supplied {@code monthString} to its integer representation.
     * Returns -1 if the supplied string is not in the {@code MONTH_STRINGS} array.
     *
     * @param monthString The month in String format to convert.
     * @return The integer representation of the month if recognised, or -1 otherwise.
     */
    static int convertMonthStringToDecimal(String monthString) {
        int index = List.of(MONTH_STRINGS).indexOf(monthString);
        if (index < 0) {
            return -1;
        }

        // We note that the array repeats in meaning every 12 items.
        // The +1 is to convert from a 0-based index to the 1-based index used by LocalDate.
        return (index % 12) + 1;
    }
}
