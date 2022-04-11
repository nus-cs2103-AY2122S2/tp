package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.expense.Budget;

/**
 * Sets the monthly budget for the expense expert.
 */
public class SetBudgetCommand extends Command {

    public static final String COMMAND_WORD = "budget";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets the monthly budget (max. $99999.99) "
            + "for Expense Expert. "
            + "Parameters: "
            + PREFIX_AMOUNT + "BUDGET AMOUNT \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_AMOUNT + "100 \n\n";

    public static final String MESSAGE_SUCCESS = "Budget for %1$s is now $%2$s.";

    public static final String BUDGET_SET_TO_UNDEFINED = "Budget for %1$s is now undefined. "
            + "\n\nPlease set a new budget if you wish to use Expense Expert normally during that month.";

    private final Budget budget;

    /**
     * Creates an SetBudgetCommand to set the specified {@code Budget}
     */
    public SetBudgetCommand(Budget budget) {
        requireNonNull(budget);
        this.budget = budget;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.setBudget(budget);

        if (budget.isUndefined()) {
            return new CommandResult(String.format(BUDGET_SET_TO_UNDEFINED, budget.getBudgetMonth()));
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, budget.getBudgetMonth(), budget.getBudgetAmount()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SetBudgetCommand // instanceof handles nulls
                && budget.equals(((SetBudgetCommand) other).budget));
    }
}
