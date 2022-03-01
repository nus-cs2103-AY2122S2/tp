package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.expense.Expense;
import seedu.address.model.Model;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an expense to the Expense Expert. "
            + "Parameters: "
            + PREFIX_DESCRIPTION + "NAME "
            + PREFIX_CATEGORY + "CATEGORY"
            + PREFIX_AMOUNT + "AMOUNT"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DESCRIPTION + "Electricity Bills"
            + PREFIX_CATEGORY + "Bills"
            + PREFIX_AMOUNT + "$100";

    public static final String MESSAGE_SUCCESS = "New expense added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This expense already exists in the address book";

    private final Expense toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(Expense expense) {
        requireNonNull(expense);
        toAdd = expense;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasExpense(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addExpense(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
