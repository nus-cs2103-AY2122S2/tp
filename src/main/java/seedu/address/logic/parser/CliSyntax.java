package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions. Add Prefixes here whenever you add a new command */
    public static final Prefix PREFIX_NAME = new Prefix("n/", "name");
    public static final Prefix PREFIX_PHONE = new Prefix("p/", "phone");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/", "email");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/", "address");
    public static final Prefix PREFIX_MODULE = new Prefix("m/", "module");
    public static final Prefix PREFIX_STATUS = new Prefix("s/", "status");
    public static final Prefix PREFIX_FORMAT = new Prefix("f/", "format");
    public static final Prefix PREFIX_COMMENT = new Prefix("c/", "comment");
}
