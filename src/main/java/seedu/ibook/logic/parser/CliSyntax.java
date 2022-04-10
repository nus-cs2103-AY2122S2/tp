package seedu.ibook.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    public static final Prefix PREFIX_NAME = new Prefix("n:");
    public static final Prefix PREFIX_CATEGORY = new Prefix("c:");
    public static final Prefix PREFIX_PRICE = new Prefix("p:");
    public static final Prefix PREFIX_START_PRICE = new Prefix("sp:");
    public static final Prefix PREFIX_END_PRICE = new Prefix("ep:");
    public static final Prefix PREFIX_DISCOUNT_RATE = new Prefix("dr:");
    public static final Prefix PREFIX_DISCOUNT_START = new Prefix("ds:");
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("d:");
    public static final Prefix PREFIX_EXPIRY_DATE = new Prefix("e:");
    public static final Prefix PREFIX_QUANTITY = new Prefix("q:");
}
