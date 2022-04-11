package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {
    public static final String MESSAGE_DUPLICATE_PERSON = "A person with this name already exists. "
            + "Please do add tags to other persons with the same name to differentiate between them!";
    public static final String MESSAGE_DUPLICATE_MEETING = "A meeting with this agenda already exists. ";
    public static final String MESSAGE_DUPLICATE_DETAILS = "A person with these details already exists. "
            + "Please do add tags that differentiate between them!";
    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_VIEW = "This command is invalid here!";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "There is no person listed with the index %1$d";
    public static final String MESSAGE_INVALID_MEETING_DISPLAYED_INDEX = "The meeting index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

}
