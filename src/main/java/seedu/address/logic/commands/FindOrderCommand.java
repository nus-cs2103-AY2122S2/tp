package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_NO_ORDERS_FOUND;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.order.OrderContainsKeywordsPredicate;

/**
 * Finds and lists all orders in address book who contains the phone number.
 */
public class FindOrderCommand extends Command {

    public static final String COMMAND_WORD = "findorder";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all orders who contain any of "
            + "the specified customer phone numbers and displays them as a list with index numbers.\n"
            + "Parameters: PHONENUMBER \n"
            + "Example: " + COMMAND_WORD + " 81234567 87654321";

    public final OrderContainsKeywordsPredicate predicate;

    public FindOrderCommand(OrderContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredOrderList(predicate);
        if (model.getFilteredOrderList().isEmpty()) {
            throw new CommandException(MESSAGE_NO_ORDERS_FOUND);
        }
        return new CommandResult(
                String.format(Messages.MESSAGE_ORDERS_LISTED_OVERVIEW, model.getFilteredOrderList().size()),
                false, false, false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof FindOrderCommand)
                && predicate.equals(((FindOrderCommand) other).predicate);
    }
}
