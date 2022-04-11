package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.expense.Budget;
import seedu.address.model.expense.Expense;
import seedu.address.model.expense.ExpenseCategory;
import seedu.address.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Expense> PREDICATE_SHOW_ALL_EXPENSES = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' expense expert file path.
     */
    Path getExpenseExpertFilePath();

    /**
     * Sets the user prefs' expense expert file path.
     */
    void setExpenseExpertFilePath(Path expenseExpertFilePath);

    /**
     * Replaces expense expert data with the data in {@code expenseExpert}.
     */
    void setExpenseExpert(ReadOnlyExpenseExpert expenseExpert);

    /** Returns the ExpenseExpert */
    ReadOnlyExpenseExpert getExpenseExpert();

    /**
     * Returns true if a expense with the same identity as {@code expense} exists in the expense expert.
     */
    boolean hasExpense(Expense expense);

    /**
     * Returns true if a person with the same identity as {@code expense} exists in the expense expert.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given expense.
     * The expense must exist in the expense expert.
     */
    void deleteExpense(Expense expense);

    /**
     * Deletes the given expense.
     * The person must exist in the expense expert.
     */
    void deletePerson(Person person);

    /**
     * Adds the given expense.
     * {@code expenseCategory} must not already exist in the expense expert.
     */
    void addExpense(Expense expense);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the expense expert.
     */
    void addPerson(Person person);

    /**
     * Replaces the given expense {@code target} with {@code editedExpense}.
     * {@code target} must exist in the expense expert.
     * The expense identity of {@code editedExpense} must not be the same as another existing expense in
     * the expense expert.
     */
    void setExpense(Expense target, Expense editedExpense);

    /**
     * Replaces the given person {@code target} with {@code editedExpense}.
     * {@code target} must exist in the expense expert.
     * The expense identity of {@code editedPerson} must not be the same as another existing person in
     * the expense expert.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Adds the given expense category.
     * {@code expenseCategory} must not already exist in the expense expert.
     */
    void addExpenseCategory(ExpenseCategory expenseCategory);


    /**
     * Returns true if a expense has a valid Expense Category {@code expense} exists in the expense expert.
     */
    boolean validExpenseCategory(Expense expense);

    /**
     * Returns true if a expense category with the same identity as {@code expense} exists in the expense expert.
     */
    boolean hasExpenseCategory(ExpenseCategory expenseCategory);

    /** Returns an unmodifiable view of the filtered expense list */
    ObservableList<Expense> getFilteredExpenseList();

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the filtered Expense Category list */
    ObservableList<ExpenseCategory> getFilteredExpenseCategoryList();

    /**
     * Updates the filter of the filtered expense list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredExpenseList(Predicate<Expense> predicate);

    /**
     * Updates the filter of the filtered Person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Replaces the current budget in {@code ExpenseExpert} to {@code budget}
     */
    void setBudget(Budget budget);

    /** Returns the current budget in {@code ExpenseExpert} */
    Budget getBudget();

    /** Returns whether the budget in {@code ExpenseExpert} is defined */
    boolean hasUndefinedBudget();
}
