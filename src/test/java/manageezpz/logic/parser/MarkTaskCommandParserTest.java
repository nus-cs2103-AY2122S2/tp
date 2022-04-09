package manageezpz.logic.parser;

import static manageezpz.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT_BIND;
import static manageezpz.logic.parser.CommandParserTestUtil.assertParseFailure;
import static manageezpz.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static manageezpz.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import manageezpz.logic.commands.MarkTaskCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the MarkTaskCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the MarkTaskCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and therefore
 * should be covered by the ParserUtilTest.
 */
public class MarkTaskCommandParserTest {

    private MarkTaskCommandParser parser = new MarkTaskCommandParser();

    @Test
    public void parse_validCommand_returnsMarkTaskCommand() {
        assertParseSuccess(parser, "1", new MarkTaskCommand(INDEX_FIRST));
    }

    @Test
    public void parse_invalidCommandEmpty_throwsParseException() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT_BIND, MarkTaskCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidCommandContainsSpace_throwsParseException() {
        assertParseFailure(parser, "1 a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT_BIND, MarkTaskCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        assertParseFailure(parser, "a",
                ParserUtil.MESSAGE_INVALID_INDEX + "\n\n" + MarkTaskCommand.MESSAGE_USAGE);
    }
}
