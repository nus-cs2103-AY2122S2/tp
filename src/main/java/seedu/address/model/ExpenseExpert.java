package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.address.model.expense.Amount;
import seedu.address.model.expense.Budget;
import seedu.address.model.expense.Date;
import seedu.address.model.expense.Expense;
import seedu.address.model.expense.ExpenseCategory;
import seedu.address.model.expense.ExpenseCategoryList;
import seedu.address.model.expense.UniqueExpenseList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameExpense comparison)
 */
public class ExpenseExpert implements ReadOnlyExpenseExpert {

    private final UniqueExpenseList expenses;
    private final ExpenseCategoryList categories;
    private Budget budget;
    private final UniquePersonList persons;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        expenses = new UniqueExpenseList();
        categories = new ExpenseCategoryList();
        persons = new UniquePersonList();
        budget = new Budget(new Amount("0"), new Date("1900-01-01"));
    }

    public ExpenseExpert() {}

    /**
     * Creates an ExpenseExpert using the Expenses in the {@code toBeCopied}
     */
    public ExpenseExpert(ReadOnlyExpenseExpert toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the expense list with {@code expenses}.
     * {@code expenses} must not contain duplicate expenses.
     */
    public void setExpenses(List<Expense> expenses) {
        this.expenses.setExpenses(expenses);
    }

    /**
     * Replaces the contents of the Persons list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Resets the existing data of this {@code ExpenseExpert} with {@code newData}.
     */
    public void resetData(ReadOnlyExpenseExpert newData) {
        requireNonNull(newData);

        setExpenses(newData.getExpenseList());
        setExpenseCategories(newData.getExpenseCategoryList());
        setBudget(newData.getBudget());
        setPersons(newData.getPersonList());
    }

    //// expense-level operations

    /**
     * Returns true if a expense with the same identity as {@code expense} exists in the expense expert.
     */
    public boolean hasExpense(Expense expense) {
        requireNonNull(expense);
        return expenses.contains(expense);
    }

    /**
     * Checks the existence of a person.
     * @param person the person whose existence is checked
     * @return boolean for the result
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a expense to the expense expert.
     * The expense must not already exist in the expense expert.
     */
    public void addExpense(Expense e) {
        expenses.add(e);
    }

    //// Person-level operations
    /**
     * Adds a person to the list.
     * @param p the person to be added to the list
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given expense {@code target} in the list with {@code editedExpense}.
     * {@code target} must exist in the expense expert.
     * The expense identity of {@code editedExpense} must not be the same as another existing expense in
     * the expense expert.
     */
    public void setExpense(Expense target, Expense editedExpense) {
        requireNonNull(editedExpense);

        expenses.setExpense(target, editedExpense);
    }

    /**
     * Edits a person given another person
     * @param target the person to be edited
     * @param editedPerson the edited person
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code ExpenseExpert}.
     * {@code key} must exist in the expense expert.
     */
    public void removeExpense(Expense key) {
        expenses.remove(key);
    }

    //// Category-level operations
    /**
     * Replaces the contents of the expense Category list with {@code expenseCategories}.
     * {@code expense categories} must not contain duplicate expense categories.
     */
    public void setExpenseCategories(List<ExpenseCategory> categories) {
        this.categories.setExpenseCategories(categories);
        ExpenseCategory defaultCategory = new ExpenseCategory("General");
        if (!categories.contains(defaultCategory)) {
            this.categories.add(defaultCategory);
        }
    }

    /**
     * Returns true if a expense with the same identity as {@code expense} exists in the expense expert.
     */
    public boolean hasExpenseCategory(ExpenseCategory expenseCategory) {
        requireNonNull(expenseCategory);
        return categories.contains(expenseCategory);
    }

    /**
     * Adds a expense to the expense expert.
     * The expense must not already exist in the expense expert.
     */
    public void addExpenseCategory(ExpenseCategory expenseCategory) {
        categories.add(expenseCategory);
    }

    /**
     * Removes {@code ExpenseCategory key} from this {@code ExpenseExpert}.
     * {@code ExpenseCategory key} must exist in the expense expert.
     */
    public void removeExpenseCategory(ExpenseCategory key) {
        categories.remove(key);
    }

    /**
     * Removes {@code Person key} from this {@code ExpenseExpert}.
     * {@code Person key} must exist in the expense expert.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// budget-level operations

    /**
     * Replaces the current budget in {@code ExpenseExpert} to {@code budget}
     */
    public void setBudget(Budget budget) {
        requireNonNull(budget);
        this.budget = budget;
    }

    //// util methods

    @Override
    public String toString() {
        return expenses.asUnmodifiableObservableList().size() + " expenses";
        // TODO: refine later
    }

    @Override
    public ObservableList<ExpenseCategory> getExpenseCategoryList() {
        return categories.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Expense> getExpenseList() {
        return expenses.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public Budget getBudget() {
        return this.budget;
    }

    @Override
    public boolean hasUndefinedBudget() {
        return this.budget.isUndefined();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExpenseExpert // instanceof handles nulls
                && expenses.equals(((ExpenseExpert) other).expenses)
                && budget.equals(((ExpenseExpert) other).budget))
                && persons.equals((((ExpenseExpert) other).persons));
    }

    @Override
    public int hashCode() {
        // return expenses.hashCode();
        return Objects.hash(expenses, budget);
    }


}
