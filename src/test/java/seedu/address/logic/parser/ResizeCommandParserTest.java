package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ResizeCommand;

/**
 * These test cases will cover path variations when the command is input with both numbers and characters.
 * Boundary Value Analysis will also be tested to ensure that the methods are bug free.
 */
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

    @Test
    public void parse_invalidBoundaryWindowSize_returnsResizeCommand() {
        assertParseFailure(parser, "0",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ResizeCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "4",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ResizeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidNumbersWindowSize_returnsResizeCommand() {
        assertParseFailure(parser, "1.5",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ResizeCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "4.001",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ResizeCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "0.5",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ResizeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validBoundaryWindowSize_returnsResizeCommand() {
        assertParseSuccess(parser, "1", new ResizeCommand((double) 1));
        assertParseSuccess(parser, "3", new ResizeCommand((double) 1));
    }
}
