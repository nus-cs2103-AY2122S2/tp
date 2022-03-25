package seedu.ibook.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_PRICE;

import java.util.List;

import seedu.ibook.commons.core.Messages;
import seedu.ibook.model.Model;
import seedu.ibook.model.product.filters.AttributeFilter;

/**
 * Finds and lists all products in ibook which has details matching the searching criteria.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists all products that matches with the key value pair "
            + "and displays them as a list with index numbers.\n"
            + "Parameters: PREFIX: VALUE [MORE_PREFIX: VALUE]...\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + ": bottle " + PREFIX_PRICE + ": 3.00";

    private final List<AttributeFilter> filterList;

    /**
     * Creates a Find Command with no filters.
     */
    public FindCommand() {
        // allow no filters, i.e. null
        this.filterList = null;
    }

    /**
     * Creates a Find Command with the filters of the {@code filterList}
     * @param filterList
     */
    public FindCommand(List<AttributeFilter> filterList) {
        requireNonNull(filterList);
        this.filterList = filterList;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (filterList != null) {
            filterList.forEach(model::addProductFilter);
        }
        return new CommandResult(
                String.format(Messages.MESSAGE_PRODUCTS_LISTED_OVERVIEW, model.getFilteredProductList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof FindCommand)) {
            return false;
        }

        assert filterList != null;
        return filterList.equals(((FindCommand) other).filterList);
    }
}
