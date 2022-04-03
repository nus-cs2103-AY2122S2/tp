package seedu.ibook.logic.commands.item;

import static java.util.Objects.requireNonNull;
import static seedu.ibook.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_EXPIRY_DATE;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_QUANTITY;

import seedu.ibook.commons.core.index.Index;
import seedu.ibook.logic.commands.Command;
import seedu.ibook.logic.commands.CommandResult;
import seedu.ibook.logic.commands.exceptions.CommandException;
import seedu.ibook.model.Model;
import seedu.ibook.model.item.ItemDescriptor;
import seedu.ibook.model.product.Product;

/**
 * Represents a command that when executed adds an item to a specified product in the iBook.
 */
public class AddItemCommand extends Command {

    public static final String COMMAND_WORD = "add-item";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an item to the IBook by the "
            + "index number (a positive integer at most " + Integer.MAX_VALUE + ")"
            + " of a product used in the displayed product list. "
            + "Parameters: INDEX "
            + PREFIX_EXPIRY_DATE + "EXPIRY DATE "
            + PREFIX_QUANTITY + "QUANTITY "
            + "\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_EXPIRY_DATE + "2022-01-01 "
            + PREFIX_QUANTITY + "10";

    public static final String MESSAGE_SUCCESS = "New item added to %1$s:\n%2$s";

    private final Index productIndex;
    private final ItemDescriptor toAdd;

    /**
     * Creates an AddItemCommand to add the specified {@code Item} to a {@code Product}
     */
    public AddItemCommand(Index index, ItemDescriptor item) {
        requireAllNonNull(index, item);
        productIndex = index;
        toAdd = item;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Product product = model.getProduct(productIndex);

        model.prepareIBookForChanges();
        model.addItem(product, toAdd.toItem(product));
        model.saveIBookChanges();
        model.clearProductFilters();

        return new CommandResult(String.format(MESSAGE_SUCCESS, product.getName(), toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddItemCommand // instanceof handles nulls
                && toAdd.equals(((AddItemCommand) other).toAdd));
    }
}
