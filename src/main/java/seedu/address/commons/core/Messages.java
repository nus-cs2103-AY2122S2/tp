package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_INTERVIEW_DISPLAYED_INDEX = "The interview index provided is invalid";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The applicant index provided is invalid";
    public static final String MESSAGE_INVALID_TASK_DISPLAYED_INDEX = "The task index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d applicants listed!";
    public static final String MESSAGE_INTERVIEWS_LISTED_OVERVIEW = "%1$d interviews listed!";
    public static final String MESSAGE_TASKS_LISTED_OVERVIEW = "%1$d tasks listed!";
    public static final String MESSAGE_NO_TYPE_GIVEN = "No type given. Type should be either [p] or [i] or [t].";
    public static final String MESSAGE_INVALID_TYPE_GIVEN = "Invalid type given. Type should be either [p] or [i]"
            + " or [t].";
    public static final String MESSAGE_INTERVIEW_LIST_NOT_EMPTY = "Interview list is not empty. "
            + "Please clear interview list first.";
    public static final String MESSAGE_PERSON_HAS_INTERVIEW = "Applicant has interview. "
            + "Delete interview first before editing or deleting applicant.";
}
