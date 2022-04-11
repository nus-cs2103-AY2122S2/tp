package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ExitCommand;

public class ExitCommandParserTest {
    private ExitCommandParser parser = new ExitCommandParser();

    @Test
    public void parse_validArgs_returnsExitCommand() {
        assertParseSuccess(parser, "", new ExitCommand());
    }

    @Test
    public void parse_invalidArgs_fails() {
        assertParseFailure(parser, "abc", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ExitCommand.MESSAGE_USAGE));
    }
}
