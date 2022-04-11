package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions for Client */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_PLAN = new Prefix("l/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");

    /* Prefix definitions for Procedure */
    public static final Prefix PREFIX_INFORMATION = new Prefix("i/");
    public static final Prefix PREFIX_COST = new Prefix("c/");
    public static final Prefix PREFIX_DATE = new Prefix("d/");

}
