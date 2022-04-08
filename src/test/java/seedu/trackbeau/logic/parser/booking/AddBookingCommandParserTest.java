package seedu.trackbeau.logic.parser.booking;

import static seedu.trackbeau.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackbeau.logic.commands.CommandTestUtil.CUSTOMERINDEX_FIRST;
import static seedu.trackbeau.logic.commands.CommandTestUtil.CUSTOMERINDEX_SECOND;
import static seedu.trackbeau.logic.commands.CommandTestUtil.DATETIME_DESC1;
import static seedu.trackbeau.logic.commands.CommandTestUtil.DATETIME_DESC2;
import static seedu.trackbeau.logic.commands.CommandTestUtil.INVALID_CUSTOMERINDEX;
import static seedu.trackbeau.logic.commands.CommandTestUtil.INVALID_DATETIME;
import static seedu.trackbeau.logic.commands.CommandTestUtil.INVALID_SERVICEINDEX;
import static seedu.trackbeau.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.trackbeau.logic.commands.CommandTestUtil.SERVICEINDEX_FIRST;
import static seedu.trackbeau.logic.commands.CommandTestUtil.SERVICEINDEX_SECOND;
import static seedu.trackbeau.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.trackbeau.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.trackbeau.logic.commands.booking.AddBookingCommand;
import seedu.trackbeau.logic.parser.ParserUtil;
import seedu.trackbeau.model.booking.BookingDateTime;

public class AddBookingCommandParserTest {
    private AddBookingCommandParser parser = new AddBookingCommandParser();

    private BookingDateTime st = new BookingDateTime("10-10-2022 10:00");
    private AddBookingCommand expectedCommand = new AddBookingCommand(0, 0, st);

    @Test
    public void parse_allFieldsPresent_success() {
        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + CUSTOMERINDEX_FIRST + SERVICEINDEX_FIRST
                + DATETIME_DESC1, expectedCommand);

        // multiple customer index - last index accepted
        assertParseSuccess(parser, CUSTOMERINDEX_SECOND + CUSTOMERINDEX_FIRST + SERVICEINDEX_FIRST
                + DATETIME_DESC1, expectedCommand);

        // multiple service index - last index accepted
        assertParseSuccess(parser, SERVICEINDEX_SECOND + CUSTOMERINDEX_FIRST + SERVICEINDEX_FIRST
                + DATETIME_DESC1, expectedCommand);

        // multiple datetime - last datetime accepted
        assertParseSuccess(parser, CUSTOMERINDEX_FIRST + SERVICEINDEX_FIRST + DATETIME_DESC2
                + DATETIME_DESC1, expectedCommand);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddBookingCommand.MESSAGE_USAGE);

        // missing customer index prefix
        assertParseFailure(parser, SERVICEINDEX_FIRST + DATETIME_DESC1,
                expectedMessage);

        // missing service index prefix
        assertParseFailure(parser, CUSTOMERINDEX_FIRST + DATETIME_DESC1,
                expectedMessage);

        // missing date time prefix
        assertParseFailure(parser, CUSTOMERINDEX_FIRST + SERVICEINDEX_FIRST,
                expectedMessage);
    }

    @Test
    public void parse_invalidCustomerIndex_failure() {
        // invalid customer index
        assertParseFailure(parser, INVALID_CUSTOMERINDEX + SERVICEINDEX_FIRST + DATETIME_DESC1,
                ParserUtil.MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_invalidServiceIndex_failure() {
        // invalid service index
        assertParseFailure(parser, CUSTOMERINDEX_FIRST + INVALID_SERVICEINDEX + DATETIME_DESC1,
                ParserUtil.MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_invalidDateTime_failure() {
        // invalid date time
        assertParseFailure(parser, CUSTOMERINDEX_FIRST + SERVICEINDEX_FIRST + INVALID_DATETIME,
                BookingDateTime.MESSAGE_CONSTRAINTS);
    }
}
