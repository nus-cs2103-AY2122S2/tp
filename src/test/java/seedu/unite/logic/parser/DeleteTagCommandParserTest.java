package seedu.unite.logic.parser;

import static seedu.unite.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.unite.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.unite.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.unite.testutil.TypicalIndexes.INDEX_FIRST_TAG;

import org.junit.jupiter.api.Test;

import seedu.unite.logic.commands.DeleteTagCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteTagCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteTagCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteTagCommandParserTest {

    private final DeleteTagCommandParser parser = new DeleteTagCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeleteTagCommand(INDEX_FIRST_TAG));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteTagCommand.MESSAGE_USAGE));
    }
}
