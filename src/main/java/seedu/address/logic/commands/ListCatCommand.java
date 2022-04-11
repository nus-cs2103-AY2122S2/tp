package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.expense.ExpenseCategory;

public class ListCatCommand extends Command {
    public static final String COMMAND_WORD = "listCat";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all Categories\n" + "Example: "
            + COMMAND_WORD + "\n\n";

    public static final String MESSAGE_SUCCESS = "Expense Categories defined are: \n%1$s";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        ObservableList<ExpenseCategory> expenseCategoryList = model.getFilteredExpenseCategoryList();
        return new CommandResult(String.format(MESSAGE_SUCCESS, formatCategoryList(expenseCategoryList)));
    }

    public String formatCategoryList(ObservableList<ExpenseCategory> expenseCategoryList) {
        return expenseCategoryList.toString();
    }
}
