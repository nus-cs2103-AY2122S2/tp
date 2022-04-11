package seedu.address.testutil;

import seedu.address.logic.commands.EditCommand.EditExpenseDescriptor;
import seedu.address.model.expense.Amount;
import seedu.address.model.expense.Date;
import seedu.address.model.expense.Description;
import seedu.address.model.expense.Expense;
import seedu.address.model.expense.ExpenseCategory;


/**
 * A utility class to help with building EditExpenseDescriptor objects.
 */
public class EditExpenseDescriptorBuilder {

    private EditExpenseDescriptor descriptor;

    public EditExpenseDescriptorBuilder() {
        descriptor = new EditExpenseDescriptor();
    }

    public EditExpenseDescriptorBuilder(EditExpenseDescriptor descriptor) {
        this.descriptor = new EditExpenseDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditExpenseDescriptor} with fields containing {@code expense}'s details
     */
    public EditExpenseDescriptorBuilder(Expense expense) {
        descriptor = new EditExpenseDescriptor();
        descriptor.setDescription(expense.getDescription());
        descriptor.setExpenseCategory(expense.getExpenseCategory());
        descriptor.setAmount(expense.getAmount());
        descriptor.setExpenseDate(expense.getExpenseDate());
    }

    /**
     * Sets the {@code Description} of the {@code EditExpenseDescriptor} that we are building.
     */
    public EditExpenseDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    /**
     * Sets the {@code ExpenseCategory} of the {@code EditExpenseDescriptor} that we are building.
     */
    public EditExpenseDescriptorBuilder withExpenseCategory(String expenseCategory) {
        descriptor.setExpenseCategory(new ExpenseCategory(expenseCategory));
        return this;
    }

    /**
     * Sets the {@code Amount} of the {@code EditExpenseDescriptor} that we are building.
     */
    public EditExpenseDescriptorBuilder withAmount(String amount) {
        descriptor.setAmount(new Amount(amount));
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code EditExpenseDescriptor} that we are building.
     * @param expenseDate
     * @return editexpensedesciptor
     */
    public EditExpenseDescriptorBuilder withExpenseDate(String expenseDate) {
        descriptor.setExpenseDate(new Date(expenseDate));
        return this;
    }

    /**
     * build an editexpensedescriptor
     * @return editexpensedescriptor
     */
    public EditExpenseDescriptor build() {
        return descriptor;
    }
}
