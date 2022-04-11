package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_EXPENSES_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalExpenses.BASEBALL_LESSON_FEES;
import static seedu.address.testutil.TypicalExpenses.CATEGORY_WITH_NO_MATCH;
import static seedu.address.testutil.TypicalExpenses.CATEGORY_WITH_THREE_MATCHES;
import static seedu.address.testutil.TypicalExpenses.DATE_WITH_NO_MATCH;
import static seedu.address.testutil.TypicalExpenses.DATE_WITH_ONE_MATCH;
import static seedu.address.testutil.TypicalExpenses.FISHING_APPARATUS;
import static seedu.address.testutil.TypicalExpenses.GROCERIES;
import static seedu.address.testutil.TypicalExpenses.MONTH_WITH_NO_MATCH;
import static seedu.address.testutil.TypicalExpenses.MONTH_WITH_ONE_MATCH;
import static seedu.address.testutil.TypicalExpenses.MONTH_WITH_TWO_MATCHES;
import static seedu.address.testutil.TypicalExpenses.getTypicalExpenseExpert;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.expense.ExpenseCategoryIsParsedCategoryPredicate;
import seedu.address.model.expense.ExpenseDateIsParsedDatePredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FilterCommand}.
 */
public class FilterCommandTest {
    private Model model = new ModelManager(getTypicalExpenseExpert(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalExpenseExpert(), new UserPrefs());

    @Test
    public void equals() {

        //Testing for date
        ExpenseDateIsParsedDatePredicate firstDatePredicate =
                new ExpenseDateIsParsedDatePredicate(DATE_WITH_ONE_MATCH);
        ExpenseDateIsParsedDatePredicate secondDatePredicate =
                new ExpenseDateIsParsedDatePredicate(DATE_WITH_NO_MATCH);
        ExpenseDateIsParsedDatePredicate thirdDatePredicate =
                new ExpenseDateIsParsedDatePredicate(MONTH_WITH_NO_MATCH);
        ExpenseDateIsParsedDatePredicate forthDatePredicate =
                new ExpenseDateIsParsedDatePredicate(MONTH_WITH_ONE_MATCH);

        FilterCommand filterFirstCommand = new FilterCommand(firstDatePredicate, null);
        FilterCommand filterSecondCommand = new FilterCommand(secondDatePredicate, null);
        FilterCommand filterThirdCommand = new FilterCommand(thirdDatePredicate, null);
        FilterCommand filterForthCommand = new FilterCommand(forthDatePredicate, null);

        // same object -> returns true
        assertTrue(filterFirstCommand.equals(filterFirstCommand));

        // same values -> returns true
        FilterCommand filterFirstCommandCopy = new FilterCommand(firstDatePredicate, null);
        assertTrue(filterFirstCommand.equals(filterFirstCommandCopy));

        FilterCommand filterThirdCommandCopy = new FilterCommand(thirdDatePredicate, null);
        assertTrue(filterThirdCommand.equals(filterThirdCommandCopy));

        // different types -> returns false
        assertFalse(filterFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterFirstCommand.equals(null));

        // different expense -> returns false
        assertFalse(filterFirstCommand.equals(filterSecondCommand));
        assertFalse(filterFirstCommand.equals(filterThirdCommand));
        assertFalse(filterThirdCommand.equals(filterForthCommand));


        //Testing for category
        ExpenseCategoryIsParsedCategoryPredicate firstCategoryPredicate =
                new ExpenseCategoryIsParsedCategoryPredicate(CATEGORY_WITH_NO_MATCH);
        ExpenseCategoryIsParsedCategoryPredicate secondCategoryPredicate =
                new ExpenseCategoryIsParsedCategoryPredicate(CATEGORY_WITH_THREE_MATCHES);

        FilterCommand filterFifthCommand = new FilterCommand(null, firstCategoryPredicate);
        FilterCommand filterSixthCommand = new FilterCommand(null, secondCategoryPredicate);

        // same object -> returns true
        assertTrue(filterFifthCommand.equals(filterFifthCommand));

        // same values -> returns true
        FilterCommand filterFifthCommandCopy = new FilterCommand(null, firstCategoryPredicate);
        assertTrue(filterFifthCommand.equals(filterFifthCommandCopy));

        // different types -> returns false
        assertFalse(filterFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterFirstCommand.equals(null));

        // different expense -> returns false
        assertFalse(filterFirstCommand.equals(filterSixthCommand));
    }

    @Test
    public void execute_dateWithNoMatch_noExpenseFound() {
        String expectedMessage = String.format(MESSAGE_EXPENSES_LISTED_OVERVIEW, 0);
        ExpenseDateIsParsedDatePredicate predicate = new ExpenseDateIsParsedDatePredicate(DATE_WITH_NO_MATCH);
        FilterCommand command = new FilterCommand(predicate, null);
        expectedModel.updateFilteredExpenseList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredExpenseList());
    }

    @Test
    public void execute_monthWithNoMatch_noExpenseFound() {
        String expectedMessage = String.format(MESSAGE_EXPENSES_LISTED_OVERVIEW, 0);
        ExpenseDateIsParsedDatePredicate predicate = new ExpenseDateIsParsedDatePredicate(MONTH_WITH_NO_MATCH);
        FilterCommand command = new FilterCommand(predicate, null);
        expectedModel.updateFilteredExpenseList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredExpenseList());
    }

    @Test
    public void execute_dateWithOneMatch_oneExpenseFound() {
        String expectedMessage = String.format(MESSAGE_EXPENSES_LISTED_OVERVIEW, 1);
        ExpenseDateIsParsedDatePredicate predicate = new ExpenseDateIsParsedDatePredicate(DATE_WITH_ONE_MATCH);
        FilterCommand command = new FilterCommand(predicate, null);
        expectedModel.updateFilteredExpenseList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(GROCERIES), model.getFilteredExpenseList());
    }

    @Test
    public void execute_monthWithMatches_multipleExpensesFound() {
        String expectedMessage = String.format(MESSAGE_EXPENSES_LISTED_OVERVIEW, 1);
        ExpenseDateIsParsedDatePredicate predicate = new ExpenseDateIsParsedDatePredicate(MONTH_WITH_ONE_MATCH);
        FilterCommand command = new FilterCommand(predicate, null);
        expectedModel.updateFilteredExpenseList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(GROCERIES), model.getFilteredExpenseList());

        expectedMessage = String.format(MESSAGE_EXPENSES_LISTED_OVERVIEW, 2);
        predicate = new ExpenseDateIsParsedDatePredicate(MONTH_WITH_TWO_MATCHES);
        command = new FilterCommand(predicate, null);
        expectedModel.updateFilteredExpenseList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BASEBALL_LESSON_FEES, FISHING_APPARATUS), model.getFilteredExpenseList());
    }
}
