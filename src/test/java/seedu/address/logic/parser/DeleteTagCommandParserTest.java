package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeleteTagCommandParserTest {
    private DeleteTagCommandParser parser = new DeleteTagCommandParser();

    @Test
    public void parse_validCommand_success() throws ParseException {
        // single tag - accepted
        assertParseSuccess(parser, "1 " + "2", new DeleteTagCommand(Index.fromOneBased(1), 2));
        assertParseSuccess(parser, "1 " + "2         ",
                new DeleteTagCommand(Index.fromOneBased(1), 2)); // lot of spaces at end
    }

    @Test
    public void parse_noIndexAndTagNumber_failure() {
        assertParseFailure(parser, "1 ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTagCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTagCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidIndex_failure() {
        assertParseFailure(parser, "one 2 ",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTagCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidTagNumber_failure() {
        assertParseFailure(parser, "1 two ",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTagCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1 2 stringhere ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTagCommand.MESSAGE_USAGE));
    }
}
