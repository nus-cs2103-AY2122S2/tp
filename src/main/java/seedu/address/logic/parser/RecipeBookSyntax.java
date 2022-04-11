package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands.
 */
public class RecipeBookSyntax {

    /* Shorthand prefix */
    public static final Prefix PREFIX_NAME = new Prefix("-n ");
    public static final Prefix PREFIX_INGREDIENT = new Prefix("-i ");
    public static final Prefix PREFIX_COMPLETION_TIME = new Prefix("-d ");
    public static final Prefix PREFIX_SERVING_SIZE = new Prefix("-ss ");
    public static final Prefix PREFIX_STEP = new Prefix("-s ");
    public static final Prefix PREFIX_TAG = new Prefix("-t ");

    /** Used to indicate index in a command */
    public static final Prefix PREFIX_INDEX = new Prefix("-x ");

    public static final String SEPARATOR_SYMBOL = "\\|";
}
