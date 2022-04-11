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
    public static final Prefix PREFIX_JOB = new Prefix("j/");
    public static final Prefix PREFIX_STAGE = new Prefix("s/");
    public static final Prefix PREFIX_DATE = new Prefix("d/");
    public static final Prefix PREFIX_TIME = new Prefix("t/");
    public static final Prefix PREFIX_GROUP = new Prefix("g/");
    public static final Prefix PREFIX_HEADER = new Prefix("h/");
    public static final Prefix PREFIX_INFORMATION = new Prefix("i/");

    /* Type definitions */
    public static final String TYPE_INTERVIEW = "[i]";
    public static final String TYPE_PERSON = "[p]";
    public static final String TYPE_TASK = "[t]";
}
