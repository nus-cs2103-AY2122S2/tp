package seedu.address.logic.commands;


import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXPENSE_CATEGORY;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.expense.ExpenseCategory;

public class AddCategoryCommand extends Command {

    public static final String COMMAND_WORD = "addCat";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an expense Category to Expense Expert. "
            + "Parameters: "
            + PREFIX_EXPENSE_CATEGORY + "DESCRIPTION \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_EXPENSE_CATEGORY + "Entertainment \n\n";

    public static final String MESSAGE_SUCCESS = "New expense category added: %1$s";
    // public static final String BUDGET_EDITED = "Monthly budget allowance for this category is now: %1$s";
    public static final String MESSAGE_DUPLICATE_EXPENSE = "This expense category already exists in Expense Expert";

    private final ExpenseCategory toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Expense}
     */
    public AddCategoryCommand(ExpenseCategory expenseCategory) {
        requireNonNull(expenseCategory);
        toAdd = expenseCategory;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasExpenseCategory(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_EXPENSE);
        }

        model.addExpenseCategory(toAdd);

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCategoryCommand) other).toAdd));
    }
}
