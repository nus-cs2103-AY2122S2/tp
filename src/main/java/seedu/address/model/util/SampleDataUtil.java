package seedu.address.model.util;

import seedu.address.model.ExpenseExpert;
import seedu.address.model.ReadOnlyExpenseExpert;
import seedu.address.model.expense.Expense;
import seedu.address.model.expense.ExpenseCategory;
import seedu.address.model.person.Person;



/**
 * Contains utility methods for populating {@code ExpenseExpert} with sample data.
 */
public class SampleDataUtil {
    public static Expense[] getSampleExpenses() {
        return new Expense[] {
        };
    }

    public static Person[] getSamplePersons() {
        return new Person[] {};
    }

    public static ExpenseCategory[] getSampleCategories() {
        return new ExpenseCategory[] {
            new ExpenseCategory("General"),
        };
    }

    public static ReadOnlyExpenseExpert getSampleExpenseExpert() {
        ExpenseExpert sampleEe = new ExpenseExpert();
        for (ExpenseCategory sampleCategories : getSampleCategories()) {
            sampleEe.addExpenseCategory(sampleCategories);
        }

        for (Expense sampleExpense : getSampleExpenses()) {
            sampleEe.addExpense(sampleExpense);
        }
        for (Person samplePerson : getSamplePersons()) {
            sampleEe.addPerson(samplePerson);
        }
        return sampleEe;
    }
}
