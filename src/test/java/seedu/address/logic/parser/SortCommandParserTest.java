package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SORT_KEY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SORT_ORDER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.SORT_KEY_DESC_DEADLINE;
import static seedu.address.logic.commands.CommandTestUtil.SORT_KEY_DESC_PRIORITY;
import static seedu.address.logic.commands.CommandTestUtil.SORT_ORDER_DESC_ASCENDING;
import static seedu.address.logic.commands.CommandTestUtil.SORT_ORDER_DESC_DESCENDING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SORT_KEY_DEADLINE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SORT_ORDER_DESCENDING;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.sort.SortCommand;
import seedu.address.logic.commands.sort.SortKey;
import seedu.address.logic.commands.sort.SortOrder;

public class SortCommandParserTest {

    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {

        SortKey sortKey = SortKey.DEADLINE;
        SortOrder sortOrder = SortOrder.DESCENDING;

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + SORT_KEY_DESC_DEADLINE
                + SORT_ORDER_DESC_DESCENDING, new SortCommand(sortKey, sortOrder));

        // multiple sort order - last sort order accepted
        assertParseSuccess(parser, SORT_KEY_DESC_PRIORITY + SORT_KEY_DESC_DEADLINE
                + SORT_ORDER_DESC_DESCENDING, new SortCommand(sortKey, sortOrder));

        // multiple sort key - last sort key accepted
        assertParseSuccess(parser, SORT_KEY_DESC_DEADLINE + SORT_ORDER_DESC_ASCENDING
                + SORT_ORDER_DESC_DESCENDING, new SortCommand(sortKey, sortOrder));

        // rearranged sort key and sort order
        assertParseSuccess(parser, SORT_ORDER_DESC_DESCENDING + SORT_KEY_DESC_DEADLINE,
                new SortCommand(sortKey, sortOrder));

    }


    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE);

        // missing sort key prefix
        assertParseFailure(parser, VALID_SORT_KEY_DEADLINE + SORT_ORDER_DESC_DESCENDING,
                expectedMessage);

        // missing sort order prefix
        assertParseFailure(parser, SORT_KEY_DESC_DEADLINE + VALID_SORT_ORDER_DESCENDING,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_SORT_KEY_DEADLINE + VALID_SORT_ORDER_DESCENDING,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid sort key
        assertParseFailure(parser, INVALID_SORT_KEY_DESC + SORT_ORDER_DESC_DESCENDING,
                SortKey.MESSAGE_CONSTRAINTS);

        // invalid sort order
        assertParseFailure(parser, SORT_KEY_DESC_DEADLINE + INVALID_SORT_ORDER_DESC,
                SortOrder.MESSAGE_CONSTRAINTS);

        // two invalid values
        assertParseFailure(parser, INVALID_SORT_KEY_DESC + INVALID_SORT_ORDER_DESC,
                SortKey.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + SORT_KEY_DESC_DEADLINE
                        + SORT_ORDER_DESC_DESCENDING,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }
}
