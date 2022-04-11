package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddToClipboardCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.predicates.NameExistsPredicate;

/**
 * Unit and integration tests for the AddToClipboardCommandParser class.
 */
class AddToClipboardCommandParserTest {
    private AddToClipboardCommandParser parser = new AddToClipboardCommandParser();

    /**
     * Tests that if both an index and name is specified, the index will be used.
     */
    @Test
    void parseIndexAndName() throws ParseException {
        assertParseSuccess(parser, "1" + NAME_DESC_AMY,
                new AddToClipboardCommand(INDEX_FIRST_PERSON));
    }

    /**
     * Tests that indexes can be parsed correctly.
     */
    @Test
    void parseIndex() throws ParseException {
        assertParseSuccess(parser, "1",
                new AddToClipboardCommand(INDEX_FIRST_PERSON));
    }

    /**
     * Tests that names can be parsed correctly.
     */
    @Test
    void parseName() throws ParseException {
        assertParseSuccess(parser, NAME_DESC_AMY,
                new AddToClipboardCommand(new NameExistsPredicate(ParserUtil.parseName(VALID_NAME_AMY))));
    }

    /**
     * Tests that invalid values will not be parsed successfully.
     */
    @Test
    void parseInvalidValues() {
        //no values
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddToClipboardCommand.MESSAGE_USAGE));

        //invalid index
        assertParseFailure(parser, "0.1", ParserUtil.MESSAGE_INVALID_INDEX);

        //invalid preamble
        assertParseFailure(parser, "p/62353535", ParserUtil.MESSAGE_INVALID_INDEX);
    }
}
