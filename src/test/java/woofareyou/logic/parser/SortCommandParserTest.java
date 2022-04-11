package woofareyou.logic.parser;

import static woofareyou.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import woofareyou.commons.core.Messages;
import woofareyou.logic.commands.SortCommand;


public class SortCommandParserTest {
    private final SortCommandParser parser = new SortCommandParser();

    /**
     * Checks that the parser throws an error when no arguments are provided.
     */
    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    /**
     * Checks that the parser throws an error when an invalid argument is provided.
     */
    @Test
    public void parse_invalidArg_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }
}
