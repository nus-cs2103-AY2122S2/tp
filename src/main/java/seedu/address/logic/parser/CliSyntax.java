package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions for Person */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_INSURANCE_PACKAGE = new Prefix("i/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");


    /* Prefix definitions for Insurance Package */

    // reuse
    // public static final Prefix PREFIX_INSURANCE_PACKAGE = new Prefix("i/");
    public static final Prefix PREFIX_PACKAGE_DESC = new Prefix("d/");

}
