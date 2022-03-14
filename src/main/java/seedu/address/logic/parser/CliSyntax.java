package seedu.address.logic.parser;
/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */

    // General
    public static final Prefix PREFIX_TYPE = new Prefix("t/");

    // Person Prefixes
    public static final Prefix PREFIX_NRIC = new Prefix("i/");
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("l/");

    // Consultation Prefixes
    public static final Prefix PREFIX_DATE = new Prefix("dt/");
    public static final Prefix PREFIX_TIME = new Prefix("tm/");
    public static final Prefix PREFIX_NOTES = new Prefix("n/");
    public static final Prefix PREFIX_PRESCRIPTION = new Prefix("pn/");
    public static final Prefix PREFIX_TESTS_TAKEN_AND_RESULTS = new Prefix("tt/");
}
