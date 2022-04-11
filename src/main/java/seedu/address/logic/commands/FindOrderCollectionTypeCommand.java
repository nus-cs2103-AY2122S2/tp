package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COLLECTION_TYPE;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.order.CollectionTypeContainsKeywordsPredicate;

/**
 *  Finds and lists all orders in ReadyBakey whose predicate (collectionType) contains any of the argument keywords.
 *  Keyword matching is case insensitive.
 */
public class FindOrderCollectionTypeCommand extends FindOrderCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + PREFIX_COLLECTION_TYPE
            + "[Keyword]: Finds all orders based on CollectionType attribute \n"
            + "[Keyword] can only be delivery or pickup (case-insensitive)"
            + "Example: " + COMMAND_WORD + PREFIX_COLLECTION_TYPE + "delivery";

    private final String attributeName = "collectionType";
    private final CollectionTypeContainsKeywordsPredicate predicate;

    public FindOrderCollectionTypeCommand(CollectionTypeContainsKeywordsPredicate predicate) {
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
                || (other instanceof FindOrderCollectionTypeCommand // instanceof handles nulls
                && predicate.equals(((FindOrderCollectionTypeCommand) other).predicate)); // state check
    }
}
