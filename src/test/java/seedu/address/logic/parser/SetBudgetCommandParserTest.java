package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BUDGET_AMOUNT_ALPHA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BUDGET_AMOUNT_BETA;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalBudgets.ALPHA_BUDGET;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SetBudgetCommand;
import seedu.address.model.expense.Amount;
import seedu.address.model.expense.Budget;
import seedu.address.testutil.BudgetBuilder;

public class SetBudgetCommandParserTest {
    private SetBudgetCommandParser parser = new SetBudgetCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Budget expectedBudget = new BudgetBuilder(ALPHA_BUDGET)
                .withBudgetDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build();

        assertParseSuccess(parser, " a/" + VALID_BUDGET_AMOUNT_ALPHA,
                new SetBudgetCommand(expectedBudget));


        // multiple amounts - last amount accepted
        assertParseSuccess(parser, " a/" + VALID_BUDGET_AMOUNT_BETA
                        + " a/" + VALID_BUDGET_AMOUNT_ALPHA,
                new SetBudgetCommand(expectedBudget));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetBudgetCommand.MESSAGE_USAGE);

        // missing amount prefix
        assertParseFailure(parser, "",
                expectedMessage);

        // amount too large to be handled
        assertParseFailure(parser, " a/ 100000.00",
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid amount
        assertParseFailure(parser, " a/ %213!",
                Amount.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY
                + " a/" + VALID_BUDGET_AMOUNT_ALPHA,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetBudgetCommand.MESSAGE_USAGE));
    }
}

