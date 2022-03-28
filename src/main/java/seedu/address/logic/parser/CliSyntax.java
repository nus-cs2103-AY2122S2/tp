package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_STUDENT_NAME = new Prefix("-n", "studentName");
    public static final Prefix PREFIX_STUDENT_PHONE = new Prefix("-p", "studentPhone");
    public static final Prefix PREFIX_STUDENT_EMAIL = new Prefix("-e", "studentEmail");
    public static final Prefix PREFIX_STUDENT_ADDRESS = new Prefix("-a", "studentAddress");
    public static final Prefix PREFIX_STUDENT_TAG = new Prefix("-t", "studentTag");

    public static final Prefix PREFIX_LESSON_NAME = new Prefix("-n", "lessonName");
    public static final Prefix PREFIX_SUBJECT = new Prefix("-s", "subject");
    public static final Prefix PREFIX_LESSON_ADDRESS = new Prefix("-a", "address");
    public static final Prefix PREFIX_DATE = new Prefix("-d", "date");
    public static final Prefix PREFIX_START_TIME = new Prefix("-t", "time");
    public static final Prefix PREFIX_DURATION_HOURS = new Prefix("-h", "hours");
    public static final Prefix PREFIX_DURATION_MINUTES = new Prefix("-m", "minutes");
    public static final Prefix PREFIX_RECURRING = new Prefix("-r", "recurring");

    public static final Prefix PREFIX_STUDENT = new Prefix("-s", "studentID");
    public static final Prefix PREFIX_LESSON = new Prefix("-l", "lessonID");

    public static final Prefix PREFIX_CLEAR = new Prefix("-f", "clearConfirmation");
}
