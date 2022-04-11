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
    public static final Prefix PREFIX_TAG = new Prefix("t/");

    /* Added prefix definitions */
    public static final Prefix PREFIX_TASKNAME = new Prefix("tn/");
    public static final Prefix PREFIX_DATETIME = new Prefix("dt/");
    public static final Prefix PREFIX_PERSON = new Prefix("p/");
    public static final Prefix PREFIX_GIT_USERNAME = new Prefix("u/");
    public static final Prefix PREFIX_LINK = new Prefix("z/");
    public static final Prefix PREFIX_RECURRING = new Prefix("r/");
    public static final Prefix PREFIX_LIST_ALL_TASK = new Prefix("all/");
    public static final Prefix PREFIX_LIST_INCOMPLETE_TASK = new Prefix("nc/");
    public static final Prefix PREFIX_LIST_COMPLETE_TASK = new Prefix("c/");
    public static final Prefix PREFIX_FILEPATH = new Prefix("fp/");
}
