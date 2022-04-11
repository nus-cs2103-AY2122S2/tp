package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXPENSE_CATEGORY;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EXPENSES;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.expense.Amount;
import seedu.address.model.expense.Budget;
import seedu.address.model.expense.Date;
import seedu.address.model.expense.Description;
import seedu.address.model.expense.Expense;
import seedu.address.model.expense.ExpenseCategory;


/**
 * Edits the details of an existing expense in the expense expert.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the expense identified "
            + "by the index number used in the displayed expense list. \n"
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "(" + PREFIX_DESCRIPTION + "DESCRIPTION) "
            + "(" + PREFIX_EXPENSE_CATEGORY + "CATEGORY) "
            + "(" + PREFIX_AMOUNT + "AMOUNT)"
            + "(" + PREFIX_DATE + "DATE) \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DESCRIPTION + "Groceries from ShengShiong "
            + PREFIX_AMOUNT + "120 "
            + PREFIX_DATE + "2022-02-02\n\n";

    public static final String MESSAGE_EDIT_EXPENSE_SUCCESS = "Edited Expense: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_EXPENSE = "This expense already exists in Expense Expert.";
    public static final String MESSAGE_INVALID_EXPENSE_CATEGORY =
            "This expense category does not exists in Expense Expert."
                    + "\nUse the addCat Command to create a new category";

    private final Index index;
    private final EditExpenseDescriptor editExpenseDescriptor;

    /**
     * @param index of the expense in the filtered expense list to edit
     * @param editExpenseDescriptor details to edit the expense with
     */
    public EditCommand(Index index, EditExpenseDescriptor editExpenseDescriptor) {
        requireNonNull(index);
        requireNonNull(editExpenseDescriptor);

        this.index = index;
        this.editExpenseDescriptor = new EditExpenseDescriptor(editExpenseDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Expense> lastShownList = model.getFilteredExpenseList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EXPENSE_DISPLAYED_INDEX);
        }

        Expense expenseToEdit = lastShownList.get(index.getZeroBased());
        Expense editedExpense = createEditedExpense(expenseToEdit, editExpenseDescriptor);

        if (!expenseToEdit.equals(editedExpense) && model.hasExpense(editedExpense)) {
            throw new CommandException(MESSAGE_DUPLICATE_EXPENSE);
        }

        if (!model.hasExpenseCategory(editedExpense.getExpenseCategory())) {
            throw new CommandException(MESSAGE_INVALID_EXPENSE_CATEGORY);
        }

        model.setExpense(expenseToEdit, editedExpense);
        model.updateFilteredExpenseList(PREDICATE_SHOW_ALL_EXPENSES);
        double difference = expenseToEdit.getAmount().amount - editedExpense.getAmount().amount;
        String amt = String.valueOf((model.getBudget().getBudgetAmount().amount + difference));
        Amount newAmount = new Amount(amt);
        Budget budget = new Budget(newAmount);
        model.setBudget(budget);
        return new CommandResult(String.format(MESSAGE_EDIT_EXPENSE_SUCCESS, editedExpense));
    }

    /**
     * Creates and returns a {@code Expense} with the details of {@code expenseToEdit}
     * edited with {@code editExpenseDescriptor}.
     */
    private static Expense createEditedExpense(Expense expenseToEdit, EditExpenseDescriptor editExpenseDescriptor) {
        assert expenseToEdit != null;

        Description updatedDescription = editExpenseDescriptor.getDescription().orElse(expenseToEdit.getDescription());
        ExpenseCategory updatedExpenseCategory = editExpenseDescriptor
                .getExpenseCategory().orElse(expenseToEdit.getExpenseCategory());
        Amount updatedAmount = editExpenseDescriptor.getAmount().orElse(expenseToEdit.getAmount());
        Date updatedExpenseDate = editExpenseDescriptor.getExpenseDate().orElse(expenseToEdit.getExpenseDate());

        return new Expense(updatedDescription, updatedExpenseCategory, updatedAmount, updatedExpenseDate);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editExpenseDescriptor.equals(e.editExpenseDescriptor);
    }

    /**
     * Stores the details to edit the expense with. Each non-empty field value will replace the
     * corresponding field value of the expense.
     */
    public static class EditExpenseDescriptor {
        private Description description;
        private ExpenseCategory expenseCategory;
        private Amount amount;
        private Date expenseDate;

        public EditExpenseDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditExpenseDescriptor(EditExpenseDescriptor toCopy) {
            setDescription(toCopy.description);
            setExpenseCategory(toCopy.expenseCategory);
            setAmount(toCopy.amount);
            setExpenseDate(toCopy.expenseDate);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(description, expenseCategory, amount);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setExpenseCategory(ExpenseCategory expenseCategory) {
            this.expenseCategory = expenseCategory;
        }

        public Optional<ExpenseCategory> getExpenseCategory() {
            return Optional.ofNullable(expenseCategory);
        }

        public void setAmount(Amount amount) {
            this.amount = amount;
        }

        public Optional<Amount> getAmount() {
            return Optional.ofNullable(amount);
        }

        public void setExpenseDate(Date expenseDate) {
            this.expenseDate = expenseDate;
        }

        public Optional<Date> getExpenseDate() {
            return Optional.ofNullable(expenseDate);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditExpenseDescriptor)) {
                return false;
            }

            // state check
            EditExpenseDescriptor e = (EditExpenseDescriptor) other;

            return getDescription().equals(e.getDescription())
                    && getExpenseCategory().equals(e.getExpenseCategory())
                    && getAmount().equals(e.getAmount())
                    && getExpenseDate().equals(e.getExpenseDate());
        }
    }
}
