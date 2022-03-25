package seedu.ibook.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.ibook.commons.core.Messages;
import seedu.ibook.commons.core.index.CompoundIndex;
import seedu.ibook.logic.commands.exceptions.CommandException;
import seedu.ibook.model.Model;
import seedu.ibook.model.item.Item;
import seedu.ibook.model.product.Product;

/**
 * Deletes an item of a specified product in the ibook.
 */
public class DeleteItemCommand extends Command {

    public static final String COMMAND_WORD = "delete-item";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the item identified by the index number used in the displayed list.\n"
            + "Parameters: INDEX (must be a positive integer pair separated by '-')\n"
            + "Example: " + COMMAND_WORD + " 1-1";

    public static final String MESSAGE_DELETE_PRODUCT_SUCCESS = "Deleted Item: %1$s";

    private final CompoundIndex targetIndex;

    public DeleteItemCommand(CompoundIndex targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Product> lastShownList = model.getFilteredProductList();

        if (targetIndex.getZeroBasedFirst() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);
        }

        Product targetProduct = lastShownList.get(targetIndex.getZeroBasedFirst());
        List<Item> targetItemList = targetProduct.getItems().asUnmodifiableObservableList();

        if (targetIndex.getZeroBasedSecond() >= targetItemList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);
        }

        Item itemToDelete = targetItemList.get(targetIndex.getZeroBasedSecond());

        model.deleteItem(targetProduct, itemToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PRODUCT_SUCCESS, itemToDelete));
    }
}
