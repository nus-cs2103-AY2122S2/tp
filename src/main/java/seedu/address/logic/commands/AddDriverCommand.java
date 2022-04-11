package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.customer.Customer;
import seedu.address.model.driver.Driver;

/**
 * Adds a driver to the database.
 */
public class AddDriverCommand extends Command {
    public static final String COMMAND_WORD = "adddriver";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a driver to the database. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 ";

    public static final String MESSAGE_SUCCESS = "New driver added: %1$s";
    public static final String MESSAGE_DUPLICATE_DRIVER = "This driver already exists in the database";
    public static final String MESSAGE_DUPLICATE_CUSTOMER = "A customer with the same phone number "
            + "already exists. Please use another phone number as phone numbers are unique in FOW.";

    private final Driver toAdd;
    private final Customer toAddCustomer;

    /**
     * Creates an AddDriverCommand to add the specified {@code Driver}
     */
    public AddDriverCommand(Driver driver, Customer customer) {
        requireNonNull(driver);
        toAdd = driver;
        toAddCustomer = customer;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasDriver(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_DRIVER);
        }

        if (model.hasCustomer(toAddCustomer)) {
            throw new CommandException(MESSAGE_DUPLICATE_CUSTOMER);
        }

        model.addDriver(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), false, false, false, true, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddDriverCommand // instanceof handles nulls
                && toAdd.equals(((AddDriverCommand) other).toAdd));
    }
}
