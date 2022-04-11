package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXPENSE_CATEGORY;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditExpenseDescriptor;
import seedu.address.model.expense.Expense;


/**
 * A utility class for Expense.
 */
public class ExpenseUtil {

    /**
     * Returns an add command string for adding the {@code expense}.
     */
    public static String getAddCommand(Expense expense) {
        return AddCommand.COMMAND_WORD + " " + getExpenseDetails(expense);
    }

    /**
     * Returns the part of command string for the given {@code expense}'s details.
     */
    public static String getExpenseDetails(Expense expense) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_DESCRIPTION + expense.getDescription().description + " ");
        sb.append(PREFIX_EXPENSE_CATEGORY + expense.getExpenseCategory().expenseCategory + " ");
        sb.append(PREFIX_AMOUNT + expense.getAmount().amount.toString() + " ");
        sb.append(PREFIX_DATE + expense.getExpenseDate().date.toString() + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditExpenseDescriptor}'s details.
     */
    public static String getEditExpenseDescriptorDetails(EditExpenseDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getDescription().ifPresent(description -> sb.append(PREFIX_DESCRIPTION)
                .append(description.description).append(" "));
        descriptor.getExpenseCategory().ifPresent(expenseCategory -> sb.append(PREFIX_EXPENSE_CATEGORY)
                .append(expenseCategory.expenseCategory).append(" "));
        descriptor.getAmount().ifPresent(amount -> sb.append(PREFIX_AMOUNT)
                .append(amount.amount.toString()).append(" "));
        descriptor.getExpenseDate().ifPresent(expenseDate -> sb.append(PREFIX_DATE)
                .append(expenseDate.date.toString()).append(" "));
        return sb.toString();
    }
}
