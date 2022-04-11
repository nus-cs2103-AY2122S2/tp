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
    public static final Prefix PREFIX_JOBTITLE = new Prefix("j/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_APPLICATION_STATUS_TAG = new Prefix("ast/");
    public static final Prefix PREFIX_PRIORITY_TAG = new Prefix("pt/");
    public static final Prefix PREFIX_INTERVIEW_SLOT = new Prefix("idt/");
    public static final Prefix PREFIX_DETAILS = new Prefix("d/");
}
