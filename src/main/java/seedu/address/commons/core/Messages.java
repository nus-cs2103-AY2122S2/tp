package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_DATETIME = "Date time format should be yyyy-MM-dd HH:mm";
    public static final String MESSAGE_INVALID_DATE = "Date format should be yyyy-MM-dd";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The applicant index provided is invalid";
    public static final String MESSAGE_INVALID_INTERVIEW_DISPLAYED_INDEX = "The interview index provided is invalid";
    public static final String MESSAGE_INVALID_POSITION_DISPLAYED_INDEX = "The position index provided is invalid";
    public static final String MESSAGE_INVALID_FLAG = "Flag is invalid!";
    public static final String MESSAGE_NO_FLAG = "No flag is found!";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_INTERVIEW_CANNOT_BE_PASSED = "The interview cannot be passed, "
            + "as the number of current offers will exceed the number of available positions or because the status"
            + " has been modified and it is not pending";
    public static final String MESSAGE_INTERVIEW_CANNOT_BE_FAILED = "The interview cannot be failed, "
            + "since it's status has already been modified and it is not pending";
    public static final String MESSAGE_INTERVIEW_CANNOT_BE_ACCEPTED = "The interview cannot be accepted, "
            + "as it does not have a Passed status!";
    public static final String MESSAGE_INTERVIEW_CANNOT_BE_REJECTED = "The interview cannot be rejected, "
            + "as it does not have a Passed status!";
}
