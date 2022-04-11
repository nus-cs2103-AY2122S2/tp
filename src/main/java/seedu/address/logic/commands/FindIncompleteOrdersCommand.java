package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.order.Order;

/**
 *  Finds and lists all orders in ReadyBakey who is incomplete as of the given date
 */
public class FindIncompleteOrdersCommand extends FindOrderCommand {

    public static final String COMMAND_WORD = "incompleteo";

    public static final String MESSAGE_SUCCESS = "Listed all incomplete orders";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all incomplete orders before the given date\n"
            + "Parameters: DELIVERYDATETIME\n"
            + "Example: " + COMMAND_WORD + " 25-12-2022 15:30";

    private final Predicate<Order> predicate;

    public FindIncompleteOrdersCommand(Predicate<Order> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updatedSortedFilteredOrderList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_ORDERS_LISTED_OVERVIEW, model.getFilteredOrderList().size()),
                true, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindIncompleteOrdersCommand // instanceof handles nulls
                && predicate.equals(((FindIncompleteOrdersCommand) other).predicate)); // state check
    }

}
