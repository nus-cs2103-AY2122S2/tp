package seedu.contax.commons.core;

/**
 * Contains Date and Time format Strings.
 */
public class DateTimeFormats {
    public static final String DATE_DISPLAY_FORMAT = "dd LLL uuuu";
    public static final String TIME_24H_DISPLAY_FORMAT = "HH:mm";
    public static final String TIME_12H_DISPLAY_FORMAT = "hh:mm a";
    public static final String DATETIME_DISPLAY_FORMAT = DATE_DISPLAY_FORMAT + " " + TIME_12H_DISPLAY_FORMAT;
}
