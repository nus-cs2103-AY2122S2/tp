package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/", "name");
    public static final Prefix PREFIX_PHONE = new Prefix("p/", "phone");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/", "email");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/", "address");
    public static final Prefix PREFIX_TAG = new Prefix("t/", "tag");

    public static final Prefix PREFIX_LESSON_NAME = new Prefix("-n", "name");
    public static final Prefix PREFIX_SUBJECT = new Prefix("-s", "subject");
    public static final Prefix PREFIX_LESSON_ADDRESS = new Prefix("-a", "address");
    public static final Prefix PREFIX_DATE = new Prefix("-d", "date");
    public static final Prefix PREFIX_START_TIME = new Prefix("-t", "time");
    public static final Prefix PREFIX_DURATION_HOURS = new Prefix("-h", "hours");
    public static final Prefix PREFIX_DURATION_MINUTES = new Prefix("-m", "minutes");
    public static final Prefix PREFIX_RECURRING = new Prefix("-r", "recurring");

    public static final Prefix PREFIX_STUDENT = new Prefix("-s", "student");
    public static final Prefix PREFIX_LESSON = new Prefix("-l", "lesson");
}
