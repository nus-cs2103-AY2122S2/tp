package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_NO_INDEX_OR_PREFIX_PROVIDED = "You either did not provide a valid index or "
            + "a valid prefix required! "
            + "\nThe index has to be a non-negative integer lesser than or equal to the size of the list. "
            + "\n%1$s";
    public static final String MESSAGE_OOB_STUDENT_DISPLAYED_INDEX = "The student index provided is out-of-bounds";
    public static final String MESSAGE_OOB_LESSON_DISPLAYED_INDEX = "The lesson index provided is out-of-bounds.";
    public static final String MESSAGE_STUDENTS_LISTED_OVERVIEW = "%1$d students listed!";
    public static final String MESSAGE_LESSONS_LISTED_OVERVIEW = "%1$d lessons listed!";

}
