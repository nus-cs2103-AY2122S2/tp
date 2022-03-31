package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ResizeCommand;

public class ResizeCommandParserTest {

    private ResizeCommandParser parser = new ResizeCommandParser();

    @Test
    public void parse_validResizeResultWindowSize_returnsResizeCommand() {
        assertParseSuccess(parser, "1", new ResizeCommand((double) 1));
        assertParseSuccess(parser, "2", new ResizeCommand(2.0));
        assertParseSuccess(parser, "3", new ResizeCommand(3.0));
        assertParseSuccess(parser, "   1", new ResizeCommand(1.0));
        assertParseSuccess(parser, "1   ", new ResizeCommand(1.0));
    }

    @Test
    public void parse_invalidResizeResultWindowSize_returnsResizeCommand() {
        assertParseFailure(parser, "-1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ResizeCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "one",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ResizeCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1 2",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ResizeCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "test 1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ResizeCommand.MESSAGE_USAGE));
    }
}
