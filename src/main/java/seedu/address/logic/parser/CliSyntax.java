package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_TELEGRAM = new Prefix("t/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_ID = new Prefix("id/");
    public static final Prefix PREFIX_MODULE_CODE = new Prefix("c/");
    public static final Prefix PREFIX_ACADEMIC_YEAR = new Prefix("a/");
    public static final Prefix PREFIX_TYPE = new Prefix("t/");
    public static final Prefix PREFIX_MODULE_INDEX = new Prefix("m/");
    public static final Prefix PREFIX_SIMPLE_NAME = new Prefix("sn/");
    public static final String TYPE_STUDENT = "student";
    public static final String TYPE_MODULE = "module";
    public static final String TYPE_CLASS = "class";
    public static final String TYPE_ASSESSMENT = "assessment";
}
