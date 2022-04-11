package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyExpenseExpert;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.expense.Budget;
import seedu.address.model.expense.Expense;
import seedu.address.model.expense.ExpenseCategory;
import seedu.address.model.person.Person;
import seedu.address.testutil.BudgetBuilder;

public class SetBudgetCommandTest {
    @Test
    public void constructor_nullBudget_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SetBudgetCommand(null));
    }

    @Test
    public void execute_nonZeroBudgetAcceptedByModel_setSuccessful() throws Exception {
        ModelStubAcceptingBudgetSet modelStub = new ModelStubAcceptingBudgetSet();
        Budget validBudget = new BudgetBuilder().build();

        CommandResult commandResult = new SetBudgetCommand(validBudget).execute(modelStub);

        assertEquals(String.format(SetBudgetCommand.MESSAGE_SUCCESS, validBudget.getBudgetMonth(),
                validBudget.getBudgetAmount()), commandResult.getFeedbackToUser());
        assertEquals(validBudget, modelStub.budget);
    }

    @Test
    public void execute_zeroBudgetAcceptedByModel_setSuccessful() throws Exception {
        ModelStubAcceptingBudgetSet modelStub = new ModelStubAcceptingBudgetSet();
        Budget validBudget = new BudgetBuilder().withBudgetDate("1900-11-11").build();

        CommandResult commandResult = new SetBudgetCommand(validBudget).execute(modelStub);

        assertEquals(String.format(SetBudgetCommand.BUDGET_SET_TO_UNDEFINED, validBudget.getBudgetMonth()),
                commandResult.getFeedbackToUser());
        assertEquals(validBudget, modelStub.budget);
    }

    @Test
    public void equals() {
        Budget budgetOf500 = new BudgetBuilder().withBudgetAmount("500").build();
        Budget budgetOf300 = new BudgetBuilder().withBudgetAmount("300").build();
        SetBudgetCommand set500BudgetCommand = new SetBudgetCommand(budgetOf500);
        SetBudgetCommand set300BudgetCommand = new SetBudgetCommand(budgetOf300);

        // same object -> returns true
        assertTrue(set500BudgetCommand.equals(set500BudgetCommand));

        // same values -> returns true
        SetBudgetCommand set500BudgetCommandCopy = new SetBudgetCommand(budgetOf500);
        assertTrue(set500BudgetCommand.equals(set500BudgetCommandCopy));

        // different types -> returns false
        assertFalse(set500BudgetCommand.equals(1));

        // null -> returns false
        assertFalse(set500BudgetCommand.equals(null));

        // different budget -> returns false
        assertFalse(set500BudgetCommand.equals(set300BudgetCommand));
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
        public boolean validExpenseCategory(Expense expense) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasExpense(Expense expense) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteExpense(Expense target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setExpense(Expense target, Expense editedExpense) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addExpenseCategory(ExpenseCategory expenseCategory) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }


        @Override
        public void deletePerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasExpenseCategory(ExpenseCategory expenseCategory) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
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
     * A Model stub that always accept the budget being set.
     */
    private class ModelStubAcceptingBudgetSet extends ModelStub {
        private Budget budget;

        @Override
        public void setBudget(Budget budget) {
            requireNonNull(budget);
            this.budget = budget;
        }

        @Override
        public Budget getBudget() {
            return this.budget;
        }
    }
}
