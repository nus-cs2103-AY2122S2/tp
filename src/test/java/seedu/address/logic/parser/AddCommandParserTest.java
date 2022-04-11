package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.AMOUNT_DESC_ANNUAL_SPOTIFY;
import static seedu.address.logic.commands.CommandTestUtil.AMOUNT_DESC_BUILD_A_BEAR;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_ANNUAL_SPOTIFY;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_BUILD_A_BEAR;
import static seedu.address.logic.commands.CommandTestUtil.EXPENSE_CATEGORY_DESC_ENTERTAINMENT;
import static seedu.address.logic.commands.CommandTestUtil.EXPENSE_DATE_DESC_BUILD_A_BEAR;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_AMOUNT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EXPENSE_CATEGORY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EXPENSE_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalExpenses.BUILD_A_BEAR;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.expense.Amount;
import seedu.address.model.expense.Date;
import seedu.address.model.expense.Description;
import seedu.address.model.expense.Expense;
import seedu.address.model.expense.ExpenseCategory;
import seedu.address.testutil.ExpenseBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Expense expectedExpense = new ExpenseBuilder(BUILD_A_BEAR).build();

        assertParseSuccess(parser, DESCRIPTION_DESC_BUILD_A_BEAR
                        + EXPENSE_CATEGORY_DESC_ENTERTAINMENT + AMOUNT_DESC_BUILD_A_BEAR
                        + EXPENSE_DATE_DESC_BUILD_A_BEAR,
                new AddCommand(expectedExpense));


        // multiple descriptions - last description accepted
        assertParseSuccess(parser, DESCRIPTION_DESC_ANNUAL_SPOTIFY + DESCRIPTION_DESC_BUILD_A_BEAR
                        + EXPENSE_CATEGORY_DESC_ENTERTAINMENT + AMOUNT_DESC_BUILD_A_BEAR
                        + EXPENSE_DATE_DESC_BUILD_A_BEAR,
                new AddCommand(expectedExpense));

        // multiple amounts - last amount accepted
        assertParseSuccess(parser, DESCRIPTION_DESC_BUILD_A_BEAR + EXPENSE_CATEGORY_DESC_ENTERTAINMENT
                        + AMOUNT_DESC_ANNUAL_SPOTIFY + AMOUNT_DESC_BUILD_A_BEAR + EXPENSE_DATE_DESC_BUILD_A_BEAR,
                new AddCommand(expectedExpense));
    }

    @Test
    public void parse_optionalFieldMissing_success() {
        Expense expectedExpense = new ExpenseBuilder(BUILD_A_BEAR).withExpenseCategory("General").build();
        // missing expenseCategory prefix
        assertParseSuccess(parser, DESCRIPTION_DESC_BUILD_A_BEAR + AMOUNT_DESC_BUILD_A_BEAR
                        + EXPENSE_DATE_DESC_BUILD_A_BEAR, new AddCommand(expectedExpense));
    }


    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing amount prefix
        assertParseFailure(parser, DESCRIPTION_DESC_BUILD_A_BEAR
                        + EXPENSE_CATEGORY_DESC_ENTERTAINMENT + EXPENSE_DATE_DESC_BUILD_A_BEAR,
                expectedMessage);

        // missing description prefix
        assertParseFailure(parser, EXPENSE_CATEGORY_DESC_ENTERTAINMENT + AMOUNT_DESC_BUILD_A_BEAR
                        + EXPENSE_DATE_DESC_BUILD_A_BEAR, expectedMessage);

        // missing expenseDate prefix
        assertParseFailure(parser, DESCRIPTION_DESC_BUILD_A_BEAR
                        + EXPENSE_CATEGORY_DESC_ENTERTAINMENT + AMOUNT_DESC_BUILD_A_BEAR,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, "",
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid amount
        assertParseFailure(parser, INVALID_AMOUNT_DESC + DESCRIPTION_DESC_BUILD_A_BEAR
                + EXPENSE_CATEGORY_DESC_ENTERTAINMENT + EXPENSE_DATE_DESC_BUILD_A_BEAR, Amount.MESSAGE_CONSTRAINTS);

        // invalid expenseDate
        assertParseFailure(parser, AMOUNT_DESC_BUILD_A_BEAR + DESCRIPTION_DESC_BUILD_A_BEAR
                + EXPENSE_CATEGORY_DESC_ENTERTAINMENT + INVALID_EXPENSE_DATE_DESC, Date.MESSAGE_CONSTRAINTS);

        // invalid description
        assertParseFailure(parser, AMOUNT_DESC_BUILD_A_BEAR + INVALID_DESCRIPTION_DESC
                + EXPENSE_CATEGORY_DESC_ENTERTAINMENT + EXPENSE_DATE_DESC_BUILD_A_BEAR,
                Description.MESSAGE_CONSTRAINTS);

        // invalid expenseCategory
        assertParseFailure(parser, AMOUNT_DESC_BUILD_A_BEAR + DESCRIPTION_DESC_BUILD_A_BEAR
                + INVALID_EXPENSE_CATEGORY_DESC + EXPENSE_DATE_DESC_BUILD_A_BEAR,
                ExpenseCategory.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + DESCRIPTION_DESC_BUILD_A_BEAR
                        + EXPENSE_CATEGORY_DESC_ENTERTAINMENT + AMOUNT_DESC_BUILD_A_BEAR
                        + EXPENSE_DATE_DESC_BUILD_A_BEAR,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
