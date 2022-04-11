package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyExpenseExpert;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.expense.Budget;
import seedu.address.model.expense.Expense;
import seedu.address.model.expense.ExpenseCategory;
import seedu.address.model.person.Person;
import seedu.address.testutil.ExpenseCategoryBuilder;

class AddCategoryCommandTest {

    @Test
    public void constructor_nullExpense_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCategoryCommand(null));
    }

    @Test
    public void execute_duplicateExpenseCategory_throwsCommandException() {
        ExpenseCategory validExpenseCategory = new ExpenseCategoryBuilder().build();
        AddCategoryCommand addCategoryCommand = new AddCategoryCommand(validExpenseCategory);
        ModelStub modelStub = new ModelStubWithExpenseCategory(validExpenseCategory);

        assertThrows(CommandException.class, AddCategoryCommand.MESSAGE_DUPLICATE_EXPENSE, () -> addCategoryCommand
                .execute(modelStub));
    }



    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getExpenseExpertFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setExpenseExpertFilePath(Path expenseExpertFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addExpense(Expense expense) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setExpenseExpert(ReadOnlyExpenseExpert newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyExpenseExpert getExpenseExpert() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasExpense(Expense expense) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteExpense(Expense target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setExpense(Expense target, Expense editedExpense) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addExpenseCategory(ExpenseCategory expenseCategory) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean validExpenseCategory(Expense expense) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasExpenseCategory(ExpenseCategory expenseCategory) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Expense> getFilteredExpenseList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<ExpenseCategory> getFilteredExpenseCategoryList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredExpenseList(Predicate<Expense> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setBudget(Budget budget) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Budget getBudget() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasUndefinedBudget() {
            throw new AssertionError("This method should not be called.");
        }
    }


    /**
     * A Model stub that contains a single expense.
     */
    private class ModelStubWithExpenseCategory extends AddCategoryCommandTest.ModelStub {
        private final ExpenseCategory expenseCategory;

        ModelStubWithExpenseCategory(ExpenseCategory expenseCategory) {
            requireNonNull(expenseCategory);
            this.expenseCategory = expenseCategory;
        }

        @Override
        public boolean hasExpenseCategory(ExpenseCategory expenseCategory) {
            requireNonNull(expenseCategory);
            return this.expenseCategory.equals(expenseCategory);
        }
    }

}
