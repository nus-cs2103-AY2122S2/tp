package seedu.ibook.logic.commands.item;

import static java.util.Objects.requireNonNull;

import seedu.ibook.commons.core.index.CompoundIndex;
import seedu.ibook.logic.commands.Command;
import seedu.ibook.logic.commands.CommandResult;
import seedu.ibook.logic.commands.exceptions.CommandException;
import seedu.ibook.model.Model;
import seedu.ibook.model.item.Item;
import seedu.ibook.model.product.Product;

/**
 * Represents a command that when executed deletes an item of a specified product in the iBook.
 */
public class DeleteItemCommand extends Command {

    public static final String COMMAND_WORD = "delete-item";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the item identified by the index number used in the displayed list.\n"
            + "Parameters: INDEX (must be a positive integer pair separated by '"
            + CompoundIndex.SEPARATOR
            + "' at most " + Integer.MAX_VALUE + ")\n"
            + "Example: " + COMMAND_WORD + " 1" + CompoundIndex.SEPARATOR + "1";

    public static final String MESSAGE_DELETE_ITEM_SUCCESS = "Deleted Item: %1$s";

    private final CompoundIndex targetIndex;

    public DeleteItemCommand(CompoundIndex targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Product targetProduct = model.getProduct(targetIndex.getFirst());
        Item itemToDelete = model.getItem(targetIndex);

        model.prepareIBookForChanges();
        model.deleteItem(targetProduct, itemToDelete);
        model.saveIBookChanges();
        model.clearProductFilters();

        return new CommandResult(String.format(MESSAGE_DELETE_ITEM_SUCCESS, itemToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof DeleteItemCommand // instanceof handles nulls
            && targetIndex.equals(((DeleteItemCommand) other).targetIndex)); // state check
    }
}
