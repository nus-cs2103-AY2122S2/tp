package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.order.DetailsContainsKeywordsPredicate;

/**
 *  Finds and lists all orders in ReadyBakey whose predicate (details) contains any of the argument keywords.
 *  Keyword matching is case insensitive.
 */
public class FindOrderDetailsCommand extends FindOrderCommand {
    private final String attributeName = "details";
    private final DetailsContainsKeywordsPredicate predicate;

    public FindOrderDetailsCommand(DetailsContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredOrderList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_FIND_ORDERS_OVERVIEW, model.getFilteredOrderList().size(),
                        attributeName, predicate.getKeywordsString()),
                true, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindOrderDetailsCommand // instanceof handles nulls
                && predicate.equals(((FindOrderDetailsCommand) other).predicate)); // state check
    }
}
