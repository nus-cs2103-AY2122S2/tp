package seedu.address.logic.commands;

/**
 * Finds and lists all persons in ReadyBakey whose attributes contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public abstract class FindPersonCommand extends Command {

    public static final String COMMAND_WORD = "findp";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons based on attributes "
            + "(name/phone/email/address/remark) whose attributes contain any of the specified keywords "
            + "(case-insensitive) and displays them as a list with index numbers.\n"
            + "The command does not allow multiple attributes to be filtered at the same time.\n"
            + "Parameters: (n|p|e|a|r)/ KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " n/ alice bob charlie";

}
