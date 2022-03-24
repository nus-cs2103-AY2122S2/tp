package seedu.ibook.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_PRICE;

import seedu.ibook.commons.core.Messages;
import seedu.ibook.model.Model;
import seedu.ibook.model.product.filters.ProductFulfillsFiltersPredicate;

/**
 * Finds and lists all persons in Ibook whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists all products that matches with the key value pair "
            + "and displays them as a list with index numbers.\n"
            + "Parameters: PREFIX: VALUE [MORE_PREFIX: VALUE]...\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + ": bottle " + PREFIX_PRICE + ": 3.00";

    private final ProductFulfillsFiltersPredicate predicate;

    /**
     * Creates a Find Command with the filters of the {@code predicate}
     * @param predicate
     */
    public FindCommand(ProductFulfillsFiltersPredicate predicate) {
        assert predicate != null;
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {

        requireNonNull(model);
        model.updateProductFilters(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PRODUCTS_LISTED_OVERVIEW, model.getFilteredProductList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
