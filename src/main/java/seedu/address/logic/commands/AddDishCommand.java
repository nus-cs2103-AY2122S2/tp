package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.dish.Dish;

/**
 * Adds a person to the address book.
 */
public class AddDishCommand extends Command {

    public static final String COMMAND_WORD = "adddish";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a dish to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PRICE + "PRICE "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Kimchi Fried Rice "
            + PREFIX_PRICE + "15.50 ";

    public static final String MESSAGE_SUCCESS = "New dish added: %1$s";
    public static final String MESSAGE_DUPLICATE_DISH = "This dish already exists in FoodOnWheels";

    private final Dish toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddDishCommand(Dish dish) {
        requireNonNull(dish);
        toAdd = dish;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasDish(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_DISH);
        }

        model.addDish(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), false, false, true, false, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddDishCommand // instanceof handles nulls
                && toAdd.equals(((AddDishCommand) other).toAdd));
    }
}

