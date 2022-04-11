package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.HelpCommand;

public class HelpCommandParserTest {
    private HelpCommandParser parser = new HelpCommandParser();

    @Test
    public void parse_validArgs_returnsHelpCommand() {
        assertParseSuccess(parser, "", new HelpCommand());
    }

    @Test
    public void parse_invalidArgs_fails() {
        assertParseFailure(parser, "abc", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                HelpCommand.MESSAGE_USAGE));
    }
}
