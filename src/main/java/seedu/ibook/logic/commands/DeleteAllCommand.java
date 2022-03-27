package seedu.ibook.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.ibook.logic.commands.exceptions.CommandException;
import seedu.ibook.model.Model;
import seedu.ibook.model.product.Product;

/**
 * Represents a command that when executed deletes from the iBook
 * all products shown in the displayed list.
 */
public class DeleteAllCommand extends Command {

    public static final String COMMAND_WORD = "delete-all";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes all products in the displayed list.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_DELETE_ALL_PRODUCT_SUCCESS = "All products deleted.\n";
    public static final String MESSAGE_DELETED_PRODUCT = "Deleted Product: %1$s\n";
    public static final String MESSAGE_NO_PRODUCT_TO_DELETE = "There is no products to delete!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Product> lastShownList = model.getFilteredProductList();
        List<Product> productsToDelete = new ArrayList<>(lastShownList);

        if (productsToDelete.size() == 0) {
            throw new CommandException(MESSAGE_NO_PRODUCT_TO_DELETE);
        }

        StringBuilder deletedProductDescription = new StringBuilder();
        for (Product target : productsToDelete) {
            model.deleteProduct(target);
            deletedProductDescription.append(String.format(MESSAGE_DELETED_PRODUCT, target));
        }

        return new CommandResult(MESSAGE_DELETE_ALL_PRODUCT_SUCCESS + deletedProductDescription);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof DeleteAllCommand;
    }
}
