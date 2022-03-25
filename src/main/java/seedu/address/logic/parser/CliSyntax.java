package seedu.address.logic.parser;


/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_GITHUB = new Prefix("g/");
    public static final Prefix PREFIX_TELEGRAM = new Prefix("tl/"); // need to change this in the future
    public static final Prefix PREFIX_STUDENTID = new Prefix("i/");
    public static final Prefix PREFIX_LAB = new Prefix("l/");
    public static final Prefix PREFIX_LABSTATUS = new Prefix("s/");
    public static final Prefix PREFIX_LABMARK = new Prefix("m/");
}
