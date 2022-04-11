package seedu.address.logic.parser.module;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.typical.TypicalIndexes.INDEX_FIRST_MODULE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.module.DeleteModuleCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteModuleCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteModuleCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteModuleCommandParserTest {
    private DeleteModuleCommandParser parser = new DeleteModuleCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteModuleCommand() {
        assertParseSuccess(parser, "1", new DeleteModuleCommand(INDEX_FIRST_MODULE, false));
    }

    @Test
    public void parse_validArgsForced_returnsDeleteModuleCommand() {
        assertParseSuccess(parser, "1 f/", new DeleteModuleCommand(INDEX_FIRST_MODULE, true));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteModuleCommand.MESSAGE_USAGE));
    }
}
