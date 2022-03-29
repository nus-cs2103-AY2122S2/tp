package seedu.ibook.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.ibook.commons.core.Messages;
import seedu.ibook.model.Model;
import seedu.ibook.model.product.Product;
import seedu.ibook.model.product.filters.AttributeFilter;
import seedu.ibook.model.product.filters.OutOfStockFilter;

public class OutOfStockCommand extends Command {
    public static final String COMMAND_WORD = "out-of-stock";

    public static final String MESSAGE_SUCCESS = "Listed products that are out of stock.\n";

    private final AttributeFilter outOfStockFilter = new OutOfStockFilter();

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.addProductFilter(outOfStockFilter);
        model.updateFilteredItemListForProducts(Product.PREDICATE_SHOW_ALL_ITEMS);
        return new CommandResult(MESSAGE_SUCCESS
                + String.format(Messages.MESSAGE_PRODUCTS_LISTED_OVERVIEW, model.getFilteredProductList().size()));
    }
}
