package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_REMARK = new Prefix("r/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_DETAILS = new Prefix("d/");
    public static final Prefix PREFIX_DELIVERYDATETIME = new Prefix("c/");
    public static final Prefix PREFIX_COLLECTION_TYPE = new Prefix("m/");
    public static final Prefix PREFIX_BLANK = new Prefix("");

}
