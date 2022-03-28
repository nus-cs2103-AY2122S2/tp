package seedu.contax.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");

    /* Prefix definition for import CSV */
    public static final Prefix PREFIX_FILE = new Prefix("f/");

    /* Prefix definition for advanced commands */
    public static final Prefix PREFIX_SEARCH_TYPE = new Prefix("by/");
    public static final Prefix PREFIX_RANGE_FROM = new Prefix("from/");
    public static final Prefix PREFIX_RANGE_TO = new Prefix("to/");
    public static final Prefix PREFIX_EQUALS = new Prefix("=/");
    public static final Prefix PREFIX_START_WITH = new Prefix("start/");
    public static final Prefix PREFIX_END_WITH = new Prefix("end/");


    /* Prefix definitions for Appointments */
    public static final Prefix PREFIX_DATE = new Prefix("d/");
    public static final Prefix PREFIX_TIME = new Prefix("t/");
    public static final Prefix PREFIX_DURATION = new Prefix("l/");
    public static final Prefix PREFIX_PERSON = new Prefix("p/");
    public static final Prefix PREFIX_DATE_START = new Prefix("sd/");
    public static final Prefix PREFIX_TIME_START = new Prefix("st/");
    public static final Prefix PREFIX_DATE_END = new Prefix("ed/");
    public static final Prefix PREFIX_TIME_END = new Prefix("et/");

    public static final Prefix PREFIX_PRIORITY = new Prefix("pri/");
}
