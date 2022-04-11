package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROCEDURE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.MarkCommand;

public class MarkCommandParserTest {
    private final MarkCommandParser parser = new MarkCommandParser();

    @Test
    public void parse_validArgs_returnsMarkCommand() {
        assertParseSuccess(parser, "1 1", new MarkCommand(INDEX_FIRST_CLIENT, INDEX_FIRST_PROCEDURE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "b a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                MarkCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                MarkCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                MarkCommand.MESSAGE_USAGE));
    }
}
