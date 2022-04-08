package woofareyou.logic.parser;

import static woofareyou.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static woofareyou.commons.core.Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX;
import static woofareyou.logic.parser.CommandParserTestUtil.assertParseFailure;
import static woofareyou.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static woofareyou.testutil.TypicalIndexes.INDEX_FIRST_PET;

import org.junit.jupiter.api.Test;

import woofareyou.logic.commands.DeleteCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private static final String NEG_INTEGER_MAX = String.valueOf(-(Integer.MAX_VALUE + 1));
    private static final String POS_INTEGER_MAX = String.valueOf(Integer.MAX_VALUE + 1);
    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeleteCommand(INDEX_FIRST_PET));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_indexOutOfBoundsIntegerOverflow_throwsParseException() {
        // large positive number
        assertParseFailure(parser, POS_INTEGER_MAX, MESSAGE_INVALID_PET_DISPLAYED_INDEX);
        // large negative number
        assertParseFailure(parser, NEG_INTEGER_MAX, MESSAGE_INVALID_PET_DISPLAYED_INDEX);
    }
}
