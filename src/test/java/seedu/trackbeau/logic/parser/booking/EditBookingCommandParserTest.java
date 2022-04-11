package seedu.trackbeau.logic.parser.booking;

import org.junit.jupiter.api.Test;
import seedu.trackbeau.logic.commands.customer.EditCustomerCommand;
import seedu.trackbeau.logic.parser.ParserUtil;
import seedu.trackbeau.model.booking.BookingDateTime;

import static seedu.trackbeau.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackbeau.logic.commands.CommandTestUtil.*;
import static seedu.trackbeau.logic.parser.CommandParserTestUtil.assertParseFailure;

public class EditBookingCommandParserTest {
    private EditBookingCommandParser parser = new EditBookingCommandParser();

    public static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCustomerCommand.MESSAGE_USAGE);

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-1 " + INVALID_CUSTOMERINDEX + SERVICEINDEX_FIRST + DATETIME_DESC1 + FEEDBACK_DESC1,
                MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0 " + INVALID_CUSTOMERINDEX + SERVICEINDEX_FIRST + DATETIME_DESC1 + FEEDBACK_DESC1,
                MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidCustomerIndex_failure() {
        // invalid customer index
        assertParseFailure(parser, "0 " + INVALID_CUSTOMERINDEX + SERVICEINDEX_FIRST + DATETIME_DESC1 + FEEDBACK_DESC1,
                ParserUtil.MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_invalidServiceIndex_failure() {
        // invalid customer index
        assertParseFailure(parser, "0 " + CUSTOMERINDEX_FIRST + INVALID_SERVICEINDEX + DATETIME_DESC1 + FEEDBACK_DESC1,
                ParserUtil.MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_invalidDateTime_failure() {
        // invalid date time
        assertParseFailure(parser, "0 " + CUSTOMERINDEX_FIRST + SERVICEINDEX_FIRST + INVALID_DATETIME,
                BookingDateTime.MESSAGE_CONSTRAINTS);
    }
}
