package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";

    public static final String MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX = "The client index provided is invalid";
    public static final String MESSAGE_INVALID_BUYER_DISPLAYED_INDEX = "Ensure index is positive integer & less than "
            + "or equal to size of displayed buyer list ";
    public static final String MESSAGE_INVALID_SELLER_DISPLAYED_INDEX = "Ensure index is positive integer & less than "
            + "or equal to size of displayed seller list ";

    public static final String MESSAGE_CLIENTS_LISTED_OVERVIEW = "%1$d clients found!";
    public static final String MESSAGE_BUYERS_LISTED_OVERVIEW = "%1$d buyers found!";
    public static final String MESSAGE_SELLERS_LISTED_OVERVIEW = "%1$d sellers found!";

    public static final String MESSAGE_NO_PROPERTY_ADDED = "You can only match a buyer only after a property has "
            + "been added!\nUse: add-ptb to add a property first.";

}
