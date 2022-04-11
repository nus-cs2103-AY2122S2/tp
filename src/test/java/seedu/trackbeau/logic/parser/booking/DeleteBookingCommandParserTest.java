package seedu.trackbeau.logic.parser.booking;

import static seedu.trackbeau.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackbeau.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.trackbeau.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.trackbeau.testutil.TypicalIndexes.INDEX_FIRST_BOOKING;
import static seedu.trackbeau.testutil.TypicalIndexes.INDEX_SECOND_BOOKING;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.trackbeau.commons.core.index.Index;
import seedu.trackbeau.logic.commands.booking.DeleteBookingCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteBookingCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCustomerCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteBookingCommandParserTest {

    private DeleteBookingCommandParser parser = new DeleteBookingCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        ArrayList<Index> bookings = new ArrayList<>() {
            {
                add(INDEX_FIRST_BOOKING);
                add(INDEX_SECOND_BOOKING);
            }
        };
        assertParseSuccess(parser, "1", new DeleteBookingCommand(bookings));
    }

    @Test
    public void parse_validMultipleArgs_returnsDeleteCommand() {
        ArrayList<Index> bookings = new ArrayList<>() {
            {
                add(INDEX_FIRST_BOOKING);
                add(INDEX_SECOND_BOOKING);
            }
        };
        assertParseSuccess(parser, "1,2", new DeleteBookingCommand(bookings));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteBookingCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "-1",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteBookingCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidMultipleArgs_throwsParseException() {
        assertParseFailure(parser, "1, a",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteBookingCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "1, -2",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteBookingCommand.MESSAGE_USAGE));
    }
}
