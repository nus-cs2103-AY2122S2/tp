package seedu.ibook.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_EXPIRY_DATE;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_PRICE;

import seedu.ibook.logic.commands.exceptions.CommandException;
import seedu.ibook.model.Model;
import seedu.ibook.model.product.Product;

/**
 * Adds a product to the Ibook.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a product to the IBook. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_CATEGORY + "CATEGORY "
            + PREFIX_EXPIRY_DATE + "EXPIRY DATE "
            + PREFIX_PRICE + "PRICE "
            + PREFIX_DESCRIPTION + "DESCRIPTION"
            + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Tasty Bread "
            + PREFIX_CATEGORY + "Bread "
            + PREFIX_EXPIRY_DATE + "2022-01-01 "
            + PREFIX_PRICE + "3.00 "
            + PREFIX_DESCRIPTION + "Very Tasty";

    public static final String MESSAGE_SUCCESS = "New product added: %1$s";
    public static final String MESSAGE_DUPLICATE_PRODUCT = "This product already exists in the ibook.";

    private final Product toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
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
