package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseNoFailure;
import static seedu.address.testutil.PersonUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.testutil.PersonUtil.PREAMBLE_WHITESPACE;
import static seedu.address.testutil.TransactionUtil.AMOUNT_ONE;
import static seedu.address.testutil.TransactionUtil.AMOUNT_TWO;
import static seedu.address.testutil.TransactionUtil.DUE_DATE_ONE;
import static seedu.address.testutil.TransactionUtil.DUE_DATE_TWO;
import static seedu.address.testutil.TransactionUtil.FLAG_PAID;
import static seedu.address.testutil.TransactionUtil.INVALID_AMOUNT;
import static seedu.address.testutil.TransactionUtil.INVALID_DUE_DATE;
import static seedu.address.testutil.TransactionUtil.INVALID_INDEX_STRING;
import static seedu.address.testutil.TransactionUtil.INVALID_TRANSACTION_DATE;
import static seedu.address.testutil.TransactionUtil.NOTE_ONE;
import static seedu.address.testutil.TransactionUtil.NOTE_TWO;
import static seedu.address.testutil.TransactionUtil.TRANSACTION_DATE_ONE;
import static seedu.address.testutil.TransactionUtil.TRANSACTION_DATE_TWO;
import static seedu.address.testutil.TransactionUtil.VALID_INDEX_STRING;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddTransactionCommand;
import seedu.address.model.transaction.Amount;
import seedu.address.model.transaction.DueDate;
import seedu.address.model.transaction.TransactionDate;

public class AddTransactionCommandParserTest {
    private static AddTransactionParser parser;

    @BeforeAll
    private static void init() {
        parser = new AddTransactionParser();
    }

    @Test
    public void parse_allFieldsPresent_success() {
        // Could not test the output as the AddTransactionCommand have a functional interface as
        // an attribute.

        // whitespace only preamble
        assertParseNoFailure(parser, PREAMBLE_WHITESPACE + VALID_INDEX_STRING
                        + AMOUNT_ONE + TRANSACTION_DATE_ONE + DUE_DATE_ONE + NOTE_ONE + FLAG_PAID);

        // multiple amount
        assertParseNoFailure(parser,
                PREAMBLE_WHITESPACE + VALID_INDEX_STRING
                        + AMOUNT_ONE + AMOUNT_TWO + TRANSACTION_DATE_ONE + DUE_DATE_ONE + NOTE_ONE + FLAG_PAID);

        // multiple transaction date
        assertParseNoFailure(parser,
                PREAMBLE_WHITESPACE + VALID_INDEX_STRING
                        + AMOUNT_ONE + TRANSACTION_DATE_ONE + TRANSACTION_DATE_TWO
                        + DUE_DATE_ONE + NOTE_ONE + FLAG_PAID);

        // multiple due date
        assertParseNoFailure(parser,
                PREAMBLE_WHITESPACE + VALID_INDEX_STRING
                        + AMOUNT_ONE + TRANSACTION_DATE_ONE + DUE_DATE_ONE + DUE_DATE_TWO + NOTE_ONE + FLAG_PAID);


        // multiple note
        assertParseNoFailure(parser,
                PREAMBLE_WHITESPACE + VALID_INDEX_STRING
                        + AMOUNT_ONE + AMOUNT_TWO + TRANSACTION_DATE_ONE + DUE_DATE_ONE
                        + NOTE_ONE + NOTE_TWO + FLAG_PAID);

        // multiple amount, the first one is invalid
        assertParseNoFailure(parser,
                PREAMBLE_WHITESPACE + VALID_INDEX_STRING
                        + INVALID_AMOUNT + AMOUNT_TWO + TRANSACTION_DATE_ONE + DUE_DATE_ONE
                        + NOTE_ONE + NOTE_TWO + FLAG_PAID);

        // multiple transaction date, the first one is invalid
        assertParseNoFailure(parser,
                PREAMBLE_WHITESPACE + VALID_INDEX_STRING
                        + AMOUNT_TWO + INVALID_TRANSACTION_DATE + TRANSACTION_DATE_ONE + DUE_DATE_ONE
                        + NOTE_ONE + NOTE_TWO + FLAG_PAID);

        // multiple due date, the first one is invalid
        assertParseNoFailure(parser,
                PREAMBLE_WHITESPACE + VALID_INDEX_STRING
                        + AMOUNT_TWO + TRANSACTION_DATE_ONE + INVALID_DUE_DATE + DUE_DATE_ONE
                        + NOTE_ONE + NOTE_TWO + FLAG_PAID);
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // no due date
        assertParseNoFailure(parser, PREAMBLE_WHITESPACE + VALID_INDEX_STRING
                        + AMOUNT_ONE + AMOUNT_TWO + TRANSACTION_DATE_ONE
                        + NOTE_ONE + FLAG_PAID);

        // no note
        assertParseNoFailure(parser, PREAMBLE_WHITESPACE + VALID_INDEX_STRING
                        + AMOUNT_ONE + AMOUNT_TWO + TRANSACTION_DATE_ONE + DUE_DATE_ONE
                        + FLAG_PAID);

        // without flag
        assertParseNoFailure(parser, PREAMBLE_WHITESPACE + VALID_INDEX_STRING
                        + AMOUNT_ONE + AMOUNT_TWO + TRANSACTION_DATE_ONE + DUE_DATE_ONE
                        + NOTE_ONE);
    }

    @Test
    public void parse_requiredFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTransactionCommand.MESSAGE_USAGE);

        // missing index
        assertParseFailure(parser, PREAMBLE_WHITESPACE
                        + AMOUNT_ONE + TRANSACTION_DATE_ONE + DUE_DATE_ONE
                        + NOTE_ONE + FLAG_PAID, expectedMessage);

        // missing transaction date
        assertParseFailure(parser, PREAMBLE_WHITESPACE + VALID_INDEX_STRING
                + AMOUNT_ONE + DUE_DATE_ONE
                + NOTE_ONE + FLAG_PAID, expectedMessage);

        // missing amount
        assertParseFailure(parser, PREAMBLE_WHITESPACE + VALID_INDEX_STRING
                + TRANSACTION_DATE_ONE + DUE_DATE_ONE
                + NOTE_ONE + FLAG_PAID, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTransactionCommand.MESSAGE_USAGE);

        // invalid index
        assertParseFailure(parser, PREAMBLE_WHITESPACE + INVALID_INDEX_STRING
                + AMOUNT_ONE + TRANSACTION_DATE_ONE + DUE_DATE_ONE
                + NOTE_ONE + FLAG_PAID, expectedMessage);

        // two index
        assertParseFailure(parser, VALID_INDEX_STRING + VALID_INDEX_STRING
                + AMOUNT_ONE + TRANSACTION_DATE_ONE + DUE_DATE_ONE
                + NOTE_ONE + FLAG_PAID, expectedMessage);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + VALID_INDEX_STRING
                + AMOUNT_ONE + TRANSACTION_DATE_ONE + DUE_DATE_ONE
                + NOTE_ONE + FLAG_PAID, expectedMessage);

        // invalid amount
        assertParseFailure(parser, VALID_INDEX_STRING
                + INVALID_AMOUNT + TRANSACTION_DATE_ONE + DUE_DATE_ONE
                + NOTE_ONE + FLAG_PAID, Amount.MESSAGE_CONSTRAINT);

        // invalid transaction date
        assertParseFailure(parser, VALID_INDEX_STRING
                + AMOUNT_ONE + INVALID_TRANSACTION_DATE + DUE_DATE_ONE
                + NOTE_ONE + FLAG_PAID, TransactionDate.MESSAGE_CONSTRAINT);

        // invalid due date
        assertParseFailure(parser, VALID_INDEX_STRING
                + AMOUNT_ONE + TRANSACTION_DATE_ONE + INVALID_DUE_DATE
                + NOTE_ONE + FLAG_PAID, DueDate.MESSAGE_CONSTRAINT);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, VALID_INDEX_STRING
                + INVALID_AMOUNT + INVALID_AMOUNT + TRANSACTION_DATE_ONE + DUE_DATE_TWO
                + NOTE_ONE + FLAG_PAID, Amount.MESSAGE_CONSTRAINT);
    }
}
