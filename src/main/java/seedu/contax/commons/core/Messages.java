package seedu.contax.commons.core;

/**
 * Contains user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_INVALID_TAG_DISPLAYED_INDEX = "The tag index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_TAGS_LISTED_OVERVIEW = "%d tags listed!";

    // ====== Appointments Related Messages =======
    public static final String MESSAGE_APPOINTMENTS_LISTED_OVERVIEW = "%1$d appointments listed!";
    public static final String MESSAGE_APPOINTMENTS_OVERLAPPING = "The new appointment will **overlap** with"
            + " another appointment in the schedule!";
    public static final String MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX =
            "The appointment index provided is invalid";

    public static final String MESSAGE_INVALID_DATE = "The date provided is invalid!";
    public static final String MESSAGE_INVALID_TIME = "The time provided is invalid!";

    public static final String MESSAGE_INVALID_PRIORITY_LEVEL = "Invalid priority level for editpriority \n%1$s";

    // ====== Bulk Operation Related Messages =======
    public static final String MESSAGE_INVALID_RANGE_INDEX = "The range index is invalid!";
}
