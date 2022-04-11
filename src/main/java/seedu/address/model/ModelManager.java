package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.expense.Budget;
import seedu.address.model.expense.Expense;
import seedu.address.model.expense.ExpenseCategory;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of the expense expert data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ExpenseExpert expenseExpert;
    private final UserPrefs userPrefs;
    private final FilteredList<Expense> filteredExpenses;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<ExpenseCategory> expenseCategories;

    /**
     * Initializes a ModelManager with the given expenseExpert and userPrefs.
     */
    public ModelManager(ReadOnlyExpenseExpert expenseExpert, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(expenseExpert, userPrefs);

        logger.fine("Initializing with expense expert: " + expenseExpert + " and user prefs " + userPrefs);

        this.expenseExpert = new ExpenseExpert(expenseExpert);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredExpenses = new FilteredList<>(this.expenseExpert.getExpenseList());
        filteredPersons = new FilteredList<>(this.expenseExpert.getPersonList());
        expenseCategories = new FilteredList<>(this.expenseExpert.getExpenseCategoryList());
    }

    public ModelManager() {
        this(new ExpenseExpert(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getExpenseExpertFilePath() {
        return userPrefs.getExpenseExpertFilePath();
    }

    @Override
    public void setExpenseExpertFilePath(Path expenseExpertFilePath) {
        requireNonNull(expenseExpertFilePath);
        userPrefs.setExpenseExpertFilePath(expenseExpertFilePath);
    }

    //=========== ExpenseExpert ================================================================================

    @Override
    public void setExpenseExpert(ReadOnlyExpenseExpert expenseExpert) {
        this.expenseExpert.resetData(expenseExpert);
    }

    @Override
    public ReadOnlyExpenseExpert getExpenseExpert() {
        return expenseExpert;
    }

    @Override
    public boolean hasExpense(Expense expense) {
        requireNonNull(expense);
        return expenseExpert.hasExpense(expense);
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return expenseExpert.hasPerson(person);
    }

    @Override
    public void deleteExpense(Expense target) {
        expenseExpert.removeExpense(target);
    }

    @Override
    public void addExpense(Expense expense) {
        expenseExpert.addExpense(expense);
        updateFilteredExpenseList(PREDICATE_SHOW_ALL_EXPENSES);
    }

    @Override
    public void setExpense(Expense target, Expense editedExpense) {
        requireAllNonNull(target, editedExpense);

        expenseExpert.setExpense(target, editedExpense);
    }

    @Override
    public void deletePerson(Person target) {
        expenseExpert.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        expenseExpert.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        expenseExpert.setPerson(target, editedPerson);
    }

    @Override
    public void setBudget(Budget budget) {
        requireNonNull(budget);
        expenseExpert.setBudget(budget);
    }

    @Override
    public void addExpenseCategory(ExpenseCategory expenseCategory) {
        expenseExpert.addExpenseCategory(expenseCategory);
    }

    @Override
    public boolean hasExpenseCategory(ExpenseCategory expenseCategory) {
        requireNonNull(expenseCategory);
        return expenseExpert.hasExpenseCategory(expenseCategory);
    }

    @Override
    public boolean validExpenseCategory(Expense expense) {
        requireNonNull(expense);
        return expenseExpert.hasExpenseCategory(expense.getExpenseCategory());
    }

    @Override
    public Budget getBudget() {
        return expenseExpert.getBudget();
    }

    @Override
    public boolean hasUndefinedBudget() {
        return expenseExpert.hasUndefinedBudget();
    }

    //=========== Filtered Expense List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Expense} backed by the internal list of
     * {@code versionedExpenseExpert}
     */
    @Override
    public ObservableList<Expense> getFilteredExpenseList() {
        return filteredExpenses;
    }


    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public ObservableList<ExpenseCategory> getFilteredExpenseCategoryList() {
        return expenseCategories;
    }

    @Override
    public void updateFilteredExpenseList(Predicate<Expense> predicate) {
        requireNonNull(predicate);
        filteredExpenses.setPredicate(predicate);
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return expenseExpert.equals(other.expenseExpert)
                && userPrefs.equals(other.userPrefs)
                && filteredExpenses.equals(other.filteredExpenses)
                && filteredPersons.equals(other.filteredPersons);
    }
}
