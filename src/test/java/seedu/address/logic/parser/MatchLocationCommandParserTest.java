package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MatchLocationCommand;

class MatchLocationCommandParserTest {

    public final MatchLocationCommandParser parser = new MatchLocationCommandParser();

    @Test
    public void parse_validArgs_returnsMatchBuyerCommand() {
        assertParseSuccess(parser, "1", new MatchLocationCommand(INDEX_FIRST_CLIENT));
        assertParseSuccess(parser, "2147483647", new MatchLocationCommand(Index.fromOneBased(2147483647)));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            MatchLocationCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "%", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            MatchLocationCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1b", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            MatchLocationCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "-1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            MatchLocationCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "0", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            MatchLocationCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "/", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            MatchLocationCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "2147483648", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            MatchLocationCommand.MESSAGE_USAGE));
    }

}
