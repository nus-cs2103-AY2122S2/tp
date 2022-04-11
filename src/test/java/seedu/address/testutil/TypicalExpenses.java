package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_ANNUAL_SPOTIFY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_BUILD_A_BEAR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_ANNUAL_SPOTIFY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BUILD_A_BEAR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPENSE_CATEGORY_ENTERTAINMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPENSE_DATE_ANNUAL_SPOTIFY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPENSE_DATE_BUILD_A_BEAR;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.ExpenseExpert;
import seedu.address.model.expense.Expense;
import seedu.address.model.expense.ExpenseCategory;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Expense} objects to be used in tests.
 */
public class TypicalExpenses {
    public static final ExpenseCategory ENTERTAINMENT_CATEGORY = new ExpenseCategoryBuilder()
            .withExpenseCategory("Entertainment").build();
    public static final ExpenseCategory MISCELLANEOUS_CATEGORY = new ExpenseCategoryBuilder()
            .withExpenseCategory("Miscellaneous").build();
    public static final ExpenseCategory MEDICAL_CATEGORY = new ExpenseCategoryBuilder()
            .withExpenseCategory("Medical").build();
    public static final ExpenseCategory GROCERIES_CATEGORY = new ExpenseCategoryBuilder()
            .withExpenseCategory("Groceries").build();
    public static final ExpenseCategory TRANSPORT_CATEGORY = new ExpenseCategoryBuilder()
            .withExpenseCategory("Transport").build();
    public static final ExpenseCategory BILLS_CATEGORY = new ExpenseCategoryBuilder()
            .withExpenseCategory("Bills").build();

    public static final Expense ANNUAL_NETFLIX_FEES = new ExpenseBuilder().withDescription("Annual Netflix Fees")
            .withExpenseCategory("Entertainment").withAmount("200").withExpenseDate("2022-03-02").build();
    public static final Expense BASEBALL_LESSON_FEES = new ExpenseBuilder().withDescription("Baseball Lesson Fees")
            .withExpenseCategory("Entertainment").withAmount("100").withExpenseDate("2022-02-02").build();
    public static final Expense CAR_WASH = new ExpenseBuilder().withDescription("Car Wash")
            .withExpenseCategory("Miscellaneous").withAmount("20").withExpenseDate("2022-03-14").build();
    public static final Expense DENTAL = new ExpenseBuilder().withDescription("Dental")
            .withExpenseCategory("Medical").withAmount("200").withExpenseDate("2022-03-18").build();
    public static final Expense ELECTRICAL_APPLIANCES = new ExpenseBuilder().withDescription("Electrical Appliances")
            .withExpenseCategory("Miscellaneous").withAmount("50").withExpenseDate("2022-03-19").build();
    public static final Expense FISHING_APPARATUS = new ExpenseBuilder().withDescription("Fishing Apparatus")
            .withExpenseCategory("Entertainment").withAmount("159.5").withExpenseDate("2022-02-23").build();
    public static final Expense GROCERIES = new ExpenseBuilder().withDescription("Groceries")
            .withExpenseCategory("Groceries").withAmount("69.85").withExpenseDate("2022-01-12").build();
    public static final Expense HOUSING_RENT = new ExpenseBuilder().withDescription("Grab")
            .withExpenseCategory("Transport").withAmount("500").withExpenseDate("2022-02-25").build();
    public static final Expense INTERNET_BILLS = new ExpenseBuilder().withDescription("Internet Bills")
            .withExpenseCategory("Bills").withAmount("69").withExpenseDate("2022-01-13").build();

    public static final String DATE_WITH_NO_MATCH = "2055-03-02";
    public static final String DATE_WITH_ONE_MATCH = "2022-01-12";
    public static final String MONTH_WITH_NO_MATCH = "2055-03";
    public static final String MONTH_WITH_ONE_MATCH = "2022-01";
    public static final String MONTH_WITH_TWO_MATCHES = "2022-02";

    public static final String CATEGORY_WITH_THREE_MATCHES = "Entertainment";
    public static final String CATEGORY_WITH_NO_MATCH = "Food";

    public static final Expense ANNUAL_SPOTIFY = new ExpenseBuilder().withDescription(VALID_DESCRIPTION_ANNUAL_SPOTIFY)
            .withExpenseCategory(VALID_EXPENSE_CATEGORY_ENTERTAINMENT).withAmount(VALID_AMOUNT_ANNUAL_SPOTIFY)
            .withExpenseDate(VALID_EXPENSE_DATE_ANNUAL_SPOTIFY).build();
    public static final Expense BUILD_A_BEAR = new ExpenseBuilder().withDescription(VALID_DESCRIPTION_BUILD_A_BEAR)
            .withExpenseCategory(VALID_EXPENSE_CATEGORY_ENTERTAINMENT)
            .withAmount(VALID_AMOUNT_BUILD_A_BEAR)
            .withExpenseDate(VALID_EXPENSE_DATE_BUILD_A_BEAR).build();

    public static final Person BOB = new PersonBuilder().withPersonName("Bob").withPersonAmount("40").build();
    public static final Person ALEX = new PersonBuilder().withPersonName("Alex").withPersonAmount("50").build();
    public static final String KEYWORD_MATCHING_RENT = "Rent"; // A keyword that matches Rent

    private TypicalExpenses() {} // prevents instantiation

    /**
     * Returns an {@code Expense Expert} with all the typical expenses.
     */
    public static ExpenseExpert getTypicalExpenseExpert() {
        ExpenseExpert ee = new ExpenseExpert();
        for (ExpenseCategory expenseCategory : getTypicalExpenseCategories()) {
            ee.addExpenseCategory(expenseCategory);
        }
        for (Expense expense : getTypicalExpenses()) {
            ee.addExpense(expense);
        }
        for (Person person : getTypicalPersons()) {
            ee.addPerson(person);
        }

        ee.setBudget(new BudgetBuilder().withBudgetAmount("100").withBudgetDate("2020-01-03").build());
        return ee;
    }

    public static List<Expense> getTypicalExpenses() {
        return new ArrayList<>(Arrays.asList(ANNUAL_NETFLIX_FEES, BASEBALL_LESSON_FEES, CAR_WASH, DENTAL,
                ELECTRICAL_APPLIANCES, FISHING_APPARATUS, GROCERIES));
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(BOB, ALEX));
    }

    public static List<ExpenseCategory> getTypicalExpenseCategories() {
        return new ArrayList<>(Arrays.asList(ENTERTAINMENT_CATEGORY, MISCELLANEOUS_CATEGORY, MEDICAL_CATEGORY,
                GROCERIES_CATEGORY, TRANSPORT_CATEGORY, BILLS_CATEGORY));
    }
}
