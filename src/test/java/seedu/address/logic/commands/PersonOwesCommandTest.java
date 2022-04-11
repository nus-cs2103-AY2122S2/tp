package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ExpenseExpert;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyExpenseExpert;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.expense.Budget;
import seedu.address.model.expense.Expense;
import seedu.address.model.expense.ExpenseCategory;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;


public class PersonOwesCommandTest {
    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PersonOwesCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Person validPerson = new PersonBuilder().build();

        CommandResult commandResult = new PersonOwesCommand(validPerson).execute(modelStub);

        assertEquals(String.format(PersonOwesCommand.MESSAGE_SUCCESS, validPerson), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPerson), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person validPerson = new PersonBuilder().build();
        PersonOwesCommand personOwesCommand = new PersonOwesCommand(validPerson);
        ModelStub modelStub = new ModelStubWithPerson(validPerson);

        assertThrows(CommandException.class,
                PersonOwesCommand.MESSAGE_DUPLICATE_PERSON, () -> personOwesCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Person bob = new PersonBuilder().withPersonName("Bob").build();
        Person alex = new PersonBuilder().withPersonName("Alex").build();
        PersonOwesCommand addBobCommand = new PersonOwesCommand(bob);
        PersonOwesCommand addAlexCommand = new PersonOwesCommand(alex);

        // same object -> returns true
        assertTrue(addBobCommand.equals(addBobCommand));

        // same values -> returns true
        PersonOwesCommand addBobCommandCopy = new PersonOwesCommand(bob);
        assertTrue(addBobCommand.equals(addBobCommandCopy));

        // different types -> returns false
        assertFalse(addBobCommand.equals(1));

        // null -> returns false
        assertFalse(addBobCommand.equals(null));

        // different expense -> returns false
        assertFalse(addBobCommand.equals(addAlexCommand));
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
        public ObservableList<ExpenseCategory> getFilteredExpenseCategoryList() {
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
        public boolean validExpenseCategory(Expense expense) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredExpenseList(Predicate<Expense> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setBudget(Budget budget) {
        }

        @Override
        public Budget getBudget() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
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
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasUndefinedBudget() {
            throw new AssertionError("This method should not be called.");
        }
    }


    /**
     * A Model stub that contains a single Person.
     */
    private class ModelStubWithPerson extends PersonOwesCommandTest.ModelStub {
        private final Person person;

        ModelStubWithPerson(Person person) {
            requireNonNull(person);
            this.person = person;
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return this.person.equals(person);
        }


    }

    /**
     * A Model stub that contains a single expense.
     */
    private class ModelStubWithBudget extends PersonOwesCommandTest.ModelStub {
        private final Budget budget;

        ModelStubWithBudget(Budget budget) {
            requireNonNull(budget);
            this.budget = budget;
        }

        @Override
        public Budget getBudget() {
            return this.budget;
        }


    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends PersonOwesCommandTest.ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();
        // private Budget budget;

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::equals);
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }


        @Override
        public ReadOnlyExpenseExpert getExpenseExpert() {
            return new ExpenseExpert();
        }
    }

}
