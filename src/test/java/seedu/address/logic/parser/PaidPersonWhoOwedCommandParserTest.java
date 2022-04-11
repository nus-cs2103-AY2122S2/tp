package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EXPENSE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.PaidPersonWhoOwedCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the PaidPersonWhoOwedCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the PaidPersonWhoOwedCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class PaidPersonWhoOwedCommandParserTest {

    private PaidPersonWhoOwedCommandParser parser = new PaidPersonWhoOwedCommandParser();

    /**
     * Test method for checking the functionality of PaidPersonWhoOwedCommand
     */
    @Test
    public void parse_validArgs_returnsPaidPersonWhoOwedCommand() {
        assertParseSuccess(parser, "1", new PaidPersonWhoOwedCommand(INDEX_FIRST_EXPENSE));
    }

    /**
     * Test method to cross verify the passing of invalid input to the PaidPersonWhoOwedCommandParser.
     */
    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                PaidPersonWhoOwedCommand.MESSAGE_USAGE));
    }
}
