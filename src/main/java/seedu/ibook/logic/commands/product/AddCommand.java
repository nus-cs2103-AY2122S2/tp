package seedu.ibook.logic.commands.product;

import static java.util.Objects.requireNonNull;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_DISCOUNT_RATE;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_DISCOUNT_START;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_PRICE;

import seedu.ibook.logic.commands.Command;
import seedu.ibook.logic.commands.CommandResult;
import seedu.ibook.logic.commands.exceptions.CommandException;
import seedu.ibook.model.Model;
import seedu.ibook.model.product.Product;

/**
 * Represents a command that when executed adds a product to the iBook.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a product to the IBook. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_CATEGORY + "CATEGORY "
            + PREFIX_PRICE + "PRICE "
            + PREFIX_DISCOUNT_RATE + "DISCOUNT RATE "
            + PREFIX_DISCOUNT_START + "DISCOUNT START "
            + PREFIX_DESCRIPTION + "DESCRIPTION"
            + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Tasty Bread "
            + PREFIX_CATEGORY + "Bread "
            + PREFIX_PRICE + "3.00 "
            + PREFIX_DISCOUNT_RATE + "25 "
            + PREFIX_DISCOUNT_START + "3 "
            + PREFIX_DESCRIPTION + "Very Tasty";

    public static final String MESSAGE_SUCCESS = "New product added:\n%1$s";
    public static final String MESSAGE_DUPLICATE_PRODUCT = "This product already exists in the ibook.";

    private final Product toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Product}
     */
    public AddCommand(Product product) {
        requireNonNull(product);
        toAdd = product;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasProduct(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PRODUCT);
        }

        model.prepareIBookForChanges();
        model.addProduct(toAdd);
        model.saveIBookChanges();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
