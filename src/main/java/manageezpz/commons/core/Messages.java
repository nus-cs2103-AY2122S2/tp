package manageezpz.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command!";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format!";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT_BIND = "Invalid command format! \n\n%1$s";

    public static final String MESSAGE_EMPTY_NAME = "Name field cannot be empty! \n\n%1$s";
    public static final String MESSAGE_EDIT_TASK_NO_EMPTY_VALUES =
            "Either desc/ date/ or at/ must have a value after it!";
    public static final String MESSAGE_EDIT_TODO_TASK_NO_DATE_AND_TIME_VALUES =
            "A todo task does not have a date or time to be edited!";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX =
            "The person index provided is invalid as it exceeds the amount of persons in the displayed list! \n\n%1$s";
    public static final String MESSAGE_INVALID_TASK_DISPLAYED_INDEX =
            "The task index provided is invalid as it exceeds the amount of tasks in the displayed list! \n\n%1$s";

    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_TASKS_LISTED_OVERVIEW = "%1$d tasks listed!";

    public static final String MESSAGE_INVALID_TASK_TYPE = "Task is an invalid Task Type!";
    public static final String MESSAGE_INVALID_TIME_FORMAT = "Invalid time format!";
    public static final String MESSAGE_INVALID_TIME_RANGE =
            "The time range you provided is invalid as end time should be after start time!";

    public static final String MESSAGE_DUPLICATE_TASK = "Task with the same description '%1$s' already exists! \n\n";

    public static final String MESSAGE_FIELD_NOT_EDITED = "At least one of the fields to edit must be provided.\n\n";

    // Not used, will delete before end of V1.4

    public static final String MESSAGE_EMPTY_TASK_NUMBER = "Task number field cannot be empty! \n\n%1$s";

    public static final String MESSAGE_TODO_SHOULD_NOT_HAVE_DATETIME = "Todo Task should not have date or time!";

    public static final String MESSAGE_EMPTY_START_TIME_END_TIME =
            "Please enter a start time and end time for the event!";
}
