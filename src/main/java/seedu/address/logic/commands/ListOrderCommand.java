package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.order.OrderStatusContainsKeywordsPredicate;

/**
 * Finds and lists all orders whose status contains any of the argument keywords.
 */
public class ListOrderCommand extends Command {
    public static final String COMMAND_WORD = "listorder";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all orders based on parameter listed "
            + "and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD\n"
            + "where KEYWORD is one of 'all', 'in_progress' or 'in progress', 'delivered', 'cancelled' "
            + "(not case-sensitive)\n"
            + "Example: " + COMMAND_WORD + " all";

    private final OrderStatusContainsKeywordsPredicate predicate;

    public ListOrderCommand(OrderStatusContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (predicate.getKeywords().get(0).equalsIgnoreCase("all")) {
            model.updateFilteredOrderList(Model.PREDICATE_SHOW_ALL_ORDERS);
        } else {
            model.updateFilteredOrderList(predicate);
        }
        return new CommandResult(
                String.format(Messages.MESSAGE_ORDERS_LISTED_OVERVIEW, model.getFilteredOrderList().size()), false,
                false, false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListOrderCommand // instanceof handles nulls
                && predicate.equals(((ListOrderCommand) other).predicate)); // state check
    }
}

