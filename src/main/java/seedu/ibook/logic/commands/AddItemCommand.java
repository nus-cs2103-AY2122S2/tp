package seedu.ibook.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.ibook.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_EXPIRY_DATE;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_QUANTITY;

import java.util.List;

import seedu.ibook.commons.core.Messages;
import seedu.ibook.commons.core.index.Index;
import seedu.ibook.logic.commands.exceptions.CommandException;
import seedu.ibook.model.Model;
import seedu.ibook.model.item.Item;
import seedu.ibook.model.product.Product;

/**
 * Adds an item to a specified product in the ibook.
 */
public class AddItemCommand extends Command {

    public static final String COMMAND_WORD = "add-item";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an item to the IBook by the "
            + "index number (a positive integer) of a product used in the displayed product list. "
            + "Parameters: INDEX "
            + PREFIX_EXPIRY_DATE + "EXPIRY DATE "
            + PREFIX_QUANTITY + "QUANTITY "
            + "\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_EXPIRY_DATE + "2022-01-01 "
            + PREFIX_QUANTITY + "10";

    public static final String MESSAGE_SUCCESS = "New item added: %1$s";

    private final Index productIndex;
    private final Item toAdd;

    /**
     * Creates an AddItemCommand to add the specified {@code Item} to a {@code Product}
     */
    public AddItemCommand(Index index, Item item) {
        requireAllNonNull(index, item);
        productIndex = index;
        toAdd = item;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Product> lastShownList = model.getFilteredProductList();
        if (productIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PRODUCT_DISPLAYED_INDEX);
        }

        Product product = lastShownList.get(productIndex.getZeroBased());

        model.addItem(product, toAdd);

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.ibook.logic.commands.AddItemCommand // instanceof handles nulls
                && toAdd.equals(((seedu.ibook.logic.commands.AddItemCommand) other).toAdd));
    }
}
