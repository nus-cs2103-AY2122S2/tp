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
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("d/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");

    public static final Prefix PREFIX_CURRENT_NAME = new Prefix("cn/");
    public static final Prefix PREFIX_NEW_NAME = new Prefix("nn/");
    public static final Prefix PREFIX_NEW_PHONE = new Prefix("np/");
    public static final Prefix PREFIX_NEW_EMAIL = new Prefix("ne/");
    public static final Prefix PREFIX_NEW_ADDRESS = new Prefix("na/");
    public static final Prefix PREFIX_NEW_DESCRIPTION = new Prefix("nd/");
    public static final Prefix PREFIX_NEW_TAG = new Prefix("nt/");

    public static final Prefix PREFIX_TITLE = new Prefix("ttl/");
    public static final Prefix PREFIX_LOG_INDEX = new Prefix("id/");
    public static final Prefix FLAG_ALL = new Prefix("-a");

    public static final Prefix PREFIX_DATETIME = new Prefix("dt/");
    public static final Prefix PREFIX_DATE = new Prefix("da/");
    public static final Prefix PREFIX_FRIEND_NAME = new Prefix("f/");
    public static final Prefix PREFIX_ADD_FRIENDNAME = new Prefix("af/");
    public static final Prefix PREFIX_REMOVE_FRIENDNAME = new Prefix("rf/");
}
