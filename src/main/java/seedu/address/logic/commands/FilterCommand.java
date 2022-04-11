package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXPENSE_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILTER_DATE;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.expense.Expense;
import seedu.address.model.expense.ExpenseCategoryIsParsedCategoryPredicate;
import seedu.address.model.expense.ExpenseDateIsParsedDatePredicate;
import seedu.address.model.expense.PredicateChain;

/**
 * Filters expenses on expense expert.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Filters expenses by date in [yyyy-mm] or [yyyy-mm-dd] format. \n"
            + "Parameters: "
            + "(" + PREFIX_FILTER_DATE + "FILTER_DATE)"
            + "(" + PREFIX_EXPENSE_CATEGORY + "EXPENSE_CATEGORY)" + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_FILTER_DATE + "2022-03 \n\n";

    public static final String MESSAGE_SUCCESS = "Expense(s) filtered: %1$s";

    private final ExpenseDateIsParsedDatePredicate datePredicate;
    private final ExpenseCategoryIsParsedCategoryPredicate categoryPredicate;

    /**
     * Creates an FilterCommand to add the filter {@code Expense} by date/month.
     */
    public FilterCommand(ExpenseDateIsParsedDatePredicate datePredicate,
                         ExpenseCategoryIsParsedCategoryPredicate categoryPredicate) {
        if (datePredicate == null) {
            requireNonNull(categoryPredicate);
            this.datePredicate = null;
            this.categoryPredicate = categoryPredicate;
        } else if (categoryPredicate == null) {
            requireNonNull(datePredicate);
            this.categoryPredicate = null;
            this.datePredicate = datePredicate;
        } else {
            this.datePredicate = datePredicate;
            this.categoryPredicate = categoryPredicate;
        }
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        if (datePredicate == null) {
            model.updateFilteredExpenseList(categoryPredicate);
            return new CommandResult(
                    String.format(Messages.MESSAGE_EXPENSES_LISTED_OVERVIEW, model.getFilteredExpenseList().size()));
        } else if (categoryPredicate == null) {
            model.updateFilteredExpenseList(datePredicate);
            return new CommandResult(
                    String.format(Messages.MESSAGE_EXPENSES_LISTED_OVERVIEW, model.getFilteredExpenseList().size()));
        }

        List<Predicate<Expense>> predicateList = new ArrayList<>(List.of(datePredicate, categoryPredicate));
        PredicateChain predicateChain = new PredicateChain(predicateList);
        model.updateFilteredExpenseList(predicateChain);

        return new CommandResult(
                String.format(Messages.MESSAGE_EXPENSES_LISTED_OVERVIEW, model.getFilteredExpenseList().size()));
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof FilterCommand) {
            FilterCommand temp = (FilterCommand) other;

            if (datePredicate == null && categoryPredicate == null) {
                return temp.datePredicate == null && temp.categoryPredicate == null;
            }

            if (datePredicate == null) {
                return temp.datePredicate == null && categoryPredicate.equals(temp.categoryPredicate);
            }

            if (categoryPredicate == null) {
                return temp.categoryPredicate == null && datePredicate.equals(temp.datePredicate);
            }

            return datePredicate.equals(temp.datePredicate) && categoryPredicate.equals(temp.categoryPredicate);

        } else {
            return false;
        }
    }
}
