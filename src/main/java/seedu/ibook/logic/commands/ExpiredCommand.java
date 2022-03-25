package seedu.ibook.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.ibook.model.Model;
import seedu.ibook.model.item.Item;
import seedu.ibook.model.product.filters.AttributeFilter;
import seedu.ibook.model.product.filters.ExpiredFilter;

/**
 * Displays all products that has expired.
 */
public class ExpiredCommand extends Command {

    public static final String COMMAND_WORD = "expired";

    public static final String MESSAGE_SUCCESS = "Listed expired products";

    private final AttributeFilter expiredFilter = new ExpiredFilter();

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.addProductFilter(expiredFilter);
        model.updateFilteredItemListForProducts(Item::isExpired);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
