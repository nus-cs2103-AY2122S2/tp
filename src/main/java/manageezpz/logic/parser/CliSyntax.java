package manageezpz.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("desc/");
    public static final Prefix PREFIX_TODO = new Prefix("todo/");
    public static final Prefix PREFIX_DEADLINE = new Prefix("deadline/");
    public static final Prefix PREFIX_EVENT = new Prefix("event/");
    public static final Prefix PREFIX_TODAY = new Prefix("today/");
    public static final Prefix PREFIX_DATETIME = new Prefix("by/");
    public static final Prefix PREFIX_TIME = new Prefix("at/");
    public static final Prefix PREFIX_TASK = new Prefix("task/");
    public static final Prefix PREFIX_DATE = new Prefix("date/");
    public static final Prefix PREFIX_PRIORITY = new Prefix("priority/");
    public static final Prefix PREFIX_ASSIGNEES = new Prefix("assignees/");
    public static final Prefix PREFIX_IS_MARKED = new Prefix("isMarked/");
}
