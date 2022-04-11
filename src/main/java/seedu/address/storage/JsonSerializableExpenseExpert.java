package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ExpenseExpert;
import seedu.address.model.ReadOnlyExpenseExpert;
import seedu.address.model.expense.Expense;
import seedu.address.model.expense.ExpenseCategory;
import seedu.address.model.person.Person;

/**
 * An Immutable ExpenseExpert that is serializable to JSON format.
 */
@JsonRootName(value = "expenseexpert")
class JsonSerializableExpenseExpert {

    public static final String MESSAGE_DUPLICATE_EXPENSE = "Expenses list contains duplicate expense(s).";
    public static final String MESSAGE_DUPLICATE_EXPENSE_CATEGORY =
            "Expenses Category list contains duplicate expense category/categories.";
    public static final String MESSAGE_INVALID_EXPENSE_CATEGORY =
            "Expenses Category list contains expenses with invalid expense category/categories.";
    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";


    private final List<JsonAdaptedExpense> expenses = new ArrayList<>();
    private JsonAdaptedBudget budget;
    private final List<JsonAdaptedExpenseCategory> expenseCategories = new ArrayList<>();
    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableExpenseExpert} with the given expenses.
     */
    @JsonCreator
    public JsonSerializableExpenseExpert(@JsonProperty("expenses") List<JsonAdaptedExpense> expenses,
                                         @JsonProperty("persons") List <JsonAdaptedPerson> persons) {
        this.expenses.addAll(expenses);
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyExpenseExpert} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableExpenseExpert}.
     */
    public JsonSerializableExpenseExpert(ReadOnlyExpenseExpert source) {
        expenseCategories.addAll(source.getExpenseCategoryList().stream().map(JsonAdaptedExpenseCategory::new)
                .collect(Collectors.toList()));
        expenses.addAll(source.getExpenseList().stream().map(JsonAdaptedExpense::new).collect(Collectors.toList()));
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        this.budget = new JsonAdaptedBudget(source.getBudget());
    }

    /**
     * Converts this Expense Expert into the model's {@code ExpenseExpert} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ExpenseExpert toModelType() throws IllegalValueException {
        ExpenseExpert expenseExpert = new ExpenseExpert();
        for (JsonAdaptedExpenseCategory jsonAdaptedExpenseCategory : expenseCategories) {
            ExpenseCategory expenseCategory = jsonAdaptedExpenseCategory.toModelType();
            if (expenseExpert.hasExpenseCategory(expenseCategory)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_EXPENSE_CATEGORY);
            }
            expenseExpert.addExpenseCategory(expenseCategory);
        }
        for (JsonAdaptedExpense jsonAdaptedExpense : expenses) {
            Expense expense = jsonAdaptedExpense.toModelType();
            if (expenseExpert.hasExpense(expense)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_EXPENSE);
            }

            if (!expenseExpert.hasExpenseCategory(expense.getExpenseCategory())) {
                throw new IllegalValueException(MESSAGE_INVALID_EXPENSE_CATEGORY);
            }
            expenseExpert.addExpense(expense);
        }
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (expenseExpert.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            expenseExpert.addPerson(person);
        }
        expenseExpert.setBudget(this.budget.toModelType());

        return expenseExpert;
    }

}
