package seedu.trackbeau.logic.parser.service;

import static seedu.trackbeau.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackbeau.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.trackbeau.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.trackbeau.testutil.TypicalIndexes.INDEX_FIRST_SERVICE;
import static seedu.trackbeau.testutil.TypicalIndexes.INDEX_SECOND_SERVICE;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.trackbeau.commons.core.index.Index;
import seedu.trackbeau.logic.commands.service.DeleteServiceCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteBookingCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCustomerCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteServiceCommandParserTest {

    private DeleteServiceCommandParser parser = new DeleteServiceCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        ArrayList<Index> services = new ArrayList<>() {
            {
                add(INDEX_FIRST_SERVICE);
                add(INDEX_SECOND_SERVICE);
            }
        };
        assertParseSuccess(parser, "1", new DeleteServiceCommand(services));
    }

    @Test
    public void parse_validMultipleArgs_returnsDeleteCommand() {
        ArrayList<Index> services = new ArrayList<>() {
            {
                add(INDEX_FIRST_SERVICE);
                add(INDEX_SECOND_SERVICE);
            }
        };
        assertParseSuccess(parser, "1,2", new DeleteServiceCommand(services));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteServiceCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "-1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteServiceCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidMultipleArgs_throwsParseException() {
        assertParseFailure(parser, "1, a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteServiceCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "1, -2",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteServiceCommand.MESSAGE_USAGE));
    }
}
