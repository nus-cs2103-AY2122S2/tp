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
    private static final String COMBINED_MONTH_STRING = String.join("|", MONTH_STRINGS);
    private static final String DAY_OF_MONTH_PATTERN = "(?=.* (\\d{1,2}) )";
    private static final String MONTH_PATTERN = "(?=.* (" + COMBINED_MONTH_STRING + ") )";
    private static final String YEAR_PATTERN = "(?=.* (\\d{4}) )";

    /** Matches dd-MM-yyyy and dd/MM/yyyy formats. **/
    private static final String STANDARD_DATE_PATTERN_STRING = "(\\d{2})[/-](\\d{2})[/-](\\d{4})";

    /**
     * Matches human-readable date formats like 10 Jan 2022 and 2011 Feb 20.
     * If the same component appears twice (e.g. 10 Jan Feb 2022), only the last appearance will be taken.
     */
    private static final String NATURAL_DATE_PATTERN_STRING =
            String.format("^%s.*$", DAY_OF_MONTH_PATTERN + MONTH_PATTERN + YEAR_PATTERN);

    /** Compiled pattern for dd-MM-yyyy and dd/MM/yyyy formats. **/
    static final Pattern STANDARD_DATE_PATTERN = Pattern.compile(STANDARD_DATE_PATTERN_STRING);

    /**
     * Compiled pattern for human-readable date formats.
     * See {@link #NATURAL_DATE_PATTERN_STRING} for pattern details.
     **/
    static final Pattern NATURAL_DATE_PATTERN = Pattern.compile(NATURAL_DATE_PATTERN_STRING,
            Pattern.CASE_INSENSITIVE);

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
