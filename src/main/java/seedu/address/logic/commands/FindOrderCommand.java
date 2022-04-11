package seedu.address.logic.commands;

/**
 *  Finds and lists all orders in ReadyBakey whose predicate (attribute) contains any of the argument keywords.
 *  Search orders based on attributes is only current supported by {@code name} and {@code phone}.
 */
public abstract class FindOrderCommand extends Command {
    public static final String COMMAND_WORD = "findo";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all orders based on attributes (name/phone/details/collection_type/remark) "
            + "whose attributes contain any of the specified keywords (case-insensitive)"
            + " and displays them as a list with index numbers.\n"
            + "The command does not allow multiple attributes to be filtered at the same time.\n"
            + "Parameters: (n|p|d|m|r)/ KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " n/alice bob charlie";
}
