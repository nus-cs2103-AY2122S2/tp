package seedu.ibook.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.ibook.commons.core.Messages;
import seedu.ibook.commons.core.index.Index;
import seedu.ibook.logic.commands.exceptions.CommandException;
import seedu.ibook.model.Model;
import seedu.ibook.model.product.Product;

/**
 * Deletes a person identified using it's displayed index from the ibook.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the product identified by the index number used in the displayed list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PRODUCT_SUCCESS = "Deleted Product: %1$s";

    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Product> lastShownList = model.getFilteredProductList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PRODUCT_DISPLAYED_INDEX);
        }

        Product productToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.prepareIBookForChanges();
        model.deleteProduct(productToDelete);
        model.saveIBookChanges();
        return new CommandResult(String.format(MESSAGE_DELETE_PRODUCT_SUCCESS, productToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
