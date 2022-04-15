package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("d/");
    public static final Prefix PREFIX_DEADLINE = new Prefix("dl/");
    public static final Prefix PREFIX_PRIORITY = new Prefix("p/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");


    /* Prefix definitions for non-attribute fields */
    public static final Prefix PREFIX_START = new Prefix("start/");
    public static final Prefix PREFIX_END = new Prefix("end/");
    public static final Prefix PREFIX_SORT_KEY = new Prefix("by/");
    public static final Prefix PREFIX_SORT_ORDER = new Prefix("in/");
    public static final Prefix PREFIX_COMPLETION_STATUS = new Prefix("c/");
}
