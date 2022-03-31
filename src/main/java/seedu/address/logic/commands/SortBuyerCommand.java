package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * A command class that deals with sorting command
 * Buyers will be sorted by names alphabetically or by appointment time,
 * in either ascending or descending order.
 */
public class SortBuyerCommand extends Command {
    public static final String COMMAND_WORD = "sort-b";
    public static final String MESSAGE_NOT_SORTABLE = "What you have entered cannot be sorted by AgentSee";
    public static final String MESSAGE_INCORRECT_ORDER = "Please enter a correct order, e.g. asc/desc";
    public static final String MESSAGE_MISSING_ARGUMENTS = "Please indicate what you want to sort.\n"
            + " e.g. by/name or by/time";
    public static final String MESSAGE_MISSING_ORDER = "Please indicate the sorting order, e.g. o/asc or o/desc";
    public static final String MESSAGE_USAGE = COMMAND_WORD + "Sorts the buyer in the address book according to\n"
            + "the predicate\n"
            + "Example: " + COMMAND_WORD + "by/"
            + "name";

    private final String comparedItem;
    private final String order;

    /**
     * Constructor for SortBuyerCommand
     * @param comparedItem item to be compared, time/name.
     * @param order whether it is in ascending or descending order.
     */
    public SortBuyerCommand(String comparedItem, String order) {
        this.comparedItem = comparedItem;
        this.order = order;
    }

    @Override
    public CommandResult execute(Model model) {
        model.sortFilteredBuyerList(comparedItem, order);
        return new CommandResult("Buyers sorted by " + comparedItem + " " + order);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SortBuyerCommand)) {
            return false;
        }

        // state check
        SortBuyerCommand e = (SortBuyerCommand) other;
        return comparedItem.equals(e.comparedItem)
                && order.equals(e.order);
    }
}
