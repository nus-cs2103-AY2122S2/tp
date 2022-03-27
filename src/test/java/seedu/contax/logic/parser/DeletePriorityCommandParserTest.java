package seedu.contax.logic.parser;

import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.contax.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.contax.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.contax.commons.core.index.Index;
import seedu.contax.logic.commands.DeletePriorityCommand;

/**
 * Test the parsing for the {@code DeletePriorityCommandParser}.
 */
public class DeletePriorityCommandParserTest {
    private DeletePriorityCommandParser parser = new DeletePriorityCommandParser();

    @Test
    public void parse_validArgs_returnDeleteCommand() {
        assertParseSuccess(parser, "1", new DeletePriorityCommand(Index.fromOneBased(1)));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeletePriorityCommand.MESSAGE_USAGE));
    }
}
