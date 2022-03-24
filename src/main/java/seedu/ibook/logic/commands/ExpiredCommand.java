package seedu.ibook.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.ibook.model.Model;
import seedu.ibook.model.item.Item;
import seedu.ibook.model.product.Product;

public class ExpiredCommand extends Command {

    public static final String COMMAND_WORD = "expired";

    public static final String MESSAGE_SUCCESS = "Listed expired products";

    private Predicate<Product> expiredPredicate = Product::hasExpiredItems;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateProductFilters(expiredPredicate);
        model.updateFilteredItemListForProducts(Item::isExpired);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
