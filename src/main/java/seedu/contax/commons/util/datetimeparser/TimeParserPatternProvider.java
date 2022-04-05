package seedu.contax.commons.util.datetimeparser;

import java.util.regex.Pattern;

/**
 * Provides regex patterns used for parsing times. This class is package-private and is only visible to
 * the {@code datetimeparser} package.
 */
class TimeParserPatternProvider {
    static final String TIME_MODIFIER_PM = "pm";

    /** Matches the HH:mm or HH-mm 24-hour time format. **/
    private static final String TIME_24H_PATTERN_STRING = "^([0-1]?[0-9]|2[0-3])[:-]([0-5]?[0-9])$";

    /**
     * Matches the HH:mm pm/am or HH-mm pm/am 12-hour time format.
     * Note that 0 is not a valid value for the hour field, use 12 instead.
     **/
    private static final String TIME_12H_PATTERN_STRING = "^([0]?[1-9]|1[0-2])[:-]([0-5]?[0-9])[ ]?(pm|am)$";

    /** Compiled pattern for HH:mm or HH-mm 24-hour time format. **/
    static final Pattern TIME_24H_PATTERN = Pattern.compile(TIME_24H_PATTERN_STRING);

    /**
     * Compiled pattern for HH:mm pm/am or HH-mm pm/am 12-hour time format.
     * Note that 0 is not a valid value for the hour field, use 12 instead.
     **/
    static final Pattern TIME_12H_PATTERN = Pattern.compile(TIME_12H_PATTERN_STRING,
            Pattern.CASE_INSENSITIVE);
}
