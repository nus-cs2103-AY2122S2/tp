package seedu.ibook.logic.parser;

import static seedu.ibook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.ibook.logic.commands.CommandTestUtil.EXPIRY_DATE_FULL_A;
import static seedu.ibook.logic.commands.CommandTestUtil.INVALID_EXPIRY_DATE_DESC;
import static seedu.ibook.logic.commands.CommandTestUtil.INVALID_INDEX_A;
import static seedu.ibook.logic.commands.CommandTestUtil.INVALID_INDEX_B;
import static seedu.ibook.logic.commands.CommandTestUtil.INVALID_QUANTITY_DESC;
import static seedu.ibook.logic.commands.CommandTestUtil.QUANTITY_FULL_A;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_INDEX_A;
import static seedu.ibook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.ibook.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.ibook.testutil.TypicalItems.ITEM_A;

import org.junit.jupiter.api.Test;

import seedu.ibook.commons.core.index.Index;
import seedu.ibook.logic.commands.AddItemCommand;
import seedu.ibook.model.item.ExpiryDate;
import seedu.ibook.model.item.Item;
import seedu.ibook.model.item.Quantity;
import seedu.ibook.testutil.ItemBuilder;

public class AddItemCommandParserTest {
    private AddItemCommandParser parser = new AddItemCommandParser();

    private final Index index = Index.fromOneBased(1);

    @Test
    public void parse_allFieldsPresent_success() {
        Item expectedItem = new ItemBuilder(ITEM_A).build();
        AddItemCommand expectedCommand = new AddItemCommand(index, expectedItem);

        assertParseSuccess(parser, VALID_INDEX_A + EXPIRY_DATE_FULL_A + QUANTITY_FULL_A, expectedCommand);
    }

    @Test
    public void parse_anyFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddItemCommand.MESSAGE_USAGE);

        assertParseFailure(parser, VALID_INDEX_A + EXPIRY_DATE_FULL_A, expectedMessage);

        assertParseFailure(parser, QUANTITY_FULL_A + EXPIRY_DATE_FULL_A, expectedMessage);

        assertParseFailure(parser, EXPIRY_DATE_FULL_A, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddItemCommand.MESSAGE_USAGE);

        assertParseFailure(parser,
                VALID_INDEX_A + INVALID_EXPIRY_DATE_DESC + QUANTITY_FULL_A,
                ExpiryDate.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser,
                VALID_INDEX_A + EXPIRY_DATE_FULL_A + INVALID_QUANTITY_DESC,
                Quantity.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser,
                INVALID_INDEX_A + EXPIRY_DATE_FULL_A + QUANTITY_FULL_A,
                ParserUtil.MESSAGE_INVALID_INDEX);

        assertParseFailure(parser,
                INVALID_INDEX_B + QUANTITY_FULL_A + EXPIRY_DATE_FULL_A,
                ParserUtil.MESSAGE_INVALID_INDEX);
    }
}
