package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_URL = new Prefix("u/");
    public static final Prefix PREFIX_DATETIME = new Prefix("d/");
    public static final Prefix PREFIX_DURATION = new Prefix("dur/");
    public static final Prefix PREFIX_RECURRING = new Prefix("r/");
    public static final Prefix PREFIX_MODULE = new Prefix("m/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_FORCED_DELETE = new Prefix("f/");
}
