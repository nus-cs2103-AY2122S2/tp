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

    /* Prefix definition for tag management */
    public static final Prefix PREFIX_NEWTAGNAME = new Prefix("t/");

    /* Prefix definition for import CSV */
    public static final Prefix PREFIX_FILE = new Prefix("f/");

    /* Prefix definition for advanced commands */
    public static final Prefix PREFIX_SEARCH_TYPE = new Prefix("by/");

    /* Prefix definitions for Appointments */
    public static final Prefix PREFIX_DATE = new Prefix("d/");
    public static final Prefix PREFIX_TIME = new Prefix("t/");
    public static final Prefix PREFIX_DURATION = new Prefix("l/");
    public static final Prefix PREFIX_PERSON = new Prefix("p/");
}
