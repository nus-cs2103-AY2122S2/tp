package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.FocusCommand;


public class FocusCommandParserTest {

    private FocusCommandParser parser = new FocusCommandParser();

    @Test
    public void parse_validArgs_returnsFocusCommand() {
        assertParseSuccess(parser, "2", new FocusCommand(Index.fromZeroBased(1)));
        assertParseSuccess(parser, "1", new FocusCommand(Index.fromZeroBased(0)));
        assertParseSuccess(parser, "10", new FocusCommand(Index.fromZeroBased(9)));
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FocusCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "-1", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FocusCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "one", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FocusCommand.MESSAGE_USAGE));
    }
}
