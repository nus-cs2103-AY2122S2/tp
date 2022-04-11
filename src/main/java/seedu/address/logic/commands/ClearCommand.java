package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.ExpenseExpert;
import seedu.address.model.Model;

/**
 * Clears the expense expert.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Expense Expert has been cleared!";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Clears the records of Expenditure Expert\n"
            + "Example: " + COMMAND_WORD + "\n\n";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setExpenseExpert(new ExpenseExpert());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
