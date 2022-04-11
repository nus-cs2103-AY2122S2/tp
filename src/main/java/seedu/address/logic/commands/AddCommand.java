package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXPENSE_CATEGORY;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.expense.Amount;
import seedu.address.model.expense.Budget;
import seedu.address.model.expense.Expense;


/**
 * Adds an expense to the expense expert.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an expense to Expense Expert. "
            + "Parameters: "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + "[" + PREFIX_EXPENSE_CATEGORY + "CATEGORY] "
            + PREFIX_AMOUNT + "AMOUNT "
            + PREFIX_DATE + "DATE \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DESCRIPTION + "Groceries from Fairprice "
            + PREFIX_EXPENSE_CATEGORY + "GROCERIES "
            + PREFIX_AMOUNT + "100 "
            + PREFIX_DATE + "2022-02-02 \n\n";

    public static final String MESSAGE_SUCCESS = "New expense added: %1$s";
    public static final String MESSAGE_DUPLICATE_EXPENSE = "This expense already exists in Expense Expert";
    public static final String MESSAGE_INVALID_EXPENSE_CATEGORY =
            "This expense category does not exists in Expense Expert."
                    + "\nUse the addCat Command to create a new category";

    private final Expense toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Expense}
     */
    public AddCommand(Expense expense) {
        requireNonNull(expense);
        toAdd = expense;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasExpense(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_EXPENSE);
        }

        if (!model.validExpenseCategory(toAdd)) {
            throw new CommandException(MESSAGE_INVALID_EXPENSE_CATEGORY);
        }

        model.addExpense(toAdd);
        String difference = String.valueOf((model.getBudget().getBudgetAmount().amount - toAdd.getAmount().amount));
        Amount newAmount = new Amount(difference);
        Budget budget = new Budget(newAmount);
        model.setBudget(budget);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
